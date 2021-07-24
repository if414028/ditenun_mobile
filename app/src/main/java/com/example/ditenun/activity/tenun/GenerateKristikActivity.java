package com.example.ditenun.activity.tenun;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ditenun.R;
import com.example.ditenun.databinding.ActivityGenerateKristikBinding;
import com.example.ditenun.network.DitenunApiClient;
import com.example.ditenun.network.DitenunNetworkInterface;
import com.example.ditenun.utility.BitmapUtility;
import com.example.ditenun.utility.DialogUtility;
import com.example.ditenun.utility.FileUtility;
import com.example.ditenun.utility.KristikDrawable;
import com.example.ditenun.utility.OnConfirmListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ditenun.activity.tenun.GenerateMotifActivity.DELETE_IMAGE_AFTER_READ;
import static com.example.ditenun.activity.tenun.GenerateMotifActivity.EDIT_IMAGE_INTENT_CODE;
import static com.example.ditenun.activity.tenun.GenerateMotifActivity.IMAGE_PATH;

public class GenerateKristikActivity extends AppCompatActivity {

    private ActivityGenerateKristikBinding binding;

    private DitenunNetworkInterface networkInterface;
    private Realm realm;

    private byte[] motifBytes;
    private Bitmap motifBitmap;
    private Bitmap kristikBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_generate_kristik);
        networkInterface = DitenunApiClient.createService(DitenunNetworkInterface.class);
        realm = Realm.getDefaultInstance();

        initLayout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_IMAGE_INTENT_CODE && resultCode == RESULT_OK) {
            String path = data.getStringExtra(IMAGE_PATH);
            boolean needDeleted = data.getBooleanExtra(DELETE_IMAGE_AFTER_READ, false);

            byte[] imageBytes = FileUtility.LoadImageFile(path, needDeleted);
            kristikBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            Bitmap showableKristik = BitmapUtility.drawableToBitmap(new KristikDrawable(kristikBitmap), getApplicationContext());
            showKristikPreview(showableKristik);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (kristikBitmap != null) {
            dialogConfirmation();
        } else {
            onBackPressed();
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (kristikBitmap != null) {
            dialogConfirmation();
        } else {
            super.onBackPressed();
            finish();
        }
    }

    private void initLayout() {
        if (getAdditionalData()) {
            setupCurrentView();
        } else {
            Toast.makeText(getApplicationContext(), "Tidak dapat membaca gambar!", Toast.LENGTH_LONG).show();
        }
        setupAction();
    }

    private boolean getAdditionalData() {
        Intent intent = getIntent();
        if (intent != null) {
            String path = intent.getStringExtra(IMAGE_PATH);
            boolean needDeleted = intent.getBooleanExtra(DELETE_IMAGE_AFTER_READ, false);

            if (path != null && loadImageFromPath(path, needDeleted)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private void setupAction() {
        binding.generateButton.setOnClickListener(v -> {
            clearKristikPreview();
            generateKristik();
        });
        binding.btnToWebApp.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://ditenun.com/webapp/user/dashboard");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }

    private void setupCurrentView() {
        showMotifPreview(motifBitmap);

        hideLoading();
    }

    private boolean loadImageFromPath(String path, boolean needDeleted) {
        motifBytes = FileUtility.LoadImageFile(path, needDeleted);

        if (motifBytes != null) {
            motifBitmap = BitmapFactory.decodeByteArray(motifBytes, 0, motifBytes.length);
            return true;
        } else {
            return false;
        }
    }

    public int getKristikSize() {
        int selectedIdRepeatEvent = binding.radioGroupUkuranKristik.getCheckedRadioButtonId();
        if (selectedIdRepeatEvent == R.id.radioButtonBesar) {
            return 7;
        } else if (selectedIdRepeatEvent == R.id.radioButtonSedang) {
            return 4;
        } else if (selectedIdRepeatEvent == R.id.radioButtonKecil) {
            return 2;
        } else {
            return 4;
        }
    }

    private int getColorSize() {
        int selectedIdRepeatEvent = binding.radioGroupJumlahWarna.getCheckedRadioButtonId();
        if (selectedIdRepeatEvent == R.id.radioButton2Warna) {
            return 5;
        } else if (selectedIdRepeatEvent == R.id.radioButton5Warna) {
            return 15;
        } else if (selectedIdRepeatEvent == R.id.radioButton50Warna) {
            return 30;
        } else {
            return 5;
        }
    }

    private void generateKristik() {
        int kristikSize = getKristikSize();
        int colorSize = getColorSize();
        requestKristikFromServer(kristikSize, colorSize, motifBytes);

        showLoading();
    }

    private void requestKristikFromServer(int squareSize, int colorAmount, byte[] motifBytes) {
        RequestBody photoBody = RequestBody.create(MediaType.parse("image/*"), motifBytes);
        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("img_file", "motif.jpg", photoBody);

        networkInterface.kristikEditor(DitenunApiClient.ACCESS_TOKEN, squareSize, colorAmount, photoPart).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    kristikBitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    Bitmap showableKristik = BitmapUtility.drawableToBitmap(new KristikDrawable(kristikBitmap), getApplicationContext());
                    showKristikPreview(showableKristik);
                    binding.lyGenerateKristikConfiguration.setVisibility(View.GONE);
                    binding.lyToWebApp.setVisibility(View.VISIBLE);
                }

                hideLoading();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                binding.lyGenerateKristikConfiguration.setVisibility(View.VISIBLE);
                binding.lyToWebApp.setVisibility(View.GONE);
                hideLoading();
            }
        });
    }

    private void showMotifPreview(Bitmap bitmap) {
        binding.motifImageView.setImageBitmap(bitmap);
        binding.motifImageView.setVisibility(View.VISIBLE);
    }

    private void clearKristikPreview() {
        binding.mainKristikView.setImageBitmap(null);
    }

    private void showKristikPreview(Bitmap bitmap) {
        binding.mainKristikView.setImageBitmap(bitmap);
        binding.kristikZoomLayout.zoomTo(1, false);

        binding.motifImageView.setVisibility(View.INVISIBLE);
        binding.kristikZoomLayout.setVisibility(View.VISIBLE);
    }

    private void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void dialogConfirmation() {
        DialogUtility.getInstance().displayConfirmationDialog(this,
                R.drawable.ic_ask,
                getResources().getString(R.string.keluartanpasimpan),
                getResources().getString(R.string.kembali),
                getResources().getString(R.string.tidak),
                new OnConfirmListener() {
                    @Override
                    public void onPositive(View view, BottomSheetDialog dialog) {
                        dialog.dismiss();
                        GenerateKristikActivity.this.finish();
                    }

                    @Override
                    public void onNegative(View view, BottomSheetDialog dialog) {
                        dialog.dismiss();
                    }
                });
    }

}