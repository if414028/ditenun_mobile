package com.example.ditenun.utility;

import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public interface OnConfirmListener {
    void onPositive(View view, BottomSheetDialog dialog);
    void onNegative(View view, BottomSheetDialog dialog);
}
