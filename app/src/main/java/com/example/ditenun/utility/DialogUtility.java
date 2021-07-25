package com.example.ditenun.utility;

import android.app.Activity;

public class DialogUtility {

    private static final DialogUtility ourInstance = new DialogUtility();

    public static DialogUtility getInstance() {
        return ourInstance;
    }

    private DialogUtility() {
    }

    public void displayConfirmationDialog(Activity activity, int dialogImage, String info, String positiveButtonText, String negativeButtonText, boolean isCancelable, OnConfirmListener listener) {
        DitenunBottomAlert.Builder builder = new DitenunBottomAlert.Builder()
                .setImgInfo(dialogImage)
                .setInfo(info)
                .setCancelable(isCancelable)
                .setPositiveButtonText(positiveButtonText);
        if (negativeButtonText != null) {
            builder.setNegativeButtonText(negativeButtonText);
        }
        builder.setDitenunBottomClickedListener(listener);
        DitenunBottomAlert dialog = builder.build(activity);
        dialog.show();

    }

}
