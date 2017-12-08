package wellijohn.org.varchart.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author: JiangWeiwei
 * @time: 2017/9/6-14:31
 * @email:
 * @desc:
 */
public class UiUtils {
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * dip转换px
     */
    public static int dip2px(Context context, float dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * @param paramPaint 画笔
     * @return 画笔的宽度
     */
    public static float getPaintStrokeWidth(Paint paramPaint) {
        return paramPaint.getStrokeWidth();
    }

    /**
     * @param paramText  显示的文本
     * @param paramPaint 画笔
     * @return 文本的宽度
     */
    public static float getTextWidth(@NonNull String paramText, Paint paramPaint) {
        return paramPaint.measureText(paramText);
    }

    /**
     * @param paramText  显示的文本
     * @param paramPaint 画笔
     * @return 文本的高度
     */
    public static float getTextHeight(String paramText, Paint paramPaint) {
        if (TextUtils.isEmpty(paramText)) {
            paramText = "佣金";
        }
        Rect rect = new Rect();
        paramPaint.getTextBounds(paramText, 0, paramText.length(), rect);
        return rect.height();
    }

    /**
     * 获取三个点的控制点
     *
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param paramCoefficient
     * @return
     */
    public static ControlPonits getControlPoints(double x0, double y0, double x1, double y1, double x2, double y2, double paramCoefficient) {
        double d01 = Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
        double d12 = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double fa = paramCoefficient * d01 / (d01 + d12);   // scaling factor for triangle Ta
        double fb = paramCoefficient * d12 / (d01 + d12);   // ditto for Tb, simplifies to fb=t-fa
        double p1x = x1 - fa * (x2 - x0);    // x2-x0 is the width of triangle T
        double p1y = y1 - fa * (y2 - y0);    // y2-y0 is the height of T
        double p2x = x1 + fb * (x2 - x0);
        double p2y = y1 + fb * (y2 - y0);
        ControlPonits tempControlPoints = new ControlPonits();

        tempControlPoints.beforeControlPointX = (float) p1x;
        tempControlPoints.beforeControlPointY = (float) p1y;
        tempControlPoints.afterControlPointX = (float) p2x;
        tempControlPoints.afterControlPointY = (float) p2y;
        return tempControlPoints;
    }

    public static class ControlPonits {
        public float beforeControlPointX;
        public float beforeControlPointY;
        public float afterControlPointX;
        public float afterControlPointY;
    }
}
