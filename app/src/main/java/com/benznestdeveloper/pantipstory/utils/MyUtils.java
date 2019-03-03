package com.benznestdeveloper.pantipstory.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.MyContextor;
import com.benznestdeveloper.pantipstory.R;

/**
 * Created by benznest on 16-Jul-17.
 */

public class MyUtils {
//
//    public static boolean isTablet() {
////        return MyContextor.getInstance().getResources().getBoolean(R.bool.is_tablet);
//    }

    public static void log(String str) {
        Log.d("BENZNESTLOG", str);
    }


    public static double positive(double value) {
        if (value < 0) {
            return value * -1;
        }
        return value;
    }

//    public static void showRemoveDialog(Context context,
//                                        DialogInterface.OnClickListener onOkListner) {
//        AlertDialog.Builder builder =
//                new AlertDialog.Builder(context);
//        builder.setTitle(R.string.confirm_remove);
//        builder.setPositiveButton(R.string.remove, onOkListner);
//        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        builder.create();
//        builder.show();
//    }

    public static Snackbar showSnackBarSuccess(Context context, View view, int res_message) {
        if (context == null) {
            return null;
        }

        String message = context.getString(res_message);
        return showSnackBarSuccess(context, view, message);
    }

    public static Snackbar showSnackBarSuccess(Context context, View view, String message) {
        if (context == null) {
            return null;
        }

        try {
            Snackbar snackbar = Snackbar.make(view, " " + message, Snackbar.LENGTH_LONG)
                    .setAction("Redo", null);

            View snackBarLayout = snackbar.getView();
            snackBarLayout.setBackgroundResource(R.color.colorSuccess);


            TextView textView = (TextView) snackBarLayout.findViewById(android.support.design.R.id.snackbar_text);
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle, 0, 0, 0);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(5, 0, 0, 0);
            textView.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
            snackbar.show();
            return snackbar;
        } catch (NullPointerException e) {
            //
        }
        return null;
    }

    public static Snackbar showSnackBarWarning(Context context, View view, int res_message) {
        String message = context.getString(res_message);
        return showSnackBarWarning(context, view, message);
    }

    public static Snackbar showSnackBarWarning(Context context, View view, String message) {
        try {
            Snackbar snackbar = Snackbar.make(view, " " + message, Snackbar.LENGTH_LONG)
                    .setAction("Redo", null);

            View snackBarLayout = snackbar.getView();
            snackBarLayout.setBackgroundResource(R.color.colorWarning);


            TextView textView = (TextView) snackBarLayout.findViewById(android.support.design.R.id.snackbar_text);
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_error_outline, 0, 0, 0);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(5, 0, 0, 0);
            textView.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
            snackbar.show();
            return snackbar;
        } catch (NullPointerException e) {

        }
        return null;
    }

    public static Snackbar showSnackBarFail(Context context, View view, int res_message) {
        if (context == null) {
            context = MyContextor.getInstance();
        }

        String message = context.getString(res_message);
        return showSnackBarFail(context, view, message);
    }

    public static Snackbar showSnackBarFail(Context context, View view, String message) {
        try {
            Snackbar snackbar = Snackbar.make(view, " " + message, Snackbar.LENGTH_SHORT)
                    .setAction("Redo", null);

            View snackBarLayout = snackbar.getView();
            snackBarLayout.setBackgroundResource(R.color.colorError);


            TextView textView = (TextView) snackBarLayout.findViewById(android.support.design.R.id.snackbar_text);
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_error_outline, 0, 0, 0);
            textView.setGravity(Gravity.CENTER_VERTICAL);
            textView.setPadding(5, 0, 0, 0);
            textView.setTextColor(ContextCompat.getColor(context, R.color.colorTextPrimary));
            snackbar.show();
            return snackbar;
        } catch (NullPointerException e) {

        }
        return null;
    }

    public static void hideSoftKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static int getCountChar(String str, char c) {
        int count = 0;
        char[] cStrArray = str.toCharArray();
        for (char cStr : cStrArray) {
            if (cStr == c) {
                count++;
            }
        }
        return count;
    }

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            Network[] activeNetworks = cm.getAllNetworks();
            for (Network n : activeNetworks) {
                NetworkInfo nInfo = cm.getNetworkInfo(n);
                if (nInfo.isConnected()) {
                    return true;
                }
            }

        } else {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }

        return false;

//        ConnectivityManager cm =
//                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void copyToClipboard(String str) {
        ClipboardManager clipboard = (ClipboardManager) MyContextor.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("LABEL", str);
        clipboard.setPrimaryClip(clip);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static int getDrawable(String name) {
        Context context = MyContextor.getInstance();
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

}
