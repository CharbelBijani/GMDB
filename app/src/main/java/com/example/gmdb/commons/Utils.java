package com.example.gmdb.commons;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Utils {

    public static void showSnackBar(View baseview, String message){
        Snackbar.make(baseview, message, Snackbar.LENGTH_LONG).show();
    }

}
