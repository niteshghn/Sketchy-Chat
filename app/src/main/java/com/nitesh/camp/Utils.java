package com.nitesh.camp;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by niteshverma on 24/06/17.
 */

public class Utils {
    public static int dpToPx(Context c, int dp) {
        DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
