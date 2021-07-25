package com.example.ditenun.activity.tenun;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.example.ditenun.R;
import com.example.ditenun.databinding.ActivityGenerateMotifBinding;
import com.example.ditenun.model.MotifTenun;
import com.example.ditenun.model.StockImage;
import com.example.ditenun.network.DitenunApiClient;
import com.example.ditenun.network.DitenunNetworkInterface;
import com.example.ditenun.utility.BitmapUtility;
import com.example.ditenun.utility.DialogUtility;
import com.example.ditenun.utility.FileUtility;
import com.example.ditenun.utility.OnConfirmListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerateMotifActivity extends AppCompatActivity {

    public static final String TENUN_IMAGE_ID = "TENUN_IMAGE_ID";
    public final static String STOCK_IMAGE_ID = "STOCK_IMAGE_ID";
    static final int EDIT_IMAGE_INTENT_CODE = 102;
    static final int GENERATE_MOTIF_MATRIX_PARAM = 2;
    static final int GENERATE_MOTIF_COLOR_PARAM = 1;
    public final static String IMAGE_PATH = "IMAGE_PATH_PARAM";
    public final static String DELETE_IMAGE_AFTER_READ = "DELETE_IMAGE_PARAM";

    private ActivityGenerateMotifBinding binding;

    private DitenunNetworkInterface networkInterface;
    private Realm realm;

    int stockImageId;
    String tenunImageId;

    StockImage stockImageModel;
    MotifTenun tenunImageModel;

    byte[] seedImageBytes;
    Bitmap seedImageBimap;
    Bitmap outputImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_generate_motif);
        networkInterface = DitenunApiClient.createService(DitenunNetworkInterface.class);
        realm = Realm.getDefaultInstance();

        getAdditionalData();
        initLayout();
        setupAction();
        hideLoading();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_IMAGE_INTENT_CODE && resultCode == RESULT_OK) {
            String path = data.getStringExtra(IMAGE_PATH);
            boolean needDeleted = data.getBooleanExtra(DELETE_IMAGE_AFTER_READ, false);

            byte[] imageBytes = FileUtility.LoadImageFile(path, needDeleted);
            outputImageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            showMotifPreview(outputImageBitmap);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (outputImageBitmap != null) {
            dialogConfirmation();
        } else {
            onBackPressed();
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (outputImageBitmap != null) {
            dialogConfirmation();
        } else {
            super.onBackPressed();
            finish();
        }
    }

    private void getAdditionalData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(STOCK_IMAGE_ID)) {
                stockImageId = intent.getIntExtra(STOCK_IMAGE_ID, 0);
            }
            if (intent.hasExtra(TENUN_IMAGE_ID)) {
                tenunImageId = intent.getStringExtra(TENUN_IMAGE_ID);
            }
        }
    }

    private void initLayout() {
        loadImage();
    }

    private void loadImage() {
        if (stockImageId != 0) {
            stockImageModel = realm.where(StockImage.class).equalTo("id", stockImageId).findFirst();
            seedImageBytes = stockImageModel.getBytes();
            seedImageBimap = BitmapFactory.decodeByteArray(seedImageBytes, 0, seedImageBytes.length);

            outputImageBitmap = seedImageBimap;
            showMotifPreview(outputImageBitmap);
        } else if (tenunImageId != null) {
            tenunImageModel = realm.where(MotifTenun.class).equalTo("id", tenunImageId).findFirst();
            getTenunImage(tenunImageModel.getImageMotif());
        }
    }

    private void showMotifPreview(Bitmap bitmap) {
        binding.mainImageView.setImageBitmap(bitmap);
    }

    private void getTenunImage(String imgUrl) {
        networkInterface.getTentunImage(DitenunApiClient.ENDPOINT + imgUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        seedImageBytes = response.body().bytes();
                        seedImageBimap = BitmapFactory.decodeByteArray(seedImageBytes, 0, seedImageBytes.length);

                        outputImageBitmap = seedImageBimap;
                        showMotifPreview(outputImageBitmap);
                    } catch (IOException e) {
                        showMessage(e.getMessage());
                    }
                    hideLoading();
                } else {
                    showMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showMessage(t.getLocalizedMessage());
            }
        });
    }

    private void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.ok, v -> snackbar.dismiss());
        snackbar.show();
    }

    private void hideLoading() {
        binding.loadingProgressBar.setVisibility(View.GONE);
    }

    private void setupAction() {
        binding.generateMotifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performGenerateMotif();
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (outputImageBitmap != null) {
                    dialogConfirmation();
                } else {
                    finish();
                }
            }
        });

        binding.generateKristikButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGenerateKristikActivity();
            }
        });
    }

    private void showLoading() {
        binding.loadingProgressBar.setVisibility(View.VISIBLE);

        binding.generateMotifButton.setEnabled(false);
        binding.generateKristikButton.setEnabled(false);
    }

    private void performGenerateMotif() {
        showLoading();
        requestMotifFromServer(GENERATE_MOTIF_MATRIX_PARAM, GENERATE_MOTIF_COLOR_PARAM, seedImageBytes);
    }

    private void requestMotifFromServer(int matrix, int color, byte[] motifBytes) {
        RequestBody photoBody = RequestBody.create(MediaType.parse("image/*"), motifBytes);
        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("img_file", "motif.jpg", photoBody);

        networkInterface.generateMotif(DitenunApiClient.ACCESS_TOKEN, matrix, color, photoPart).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    outputImageBitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    showMotifPreview(outputImageBitmap);
                    binding.generateKristikButton.setEnabled(true);
                    binding.generateMotifButton.setEnabled(false);
                } else {
                    binding.generateKristikButton.setEnabled(false);
                    binding.generateMotifButton.setEnabled(true);
                    showMessage(response.message());
                }

                hideLoading();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showMessage(t.getLocalizedMessage());
                hideLoading();
                binding.generateKristikButton.setEnabled(false);
                binding.generateMotifButton.setEnabled(true);
            }
        });
    }

    private void dialogConfirmation() {
        DialogUtility.getInstance().displayConfirmationDialog(this,
                R.drawable.ic_ask,
                getResources().getString(R.string.keluartanpasimpan),
                getResources().getString(R.string.kembali),
                getResources().getString(R.string.tidak),
                true,
                new OnConfirmListener() {
                    @Override
                    public void onPositive(View view, BottomSheetDialog dialog) {
                        dialog.dismiss();
                        GenerateMotifActivity.this.finish();
                    }

                    @Override
                    public void onNegative(View view, BottomSheetDialog dialog) {
                        dialog.dismiss();
                    }
                });
    }

    private void startGenerateKristikActivity() {
        Intent intent = new Intent(this, GenerateKristikActivity.class);

        String path = FileUtility.CreateTempFile("Motif", null, getCacheDir(), BitmapUtility.convertToBytes(outputImageBitmap));

        intent.putExtra(IMAGE_PATH, path);
        intent.putExtra(DELETE_IMAGE_AFTER_READ, true);

        startActivity(intent);
    }
}