package com.example.cookpal.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by ghanem on 8/9/2017.
 */

public class ScreenUtils {
    public static int convertDpToPixel(float dp, Context context) {
        try {
            Resources resources = context.getResources();
            DisplayMetrics metrics = resources.getDisplayMetrics();
            Float px = dp * (metrics.densityDpi / 160f);
            return px.intValue();
        } catch (Exception ex) {
            ex.printStackTrace();
            return (int) (dp * 3);
        }
    }

    public static float dpToPx(final Context context, final float dp) {
        if (context != null) {
            return dp * context.getResources().getDisplayMetrics().density;
        }
        return 0;
    }

    public static int getScreenHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        return height;
    }

    public static int getScreenWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        return width;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
