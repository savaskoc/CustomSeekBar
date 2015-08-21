package com.savaskoc.customseekbar.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by savaskoc on 08/08/2015.
 */
public class IntegerUtils {
    private static final DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();

    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }

    public static int round(int number, int interval) {
        int num = number + interval / 2;
        return num - num % interval;
    }
}
