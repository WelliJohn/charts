package wellijohn.org.varchart.beizcurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import wellijohn.org.varchart.R;
import wellijohn.org.varchart.utils.UiUtils;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/24-9:50
 * @email:
 * @desc:
 */
public class BeizCurve extends View {


    private List<Point> mListPoints;

    private Paint mBeizCurvePaint;
    private Path mPath = new Path();
    private Paint mPointPaint;

    public BeizCurve(Context context) {
        this(context, null);
    }

    public BeizCurve(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BeizCurve(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mBeizCurvePaint = new Paint();
        mBeizCurvePaint.setStrokeWidth(UiUtils.dip2px(getContext(), 0.1f));
        mBeizCurvePaint.setStyle(Paint.Style.STROKE);
        mBeizCurvePaint.setColor(ContextCompat.getColor(getContext(), R.color.app_red));
        mBeizCurvePaint.setAntiAlias(true);


        mPointPaint = new Paint();
        mPointPaint.setStrokeWidth(UiUtils.dip2px(getContext(), 5f));
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        mPointPaint.setAntiAlias(true);

    }

    public void setPointList(List<Point> paramListPoints) {
        this.mListPoints = paramListPoints;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mListPoints != null && mListPoints.size() > 0) {
            UiUtils.ControlPonits cp = null;
            for (int i = 0, size = mListPoints.size(); i < size; i++) {
                canvas.drawPoint(mListPoints.get(i).x, mListPoints.get(i).y, mPointPaint);
                if (i == 0) {// 第一条为二阶贝塞尔
                    mPath.moveTo(mListPoints.get(i).x, mListPoints.get(i).y);// 起点
                } else {
                    final float cpx = (mListPoints.get(i - 1).x)
                            + (mListPoints.get(i).x - mListPoints.get(i - 1).x) / 2.0f;

                    mPath.cubicTo(
                            cpx, mListPoints.get(i - 1).y,
                            cpx, mListPoints.get(i).y,
                            mListPoints.get(i).x, mListPoints.get(i).y);
                }

            }


            canvas.drawPath(mPath, mBeizCurvePaint);
        }
    }
}
