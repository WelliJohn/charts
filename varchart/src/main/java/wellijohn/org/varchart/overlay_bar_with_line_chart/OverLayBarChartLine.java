package wellijohn.org.varchart.overlay_bar_with_line_chart;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wellijohn.org.varchart.R;
import wellijohn.org.varchart.exception.YCoordinateException;
import wellijohn.org.varchart.utils.DoubleUtils;
import wellijohn.org.varchart.utils.UiUtils;
import wellijohn.org.varchart.vo.CategoryVo;
import wellijohn.org.varchart.vo.DotVo;

/**
 * @author: JiangWeiwei
 * @time: 2017/9/6-11:55
 * @email:
 * @desc: 图表的数据
 */
public class OverLayBarChartLine extends View {
    private static final String TAG = "ChartLine";

    /**
     * 画x轴横线的画笔
     */
    private Paint mXlinePaint;
    /**
     * y轴的文字的画笔
     */
    private TextPaint mYNumPaint;
    /**
     * 柱状图的表格画笔
     */
    private Paint mBarPaint;
    /**
     * 下面的表格的画笔
     */
    private Paint mTableValuePaint;
    /**
     * y轴表格的文字的画笔
     */
    private TextPaint mYTableTextPaint;
    /**
     * 折线的画笔
     */
    private Paint mLinePaint;
    /**
     * 表格下面的文案的点的画笔
     */
    private Paint mLeftDotPaint;
    //y轴的点
    private ArrayList<Double> mYdots;
    //x轴的点
    private String[] mXdots;
    //屏幕的宽高
    private double mScreenWidth;
    //屏幕的宽高
    private double mScreenHeight;

    //y轴上的间隔距离
    private double mYinterval;
    //x轴上的间隔距离
    private double mXinterval;

    private float mXvisibleNum = 7;
    private int mYvisibleNum;
    //x轴默认显示最大为7个
    private float mDefXMaxNum;


    //onScroll总共在x轴滑动的距离
    private float mTotalScrollX = 0f;

    //显示的坐标点
    private List<List<DotVo>> mListDisDots;
    //y轴的点对应的x的位置,这里放置的value的值已经包含在mLeftTextWidth
    private Map<String, Float> mYDotMaps;
    //判断数据是否有初始化
    private boolean mIsInitDataSuc = false;

    private Path mLinePath;

    private float mLineLength;

    private float[] mCurrentPosition = new float[2];
    private PathMeasure mPathMeasure;

    private final Path mLineDrawPath = new Path();

    //判断是否绘制结束
    private boolean isDrawOver;

    //是否开启动画绘制
    private boolean isAnimationOpen;

    /**
     * 从该位置开始画Content,左边的文案的宽度
     */
    private float mLeftTextWidth;

    private float[] mDots = new float[4];

    private List<CategoryVo> mCategoryList;


    /**
     * y轴上面的最大值
     */
    private double mYMaxValue;

    /**
     * 表格下面的文案的图标的宽度
     */
    private float mDotWidth;


    private OverScroller mScroller;
    private final GestureDetector mGestureDetector =
            new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                        float distanceX, float distanceY) {
                    mTotalScrollX += -distanceX;
                    Log.d(TAG, "------------------------onScroll: " + mTotalScrollX + ",Fling的距离：" + mScroller.getFinalX());
                    // 边界判断，不让滑块滑出边界
                    ViewCompat.postInvalidateOnAnimation(OverLayBarChartLine.this);
                    return true;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2,
                                       float velocityX, float velocityY) {
                    fling((int) velocityX, (int) velocityY);
                    return true;
                }

                @Override
                public boolean onDown(MotionEvent e) {
                    if (!mScroller.isFinished()) {
                        mScroller.forceFinished(true);
                    }
                    return true;
                }
            });
    private float mCurrentX;

    /**
     * 往左边滑动的最大距离
     */
    private float mMaxLeftScrollDis;

    /**
     * 往右边滑动的最大距离
     */
    private float mMaxRightScrollDis;

    /**
     * 内容的矩形，只包含滚动的距离
     */
    private Rect mContentRect;
    private Paint mCanvasBGPaint;
    private float mYOffset;

    /**
     * 在数值列表最后一根线的Y坐标
     */
    private float mLastHorLineY;
    /**
     * 全部表格的高度
     */
    private double mChartHeight;
    /**
     * 种类的数量
     */
    private int mCategoryNum;
    /**
     * 日期那一栏的背景颜色
     */
    private Paint mDateBGPaint;
    private float mLastDownX;
    /**
     * Y轴上的数字的padding
     */
    private float mYNumRightPadding;
    /**
     * 点的paint
     */
    private Paint mPointPaint;
    /**
     * 0-1
     */
    private float mPhaseY;
    /**
     * Y轴的最大值
     */
    private double mYAxisMaxValue;
    /**
     * Y轴每一栏代表的数值
     */
    private double mMaxDiv;
    private int blue;
    private int red;
    private int yellow;

    public OverLayBarChartLine(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverLayBarChartLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        blue = ContextCompat.getColor(getContext(), R.color.app_blue);
        red = ContextCompat.getColor(getContext(), R.color.app_red);
        yellow = ContextCompat.getColor(getContext(), R.color.yellow_light);
        initPaint();
        mContentRect = new Rect();
        this.mYdots = new ArrayList<>();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.OverLayBarChartLine);
        mYvisibleNum = ta.getInt(R.styleable.OverLayBarChartLine_overlay_y_visible_num, 6);
        mDefXMaxNum = ta.getFloat(R.styleable.OverLayBarChartLine_overlay_default_x_visible_num, 5);
        mYinterval = ta.getDimensionPixelSize(R.styleable.OverLayBarChartLine_overlay_y_interval, UiUtils.dip2px(context, 40));
        mLeftTextWidth = ta.getDimensionPixelSize(R.styleable.OverLayBarChartLine_overlay_y_num_text_max_width, UiUtils.dip2px(context, 60));
        mYNumRightPadding = ta.getDimensionPixelSize(R.styleable.OverLayBarChartLine_overlay_y_num_right_paddig, UiUtils.dip2px(context, 7));
        ta.recycle();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mContentRect.set((int) mLeftTextWidth, 0, UiUtils.getScreenWidth(getContext()), (int) (h - getXMaxTextHeight()));
        mMaxLeftScrollDis = UiUtils.getScreenWidth(getContext()) - w;

    }

    private void fling(int velocityX, int velocityY) {
        Log.d(TAG, "-------------------------fling: ");
        mScroller.forceFinished(true);
        // Begins the animation
        mScroller.fling(
                // Current scroll position
                mScroller.getCurrX(),
                0,
                velocityX,
                0,
                (int) mMaxLeftScrollDis, 0,
                0, 0
        );
        ViewCompat.postInvalidateOnAnimation(this);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    private void initPaint() {
        mXlinePaint = new Paint();
        mXlinePaint.setStrokeWidth(UiUtils.dip2px(getContext(), 0.1f));
        mXlinePaint.setStyle(Paint.Style.STROKE);
        mXlinePaint.setColor(ContextCompat.getColor(getContext(), R.color.text_999999));
        mXlinePaint.setAntiAlias(true);

        mYNumPaint = new TextPaint();
        mYNumPaint.setStrokeWidth(UiUtils.dip2px(getContext(), 0.03f));
        mYNumPaint.setStyle(Paint.Style.FILL);
        mYNumPaint.setColor(ContextCompat.getColor(getContext(), R.color.text_999999));
        mYNumPaint.setAntiAlias(true);
        mYNumPaint.setTextSize(UiUtils.dip2px(getContext(), 12));

        mYTableTextPaint = new TextPaint();
        mYTableTextPaint.setStrokeWidth(UiUtils.dip2px(getContext(), 0.03f));
        mYTableTextPaint.setStyle(Paint.Style.FILL);
        mYTableTextPaint.setColor(ContextCompat.getColor(getContext(), R.color.text_999999));
        mYTableTextPaint.setAntiAlias(true);
        mYTableTextPaint.setTextSize(UiUtils.dip2px(getContext(), 14));

        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(UiUtils.dip2px(getContext(), 0.5f));
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        mLinePaint.setAntiAlias(true);

        mCanvasBGPaint = new Paint();
        mCanvasBGPaint.setColor(ContextCompat.getColor(getContext(), R.color.trans));

        mBarPaint = new Paint();
        mBarPaint.setStyle(Paint.Style.FILL);

        mTableValuePaint = new Paint();
        mTableValuePaint.setStrokeWidth(UiUtils.dip2px(getContext(), 0.04f));
        mTableValuePaint.setStyle(Paint.Style.FILL);
        mTableValuePaint.setColor(ContextCompat.getColor(getContext(), R.color.text_666666));
        mTableValuePaint.setAntiAlias(true);
        mTableValuePaint.setTextSize(UiUtils.dip2px(getContext(), 13));

        mLeftDotPaint = new Paint();
        mLeftDotPaint.setStrokeWidth(UiUtils.dip2px(getContext(), 7));
        mLeftDotPaint.setStyle(Paint.Style.FILL);


        mDateBGPaint = new Paint();
        mDateBGPaint.setStrokeWidth(UiUtils.dip2px(getContext(), 0.5f));
        mDateBGPaint.setStyle(Paint.Style.FILL);
        mDateBGPaint.setColor(ContextCompat.getColor(getContext(), R.color.app_background));
        mDateBGPaint.setAntiAlias(true);

        mPointPaint = new Paint();
        mPointPaint.setStrokeWidth(UiUtils.dip2px(getContext(), 0.1f));
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setColor(ContextCompat.getColor(getContext(), R.color.point_color));
        mPointPaint.setAntiAlias(true);
    }


    //设置y轴最大值
    public OverLayBarChartLine setYAxisMaxValue(double paramYAxisMaxValue) {
        this.mMaxDiv = DoubleUtils.getLargerInterger(paramYAxisMaxValue, this.mYvisibleNum);
        for (int i = 0; i < this.mYvisibleNum; i++) {
            this.mYdots.add((new BigDecimal(i * this.mMaxDiv)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return this;
    }


    //设置x点的坐标的显示
    public OverLayBarChartLine setXdots(String[] paramXdots) {
        this.mXdots = paramXdots;
        return this;
    }

    //设置显示的坐标点会自动连线起来
    public OverLayBarChartLine setListDisDots(List<List<DotVo>> paramListDisDots) {
        this.mListDisDots = paramListDisDots;
        return this;
    }

    //设置表格的类型的数据
    public OverLayBarChartLine setCategoryList(List<CategoryVo> paramCategoryList) {
        this.mCategoryList = paramCategoryList;
        return this;
    }

    private void initData() throws YCoordinateException {
        for (List<DotVo> tempList : mListDisDots) {
            for (DotVo temp : tempList) {
                if (!Arrays.asList(mXdots).contains(temp.getX())) {
                    Log.e(TAG, "please check if you y dot exists in mYdots,you must set Y dot in Y coordinates");
                    throw new YCoordinateException("please check if you y dot exists in mYdots,you must set Y dot in Y coordinates");
                }
            }
        }
        mScroller = new OverScroller(getContext(), new FastOutLinearInInterpolator());
        mScreenHeight = UiUtils.getScreenHeight(getContext());
        mScreenWidth = UiUtils.getScreenWidth(getContext());
        mXvisibleNum = mXdots.length > mDefXMaxNum ? mDefXMaxNum : mXdots.length;
        mXinterval = (mScreenWidth - getLeft() - mLeftTextWidth) / mXvisibleNum;
        mIsInitDataSuc = true;
        mYOffset = getYMaxTextHeight() / 2;
        mLastHorLineY = (float) ((mYinterval * mYvisibleNum) + mYOffset);
        mYMaxValue = mYdots.get(mYdots.size() - 1);
        mCategoryNum = mCategoryList != null ? mCategoryList.size() + 1 : 1;//这里加1是因为有第一栏被日期占用了
        mChartHeight = getYMaxTextHeight() + (mYvisibleNum + mCategoryNum) * mYinterval + getXMaxTextHeight();
        mDotWidth = UiUtils.dip2px(getContext(), 7);
        mYDotMaps = new HashMap<>();
        for (int x = 0, size = mXdots.length; x <= size; x++) {
            if (x >= 1) {
                String tempText = mXdots[x - 1];
                mYDotMaps.put(tempText, (float) (mXinterval * x + mLeftTextWidth - mXinterval / 2));
            }
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isCanDraw() || !mIsInitDataSuc) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        int widthParentMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthParentMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightParentMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightParentMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        int resultWidthSize = 0;
        int resultHeightSize = 0;
        int resultWidthMode = MeasureSpec.EXACTLY;//用来对childView进行计算的
        int resultHeightMode = MeasureSpec.EXACTLY;
        int paddingWidth = getPaddingLeft() + getPaddingRight();
        int paddingHeight = getPaddingTop() + getPaddingBottom();
        ViewGroup.LayoutParams thisLp = getLayoutParams();
        switch (widthParentMeasureMode) {
            //父类不加限制给子类
            case MeasureSpec.UNSPECIFIED:
                //这个代表在布局写死了宽度
                if (thisLp.width > 0) {
                    resultWidthSize = thisLp.width;
                    resultWidthMode = MeasureSpec.EXACTLY;
                } else {
                    resultWidthSize = (int) (mLeftTextWidth + mXinterval * mXdots.length);
                    resultWidthMode = MeasureSpec.UNSPECIFIED;
                }
                break;
            case MeasureSpec.AT_MOST:
                //这个代表在布局写死了宽度
                if (thisLp.width > 0) {
                    resultWidthSize = thisLp.width;
                    resultWidthMode = MeasureSpec.EXACTLY;
                } else if (thisLp.width == ViewGroup.LayoutParams.MATCH_PARENT) {
                    resultWidthSize = Math.max(0, widthParentMeasureSize - paddingWidth);
                    resultWidthMode = MeasureSpec.AT_MOST;
                } else if (thisLp.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
                    resultWidthSize = (int) (mLeftTextWidth + mXinterval * mXdots.length);
                    resultWidthMode = MeasureSpec.AT_MOST;
                }
                break;
            case MeasureSpec.EXACTLY:
                //这个代表在布局写死了宽度
                if (thisLp.width > 0) {
                    resultWidthSize = Math.min(widthParentMeasureSize, thisLp.width);
                    resultWidthMode = MeasureSpec.EXACTLY;
                } else if (thisLp.width == ViewGroup.LayoutParams.MATCH_PARENT) {
                    resultWidthSize = widthParentMeasureSize;
                    resultWidthMode = MeasureSpec.EXACTLY;
                } else if (thisLp.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
                    resultWidthSize = (int) (mLeftTextWidth + mXinterval * mXdots.length);
                    resultWidthMode = MeasureSpec.AT_MOST;
                }
                break;
        }


        switch (heightParentMeasureMode) {
            //父view不加限制
            case MeasureSpec.UNSPECIFIED:
                //这个代表在布局写死了宽度
                if (thisLp.height > 0) {
                    resultHeightSize = thisLp.height;
                    resultHeightMode = MeasureSpec.EXACTLY;
                } else {
                    resultHeightSize = (int) (mChartHeight);
                    resultHeightMode = MeasureSpec.UNSPECIFIED;
                }
                break;
            case MeasureSpec.AT_MOST:
                if (thisLp.height > 0) {
                    resultHeightSize = heightParentMeasureSize;
                    resultHeightMode = MeasureSpec.EXACTLY;
                } else if (thisLp.height == ViewGroup.LayoutParams.MATCH_PARENT) {
                    resultHeightSize = Math.max(0, heightParentMeasureSize - paddingHeight);
                    resultHeightMode = MeasureSpec.AT_MOST;
                } else if (thisLp.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                    resultHeightSize = (int) (mChartHeight);
                    resultHeightMode = MeasureSpec.UNSPECIFIED;
                }
                break;
            case MeasureSpec.EXACTLY:
                //这个代表在布局写死了宽度
                if (thisLp.height > 0) {
                    resultHeightSize = Math.min(heightParentMeasureSize, getMeasuredWidth());
                    resultHeightMode = MeasureSpec.EXACTLY;
                } else if (thisLp.width == ViewGroup.LayoutParams.MATCH_PARENT) {
                    resultHeightSize = heightParentMeasureSize;
                    resultHeightMode = MeasureSpec.EXACTLY;
                } else if (thisLp.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
                    resultHeightSize = (int) (mChartHeight);
                    resultHeightMode = MeasureSpec.AT_MOST;
                }
                break;
        }

        setMeasuredDimension(MeasureSpec.makeMeasureSpec(resultWidthSize, resultWidthMode),
                MeasureSpec.makeMeasureSpec(resultHeightSize, resultHeightMode));

    }

    /**
     * @return 每个数字代表的间隔，默认是mYdots是最大的高度
     */
    private float getIntervalPerInch() {
        return (float) (mYvisibleNum * mYinterval / mYdots.get(mYdots.size() - 1));
    }


    /**
     * @return y轴文字的最大宽度
     */
    private float getYMaxTextWidth() {
        float maxWidth = 0;
        for (double y : mYdots) {
            if (mYNumPaint.measureText(String.valueOf(y)) > maxWidth) {
                maxWidth = mYNumPaint.measureText(String.valueOf(y));
            }
        }
        return maxWidth;
    }

    /**
     * @return y轴文字的最大高度
     */
    private float getYMaxTextHeight() {
        Rect rect = new Rect();
        mYNumPaint.getTextBounds(String.valueOf(mYdots.get(0)), 0, 1, rect);
        return rect.height();
    }


    /**
     * @return x轴文字的最大高度
     */
    private float getXMaxTextHeight() {
        Rect rect = new Rect();
        mYNumPaint.getTextBounds(mXdots[0], 0, 1, rect);
        return rect.height();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (!isCanDraw() || !mIsInitDataSuc) return;

        Log.d(TAG, "onDraw: " + mCurrentX + ",mTotalScrollX:" + mTotalScrollX + ",mScroller.getCurrX():" + mScroller.getCurrX());
        if (mTotalScrollX + mScroller.getCurrX() <= mMaxLeftScrollDis) {
            mTotalScrollX = mMaxLeftScrollDis - mScroller.getCurrX();
        } else if (mTotalScrollX + mScroller.getCurrX() >= mMaxRightScrollDis) {
            mTotalScrollX = mMaxRightScrollDis - mScroller.getCurrX();
        }
        mCurrentX = mTotalScrollX + mScroller.getCurrX();


        //绘制日期那一栏x轴的灰色背景
        canvas.drawRect(0, mLastHorLineY, getWidth(), (float) (mLastHorLineY + mYinterval), mDateBGPaint);

        //绘制文案
        for (int y = 0, size = mYdots.size(); y < size; y++) {
            String tempText = String.valueOf(mYdots.get(mYdots.size() - 1 - y));
            //最后的Y轴数字绘制在横线的上方
            if (y != size - 1) {
                canvas.drawText(tempText, mLeftTextWidth - mYNumRightPadding - mYNumPaint.measureText(tempText), getYMaxTextHeight() + (float) (mYinterval * y), mYNumPaint);
            } else {
                canvas.drawText(tempText, mLeftTextWidth - mYNumRightPadding - mYNumPaint.measureText(tempText), getYMaxTextHeight() / 2 + (float) (mYinterval * y), mYNumPaint);
            }
        }

        //绘制日期的文案
        canvas.drawText("日期", mLeftTextWidth - mYNumRightPadding - mYNumPaint.measureText("日期"), (float) (mLastHorLineY + mYinterval / 2 + UiUtils.getTextHeight("日期", mYNumPaint) / 2), mYNumPaint);


        //绘制图标下面的表格的左边文字
        if (mCategoryList != null && mCategoryList.size() > 0) {
            double dotLineTop = ((int) mYinterval) >> 1;
            //绘制每列的柱状图,里面可以包含多个柱状
            for (int barIndex = 0, barSize = mCategoryList.size(); barIndex < barSize; barIndex++) {
                float tempLineStartX = ((int) mLeftTextWidth) >> 3;
                CategoryVo tempCategoryVo = mCategoryList.get(barIndex);
                float mTableStartYOffset = (float) (mLastHorLineY + mYinterval + mYinterval * barIndex);
                String barValue = tempCategoryVo.getCategoryName();
                if (barIndex == 0) {
                    mLeftDotPaint.setColor(blue);
                }

                if (barIndex == 1) {
                    mLeftDotPaint.setColor(yellow);
                }
                if (barIndex == 2) {
                    mLeftDotPaint.setColor(red);
                }


                //绘制柱状图下面的文案
                StaticLayout sl = new StaticLayout(barValue, mYTableTextPaint, (int) UiUtils.getTextWidth("佣金", mYTableTextPaint), Layout.Alignment.ALIGN_NORMAL, 1, 1, false);
                canvas.save();
                float textOffset = mTableStartYOffset;
                if (barValue.length() <= 2) {
                    textOffset = (float) (mTableStartYOffset + mYinterval / 2 - UiUtils.getTextHeight(barValue, mYTableTextPaint) / 2);
                } else if (barValue.length() <= 4) {
                    textOffset = (float) (mTableStartYOffset + mYinterval / 2 - UiUtils.getTextHeight(barValue, mYTableTextPaint));
                }
                canvas.translate(mLeftTextWidth - mYNumRightPadding - UiUtils.getTextWidth("佣金", mYTableTextPaint), textOffset);
                sl.draw(canvas);
                canvas.restore();

                canvas.drawLine(tempLineStartX, (float) (mTableStartYOffset + dotLineTop), tempLineStartX + mDotWidth, (float) (mTableStartYOffset + dotLineTop), mLeftDotPaint);


            }
        }

        //绘制横线
        for (int y = 0, size = mYdots.size() + mCategoryNum; y < size; y++) {
            if (y < mYdots.size()) {
                canvas.drawLine(mLeftTextWidth, (float) (mYinterval * y) + mYOffset, (float) (mLeftTextWidth + mXdots.length * mXinterval) + mCurrentX, (float) (mYinterval * y + mYOffset), mXlinePaint);
            } else {
                canvas.drawLine(0, (float) (mYinterval * y) + mYOffset, (float) (mLeftTextWidth + mXdots.length * mXinterval) + mCurrentX, (float) (mYinterval * y + mYOffset), mXlinePaint);
            }
        }


        int clipRestoreCount = canvas.save();
        canvas.clipRect(mContentRect);


        //绘制竖线
        for (int x = 0, size = mXdots.length; x <= size; x++) {
            canvas.drawLine((float) (mLeftTextWidth + mXinterval * x) + mCurrentX, mYOffset, (float) (mLeftTextWidth + mXinterval * x + mCurrentX), (float) (mYinterval * (mYvisibleNum + mCategoryNum) + mYOffset), mXlinePaint);
            if (x >= 1) {
                String tempText = mXdots[x - 1];
                //绘制日期，x轴上的数据
                if (tempText.length() <= 6) {
                    canvas.drawText(tempText, (float) (mLeftTextWidth + mXinterval * (2 * x - 1) / 2 - mYNumPaint.measureText(tempText) / 2 + mCurrentX), (float) (mLastHorLineY + getYMaxTextHeight() / 2 + mYinterval / 2), mYNumPaint);
                } else {
                    String firstLineText = tempText.substring(0, 6);
                    String secondLineText = tempText.substring(6);
                    //在这里减5是为了实现文字的上下行的间距
                    canvas.drawText(firstLineText, (float) (mLeftTextWidth + mXinterval * (2 * x - 1) / 2 - mYNumPaint.measureText(firstLineText) / 2 + mCurrentX), (float) (mLastHorLineY + mYinterval / 2 - 5), mYNumPaint);
                    canvas.drawText(secondLineText, (float) (mLeftTextWidth + mXinterval * (2 * x - 1) / 2 - mYNumPaint.measureText(secondLineText) / 2 + mCurrentX), (float) (mLastHorLineY + getYMaxTextHeight() + mYinterval / 2 + 5), mYNumPaint);
                }
            }
        }


        //绘制叠加条状图
        if (mCategoryList != null) {
            //计算每条柱状图的宽度
            double tempBarWidth = (((int) mXinterval) >> 1 >> 1);
            mBarPaint.setStrokeWidth((float) tempBarWidth);
            mBarPaint.setColor(ContextCompat.getColor(getContext(), R.color.app_20c90a));
            double leftPadding = (mXinterval - tempBarWidth) / 2;
            //绘制竖线
            for (int x = 0, size = mXdots.length; x <= size; x++) {
                //绘制每列的柱状图,里面可以包含多个柱状
                float mStartDrawY = mLastHorLineY;
                float mEndDrawX;
                for (int barIndex = 0, barSize = mCategoryList.size(); barIndex < barSize; barIndex++) {
                    float tempBarStartX = (float) (mLeftTextWidth + mXinterval * x + leftPadding + mCurrentX + tempBarWidth / 2);
                    CategoryVo tempCategoryVo = mCategoryList.get(barIndex);
                    String barValue = tempCategoryVo.getCategoryValueList().get(x);
                    if (barIndex == 0) {
                        mBarPaint.setColor(blue);
                    }

                    if (barIndex == 1) {
                        mBarPaint.setColor(yellow);
                    }
                    if (barIndex == 2) {
                        mBarPaint.setColor(red);
                    }

                    mEndDrawX = (float) (mStartDrawY - Double.valueOf(barValue) * getIntervalPerInch());
                    canvas.drawLine(tempBarStartX, mStartDrawY, tempBarStartX, mEndDrawX, mBarPaint);
                    mStartDrawY = mEndDrawX;

                    //绘制柱状图下面的文案,就是下面表格的文案
                    canvas.drawText(barValue,
                            (float) (mXinterval * x + mXinterval / 2 - UiUtils.getTextWidth(barValue, mTableValuePaint) / 2 + mLeftTextWidth + mCurrentX),
                            (float) ((mLastHorLineY + mYinterval) + barIndex * mYinterval + mYinterval / 2 + UiUtils.getTextHeight(barValue, mTableValuePaint) / 2),
                            mTableValuePaint);

                }
            }

        }


        //绘制折线
        if (mListDisDots != null && mListDisDots.size() > 0) {
            for (List<DotVo> dotVos : mListDisDots) {
                final Path path = new Path();
                for (int i = 0, size = dotVos.size(); i < size; i++) {

                    DotVo preDotVo = i - 1 >= 0 ? dotVos.get(i - 1) : null;
                    DotVo tempDotVo = dotVos.get(i);
//                    DotVo nextDotVo = i + 1 <= size - 1 ? dotVos.get(i + 1) : null;
                    float preX = preDotVo != null ? mYDotMaps.get(preDotVo.getX()) + mCurrentX : 0;
                    float preY = preDotVo != null ? (float) ((mYMaxValue - preDotVo.getY()) * getIntervalPerInch() + mYOffset) : 0;
                    mDots[0] = mYDotMaps.get(tempDotVo.getX()) + mCurrentX;
                    mDots[1] = (float) ((mYMaxValue - tempDotVo.getY()) * getIntervalPerInch() + mYOffset);
//                    mDots[2] = nextDotVo != null ? mYDotMaps.get(nextDotVo.getX()) + mCurrentX : 0;
//                    mDots[3] = nextDotVo != null ? (float) ((mYMaxValue - nextDotVo.getY()) * getIntervalPerInch() + mYOffset) : 0;
//                    canvas.drawLines(mDots, mLinePaint);
                    canvas.drawCircle(mDots[0], mDots[1], 10, mPointPaint);
                    if (i == 0) {// 第一条为二阶贝塞尔
                        path.moveTo(mDots[0], mDots[1] + (mLastHorLineY - mDots[1]) * mPhaseY);// 起点
                    } else {
                        float cpx = preX
                                + (mDots[0] - preX) / 2.0f;
                        path.cubicTo(
                                cpx, preY + (mLastHorLineY - preY) * mPhaseY,
                                cpx, mDots[1] + (mLastHorLineY - mDots[1]) * mPhaseY,
                                mDots[0], mDots[1] + (mLastHorLineY - mDots[1]) * mPhaseY);
                    }


                }
                canvas.drawPath(path, mLinePaint);
            }

        }


        // Removes clipping rectangle
        canvas.restoreToCount(clipRestoreCount);

        // Draws chart container
        canvas.drawRect(mContentRect, mCanvasBGPaint);
    }


//    public void initPath() {
//        mLinePath = new Path();
//        if (mListDisDots != null) {
//            for (int i = 0; i < mListDisDots.size() - 1; i++) {
//                DotVo tempDotVo = mListDisDots.get(i);
//                DotVo nextDotVo = mListDisDots.get(i + 1);
//                float startX = mYDotMaps.get(tempDotVo.getX()) == null ? 0 : mYDotMaps.get(tempDotVo.getX());
//                float startY = (float) ((mYdots.get(mYdots.size() - 1) - tempDotVo.getY()) * getIntervalPerInch());
//                float stopX = mYDotMaps.get(nextDotVo.getX()) == null ? 0 : mYDotMaps.get(nextDotVo.getX());
//                float stopY = (float) ((mYdots.get(mYdots.size() - 1) - nextDotVo.getY()) * getIntervalPerInch());
//                Log.d(TAG, "第: " + i + "个坐标点" + "startX:" + startX + "  startY:" + startY + "  stopX:" + stopX + "  stopY:" + stopY);
//                if (i == 0) {
//                    mLinePath.moveTo(startX, startY);
//                    mLineDrawPath.moveTo(startX, startY);
//                }
//
//                mLinePath.lineTo(stopX, stopY);
//            }
//        }
//        mPathMeasure = new PathMeasure(mLinePath, false);
//        mLineLength = mPathMeasure.getLength();
//
//    }

    /**
     * 给外部进行调用的重新绘制的方法。
     */
    public void reDraw() throws YCoordinateException {
        if (!isCanDraw()) return;
        initData();
//        initPath();

        if (isAnimationOpen)
            startPathAnim(2000);
        else
            invalidate();
    }

    // 开启路径动画
    private void startPathAnim(long duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 0);
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPhaseY = (Float) animation.getAnimatedValue();
                ViewCompat.postInvalidateOnAnimation(OverLayBarChartLine.this);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isDrawOver = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();

    }

    public OverLayBarChartLine setAnimationOpen(boolean paramAnimationOpen) {
        isAnimationOpen = paramAnimationOpen;
        return this;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (!isAnimationOpen || isDrawOver) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mLastDownX = event.getX();
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (Math.abs(mLastDownX - event.getX()) > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
            return mGestureDetector.onTouchEvent(event);
        }
        getParent().requestDisallowInterceptTouchEvent(false);
        return super.onTouchEvent(event);
    }

    /**
     * @return 是否需要绘制图表
     */
    private boolean isCanDraw() {
        return !(mYdots == null || mYdots.size() == 0 || mXdots == null || mXdots.length == 0 || mListDisDots == null);
    }
}
