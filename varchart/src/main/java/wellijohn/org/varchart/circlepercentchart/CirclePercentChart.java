package wellijohn.org.varchart.circlepercentchart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

import wellijohn.org.varchart.circlepercentchart.exception.PercentOverFlowException;
import wellijohn.org.varchart.circlepercentchart.vo.ArcVo;
import wellijohn.org.varchart.utils.UiUtils;

import static android.content.ContentValues.TAG;

/**
 * @author: JiangWeiwei
 * @time: 2017/9/14-14:25
 * @email: wellijohn1991@gmail.com
 * @desc:
 */
public class CirclePercentChart extends View {
    //绘制外部大圆圈的paint
    private Paint mCirclePaint;

    //绘制扫描区域的paint，基本上就是颜色改变。
    private Paint mArcPaint;

    //显示的绘制角度的范围
    private List<ArcVo> mDisArcList;

    //绘制矩形
    private RectF mDrawCircleRect;

    //文字的paint
    private Paint mTextPaint;

    //圆的半径
    private float mRaduis;

    private Path mTextPath = new Path();

    private float mDrawAngle;

    public CirclePercentChart(Context context) {
        this(context, null);
    }

    public CirclePercentChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CirclePercentChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDrawCircleRect = new RectF(0, 0, w, h);
        mRaduis = h / 2;
    }


    private void initPaint() {
        mArcPaint = new Paint();
        mArcPaint.setStrokeWidth(UiUtils.dip2px(getContext(), 0.1f));
        mArcPaint.setStyle(Paint.Style.FILL);
        mArcPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setStrokeWidth(UiUtils.dip2px(getContext(), 0.1f));
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(UiUtils.dip2px(getContext(), 14));
        mTextPaint.setColor(Color.WHITE);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (!canDraw()) return;
        float sweepAngle;
        float startAngle = 0;
        for (int i = 0, size = mDisArcList.size(); i < size; i++) {
            ArcVo temp = mDisArcList.get(i);
            mArcPaint.setColor(temp.getScanColor());
            sweepAngle = temp.getPercentInCircle() * 360;
            if (startAngle + sweepAngle > mDrawAngle) {
                sweepAngle = mDrawAngle - startAngle;
            }
            Log.d(TAG, "onDraw: startAngle:" + startAngle + ",sweepAngle:" + sweepAngle);
            canvas.drawArc(mDrawCircleRect, startAngle, sweepAngle, true, mArcPaint);
            drawText(canvas, sweepAngle, startAngle, temp);
            startAngle = startAngle + sweepAngle;
        }
    }


    // 开启路径动画
    private void startPathAnim(long duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 360);
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDrawAngle = (float) animation.getAnimatedValue();
                ViewCompat.postInvalidateOnAnimation(CirclePercentChart.this);
            }
        });

        valueAnimator.start();

    }

    private void drawText(Canvas canvas, float sweepAngle, float startAngle, ArcVo temp) {
        float middleAngle;
        middleAngle = startAngle + sweepAngle / 2;
        float startX;
        float startY;
        float endX;
        float endY;
        String drawText = temp.getPercentInCircle() * 100 + "%";
        if (middleAngle <= 90) {
            //在第四象限
            double angle = middleAngle;
            angle = Math.toRadians(angle);
            startY = endY = (float) (Math.sin(angle) * mRaduis + mRaduis);
            endX = (float) (mRaduis + Math.cos(angle) * mRaduis);
            startX = endX - UiUtils.getTextWidth(drawText, mTextPaint);
        } else if (middleAngle <= 180) {
            //在第三象限
            double angle = 180 - middleAngle;
            angle = Math.toRadians(angle);
            startY = endY = (float) (Math.sin(angle) * mRaduis + mRaduis);
            startX = (float) (mRaduis - Math.cos(angle) * mRaduis);
            endX = startX + UiUtils.getTextWidth(drawText, mTextPaint);
        } else if (middleAngle <= 270) {
            //在第二象限
            double angle = 270 - middleAngle;
            angle = Math.toRadians(angle);
            startY = endY = (float) (mRaduis - Math.cos(angle) * mRaduis);
            startX = (float) (mRaduis - Math.sin(angle) * mRaduis);
            endX = startX + UiUtils.getTextWidth(drawText, mTextPaint);
        } else {
            //在第一象限
            double angle = 360 - middleAngle;
            angle = Math.toRadians(angle);
            startY = endY = (float) (mRaduis - Math.sin(angle) * mRaduis);
            endX = (float) (mRaduis + Math.cos(angle) * mRaduis);
            startX = endX - UiUtils.getTextWidth(drawText, mTextPaint);
        }

        mTextPath.reset();
        mTextPath.moveTo(startX, startY);
        mTextPath.lineTo(endX, endY);
        if (middleAngle > 180) {
            canvas.drawTextOnPath(drawText, mTextPath, 0, UiUtils.getTextHeight(drawText, mTextPaint), mTextPaint);
        } else {
            canvas.drawTextOnPath(drawText, mTextPath, 0, -UiUtils.getTextHeight(drawText, mTextPaint), mTextPaint);

        }
    }

    public CirclePercentChart setDisArcList(List<ArcVo> paramDisArcList) {
        this.mDisArcList = paramDisArcList;
        return this;
    }

    public void reDraw() throws PercentOverFlowException {
        judgeIsOverFlow();
        startPathAnim(1000);
    }

    private void judgeIsOverFlow() throws PercentOverFlowException {
        if (canDraw()) {
            float totalPercent = 0;
            for (ArcVo vo : mDisArcList) {
                totalPercent += vo.getPercentInCircle();
                if (totalPercent > 1) throw new PercentOverFlowException("total percent over folw");
            }
        }
    }


    private boolean canDraw() {
        if (mDisArcList != null && mDisArcList.size() > 0) {
            float totalPercent = 0;
            for (ArcVo vo : mDisArcList) {
                totalPercent += vo.getPercentInCircle();
                if (totalPercent > 1) return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
