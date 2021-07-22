package com.example.ditenun.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;

import com.example.ditenun.R;
import com.example.ditenun.databinding.DitenunBottomAlertBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Observable;

public class DitenunBottomAlert extends BaseObservable {

    DitenunBottomAlertBinding binding;

    private Context context;

    private BottomSheetDialog dialog;

    private int imgInfo;

    private String info;

    private String subInfo;

    private String negativeButtonText;

    private String positiveButtonText;

    private int negativeButtonVisibility;

    private int positiveButtonVisibility;

    private OnConfirmListener listener;

    private boolean isCancelable = true;

    private DitenunBottomAlert(Context context, Builder builder) {
        this.context = context;
        this.imgInfo = builder.imgInfo;
        this.info = builder.info;
        this.subInfo = builder.subInfo;
        this.negativeButtonText = builder.negativeButtonText;
        this.positiveButtonText = builder.positiveButtonText;
        this.listener = builder.listener;
        this.isCancelable = builder.isCancelable;
        bind();
    }

    @Bindable
    public int getImgInfo() {
        return imgInfo;
    }

    public void setImgInfo(int imgInfo) {
        this.imgInfo = imgInfo;
    }

    @Bindable
    public String getSubInfo() {
        return subInfo;
    }

    public void setSubInfo(String subInfo) {
        this.subInfo = subInfo;
    }

    @Bindable
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Bindable
    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    @Bindable
    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
    }

    @Bindable
    public int getNegativeButtonVisibility() {
        return checkNotNullOrEmpty(negativeButtonText) ? View.VISIBLE : View.GONE;
    }

    @Bindable
    public int getPositiveButtonVisibility() {
        return checkNotNullOrEmpty(positiveButtonText) ? View.VISIBLE : View.GONE;
    }

    public OnConfirmListener getListener() {
        return listener;
    }

    public void setListener(OnConfirmListener listener) {
        this.listener = listener;
    }

    private void bind() {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(this.context),
                R.layout.ditenun_bottom_alert,
                null,
                false
        );

        binding.setModel(this);

        dialog = new BottomSheetDialog(context, R.style.DitenunBottomSheetDialogTheme);

        binding.imgInfo.setImageResource(imgInfo);

        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(isCancelable);
        dialog.setContentView(binding.getRoot());
    }

    public void show() {
        dialog.show();
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public BottomSheetDialog getDialog() {
        return dialog;
    }

    public Button getPositiveButton() {
        return binding.positiveButton;
    }

    public Button getNegativeButton() {
        return binding.negativeButton;
    }

    public static class Builder {

        private int imgInfo;

        private String info;

        private String subInfo;

        private String negativeButtonText;

        private String positiveButtonText;

        private OnConfirmListener listener;

        private boolean isCancelable = true;

        public Builder setImgInfo(int imgInfo) {
            this.imgInfo = imgInfo;
            return this;
        }

        public Builder setInfo(String info) {
            this.info = info;
            return this;
        }

        public Builder setSubInfo(String subInfo) {
            this.subInfo = subInfo;
            return this;
        }

        public Builder setNegativeButtonText(String negativeButtonText) {
            this.negativeButtonText = negativeButtonText;
            return this;
        }

        public Builder setPositiveButtonText(String positiveButtonText) {
            this.positiveButtonText = positiveButtonText;
            return this;
        }

        public Builder setDitenunBottomClickedListener(OnConfirmListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder setCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        public DitenunBottomAlert build(Context context) {
            return new DitenunBottomAlert(context, this);
        }
    }

    public interface DitenunBottomAlertClickListener {
        void onPositiveButtonClicked(View view, BottomSheetDialog dialog);

        void onNegativeButtonClicked(View view, BottomSheetDialog dialog);
    }

    private boolean checkNotNullOrEmpty(String string) {
        return (string != null && !string.contains("null") && !string.isEmpty());
    }

}
