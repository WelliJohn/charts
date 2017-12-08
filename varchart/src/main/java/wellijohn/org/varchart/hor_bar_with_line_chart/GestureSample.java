package wellijohn.org.varchart.hor_bar_with_line_chart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;

import java.util.List;
import java.util.Map;

import wellijohn.org.varchart.R;
import wellijohn.org.varchart.vo.DotVo;
import wellijohn.org.varchart.utils.UiUtils;


/**
 * @author: JiangWeiwei
 * @time: 2017/11/13-14:58
 * @email:
 * @desc:
 */
public class GestureSample extends View {

    // Viewport extremes. See mCurrentViewport for a discussion of the viewport.
    private static final float AXIS_X_MIN = -1f;
    private static final float AXIS_X_MAX = 1f;
    private static final float AXIS_Y_MIN = -1f;
    private static final float AXIS_Y_MAX = 1f;
    private static final String TAG = "GestureSample";

    // The current viewport. This rectangle represents the currently visible
    // chart domain and range. The viewport is the part of the app that the
    // user manipulates via touch gestures.
    private RectF mCurrentViewport =
            new RectF(AXIS_X_MIN, AXIS_Y_MIN, AXIS_X_MAX, AXIS_Y_MAX);

    // The current destination rectangle (in pixel coordinates) into which the
    // chart data should be drawn.
    private Rect mContentRect = new Rect();

    private OverScroller mScroller;
    private RectF mScrollerStartViewport = new RectF();

    // Edge effect / overscroll tracking objects.
    private EdgeEffectCompat mEdgeEffectTop;
    private EdgeEffectCompat mEdgeEffectBottom;
    private EdgeEffectCompat mEdgeEffectLeft;
    private EdgeEffectCompat mEdgeEffectRight;

    private boolean mEdgeEffectTopActive;
    private boolean mEdgeEffectBottomActive;
    private boolean mEdgeEffectLeftActive;
    private boolean mEdgeEffectRightActive;
    private float mCurrentX;

    //y轴的点
    private double[] mYdots;
    //x轴的点
    private String[] mXdots;

    //屏幕的宽高
    private double mScreenWidth;
    private double mScreenHeight;

    //y轴上的间隔距离
    private double mYinterval;
    //x轴上的间隔距离
    private double mXinterval;

    private int mXvisibleNum = 7;
    private int mYvisibleNum = 6;
    //x轴默认显示最大为7个
    private static final int mDefXMaxNum = 7;

    //画x轴横线的画笔
    private Paint mXlinePaint;

    //y轴的文字的画笔
    private Paint mYNumPaint;

    private Canvas mYNumCanvas;

    private float mScrollPosX;
    private Bitmap mBitmap;

    //总共在x轴滑动的距离
    private float mTotalScrollX = 0f;

    //显示的坐标点
    private List<DotVo> mListDisDots;

    //连线的画笔
    private Paint mLinePaint;

    //y轴的点对应的x的位置
    private Map<String, Float> mYDotMaps;
    //判断数据是否有初始化
    private boolean mIsInitDataSuc = false;

    private Path mLinePath;

    private float mLineLength;

    private float[] mCurrentPosition = new float[2];
    private PathMeasure mPathMeasure;

    //绘制的起始坐标
    private float mFirstXPos = 0;
    private float mFirstYPos = 0;

    private final Path mLineDrawPath = new Path();

    //判断是否绘制结束
    private boolean isDrawOver;

    //是否开启动画绘制
    private boolean isAnimationOpen;


    private GestureDetectorCompat mGestureDetector;

    private Point mSurfaceSizeBuffer = new Point();

    public GestureSample(Context context) {
        this(context, null);
    }

    public GestureSample(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureSample(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetectorCompat(context, mGestureListener);
        mScroller = new OverScroller(context);

        // Sets up edge effects
        mEdgeEffectLeft = new EdgeEffectCompat(context);
        mEdgeEffectTop = new EdgeEffectCompat(context);
        mEdgeEffectRight = new EdgeEffectCompat(context);
        mEdgeEffectBottom = new EdgeEffectCompat(context);
    }

    private final GestureDetector.SimpleOnGestureListener mGestureListener
            = new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onDown(MotionEvent e) {
            // Initiates the decay phase of any active edge effects.
            releaseEdgeEffects();
            mScrollerStartViewport.set(mCurrentViewport);
            // Aborts any active scroll animations and invalidates.
            mScroller.forceFinished(true);
//            ViewCompat.postInvalidateOnAnimation(GestureSample.this);
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            mTotalScrollX += -distanceX;
            Log.d(TAG, "------------------------onScroll: " + mTotalScrollX + ",Fling的距离：" + mScroller.getFinalX());
            // 边界判断，不让滑块滑出边界
            ViewCompat.postInvalidateOnAnimation(GestureSample.this);


            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            fling((int) velocityX, (int) velocityY);
            return true;
        }
    };


    private void fling(int velocityX, int velocityY) {
        Log.d(TAG, "-------------------------fling: ");
        // Initiates the decay phase of any active edge effects.
        releaseEdgeEffects();
        // Flings use math in pixels (as opposed to math based on the viewport).
        mScrollerStartViewport.set(mCurrentViewport);
        // Before flinging, aborts the current animation.
        mScroller.forceFinished(true);
        // Begins the animation
        mScroller.fling(
                // Current scroll position
                mScroller.getCurrX(),
                0,
                velocityX,
                0,
            /*
             * Minimum and maximum scroll positions. The minimum scroll
             * position is generally zero and the maximum scroll position
             * is generally the content size less the screen size. So if the
             * content width is 1000 pixels and the screen width is 200
             * pixels, the maximum scroll offset should be 800 pixels.
             */
                -getWidth() + 300, 0,
                0, 0
                // The edges of the content. This comes into play when using
                // the EdgeEffect class to draw "glow" overlays.
        );
        ViewCompat.postInvalidateOnAnimation(this);
    }


    private void releaseEdgeEffects() {
        mEdgeEffectLeftActive
                = mEdgeEffectTopActive
                = mEdgeEffectRightActive
                = mEdgeEffectBottomActive
                = false;
        mEdgeEffectLeft.onRelease();
        mEdgeEffectTop.onRelease();
        mEdgeEffectRight.onRelease();
        mEdgeEffectBottom.onRelease();
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        boolean needsInvalidate = false;


        // The scroller isn't finished, meaning a fling or programmatic pan
        // operation is currently active.
        if (mScroller.computeScrollOffset()) {
            Log.d(TAG, "computeScroll: " + mTotalScrollX);
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
//            boolean canScrollX = (mCurrentViewport.left > AXIS_X_MIN
//                    || mCurrentViewport.right < AXIS_X_MAX);
            boolean canScrollX = mTotalScrollX + mScroller.getCurrX() < 0 && mTotalScrollX + mScroller.getCurrX() > -getWidth() + 300;
            needsInvalidate = canScrollX;
        }

        if (needsInvalidate) {
            Log.d(TAG, "computeScroll: " + mTotalScrollX + "x惯性滑动的偏移距离: " + mScroller.getCurrX());
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(UiUtils.dip2px(getContext(), 0.5f));
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.app_red));
        paint.setTextSize(36);
        paint.setAntiAlias(true);
//        int x = (mCurrentViewport.left + (mCurrentViewport.width() / DRAW_STEPS * i));

        mCurrentX = mTotalScrollX + mScroller.getCurrX();
        Log.d(TAG, "onDraw: " + mCurrentX + ",mTotalScrollX:" + mTotalScrollX + ",mScroller.getCurrX():" + mScroller.getCurrX());
        if (mCurrentX <= -UiUtils.getScreenWidth(getContext()) + 300) {
            mTotalScrollX = -UiUtils.getScreenWidth(getContext()) + 300 - mScroller.getCurrX();
        }

        if (mCurrentX >= 0) {
            mTotalScrollX = 0 - mScroller.getCurrX();
        }
        mCurrentX = mTotalScrollX + mScroller.getCurrX();
        canvas.drawText("测试移动aaaaaaaaaafffffffffffffqqqqq1231212323556345qqqqqqqqqqqqqqqweeeeeeeeeeeewqeqweqwerterwtertert", mCurrentX, 300, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }
}
