package com.arasan.master.di.utility;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

public class Utils {

    public static void ShowSnackBarLinearLayout(LinearLayout linearLayout, String aMsg) {
        Snackbar snackbar = Snackbar
                .make(linearLayout, aMsg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public static void ShowSnackBarRelativeLayout(RelativeLayout relativeLayout, String aMsg) {
        Snackbar snackbar = Snackbar
                .make(relativeLayout, aMsg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public static void ShowSnackBarConstraintLayout(ConstraintLayout linearLayout, String aMsg) {
        Snackbar snackbar = Snackbar
                .make(linearLayout, aMsg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
