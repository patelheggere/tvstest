package com.patelheggere.tvstest.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class utilities {
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean checkInternetStatus(Activity activity) {
        String status = NetworkUtil.getConnectivityStatusString(activity);
        boolean isNetworkAvailable = false;
        try {
            switch (status) {
                case "Connected to Internet with Mobile Data":
                    isNetworkAvailable = true;
                    break;
                case "Connected to Internet with WIFI":
                    isNetworkAvailable = true;
                    break;
                default:
                    isNetworkAvailable = false;
                    break;
            }
            //showSnackBarBasedonStatus(isNetworkAvailable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNetworkAvailable;
    }
    public static AnimatorSet anima(View v)
    {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 0.94f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 0.96f);
        scaleDownX.setDuration(150);
        scaleDownY.setDuration(150);
        scaleDownX.setRepeatCount(1);
        scaleDownY.setRepeatCount(1);
        scaleDownX.setRepeatMode(ValueAnimator.REVERSE);
        scaleDownY.setRepeatMode(ValueAnimator.REVERSE);
        scaleDownX.setInterpolator(new DecelerateInterpolator());
        scaleDownY.setInterpolator(new DecelerateInterpolator());
        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);
        return scaleDown;
    }
}
