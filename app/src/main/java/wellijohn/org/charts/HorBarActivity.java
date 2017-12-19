package wellijohn.org.charts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import wellijohn.org.varchart.exception.YCoordinateException;
import wellijohn.org.varchart.hor_bar_with_line_chart.ChartLine;
import wellijohn.org.varchart.vo.CategoryVo;
import wellijohn.org.varchart.vo.DotVo;

/**
 * @author: JiangWeiwei
 * @time: 2017/12/6-10:00
 * @email:
 * @desc:
 */
public class HorBarActivity extends AppCompatActivity {

    //显示的坐标点
    private ChartLine mChartline;

    //多条折线的坐标点
    private List<List<DotVo>> mMulListDisDots;

    //y轴的点
    //x轴的点
    private String[] mXdots = new String[]{"08/18"
            , "08/19",
            "08/20", "08/21", "08/22", "08/23", "08/24",
            "08/25", "08/26", "08/27", "08/28", "08/29", "09/01", "09/02", "09/23",
            "10/25", "10/26", "10/27", "10/28", "10/29", "10/01", "10/02", "10/23至08/18",
    };

    private double mMax = 44;

    private Random rand = new Random();

    private List<CategoryVo> mCategoryList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hor_bar);

        initView();
        initMulTestData();
        initCategoryList();
        try {
            mChartline.setYAxisMaxValue(mMax).setXdots(mXdots).setAnimationOpen(true).setListDisDots(mMulListDisDots).
                    setCategoryList(mCategoryList).reDraw();
        } catch (YCoordinateException e) {
            Log.d("MainActivity", "onCreate: ");
            e.printStackTrace();
        }

    }


    /**
     * 柱状图的数据，是一个list，一个CategoryVo，就是一列中增加一个柱状
     * CategoryVo：{
     * <p>
     * 卡券类目的名称
     * private String categoryName;
     * <p>
     * 每个卡券类目的值
     * private List<String> categoryValueList;
     * }
     */
    private void initCategoryList() {
        mCategoryList = new ArrayList<>();
        mCategoryList.add(new CategoryVo());
        mCategoryList.add(new CategoryVo());
        mCategoryList.add(new CategoryVo());
    }


    /**
     * 初始化曲线图,private List<List<DotVo>> mMulListDisDots;
     * List<DotVo>>就是一条曲线图，
     */
    private void initMulTestData() {
        mMulListDisDots = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ArrayList<DotVo> temp = new ArrayList();
            DotVo tempDotVo = new DotVo("08/18", rand.nextInt((int) mMax));
            temp.add(tempDotVo);
            DotVo tempDotVo1 = new DotVo("08/19", rand.nextInt((int) mMax));
            temp.add(tempDotVo1);
            DotVo tempDotVo2 = new DotVo("08/20", rand.nextInt((int) mMax));
            temp.add(tempDotVo2);
            DotVo tempDotVo3 = new DotVo("08/21", rand.nextInt((int) mMax));
            temp.add(tempDotVo3);
            DotVo tempDotVo4 = new DotVo("08/22", rand.nextInt((int) mMax));
            temp.add(tempDotVo4);
            DotVo tempDotVo5 = new DotVo("08/23", rand.nextInt((int) mMax));
            temp.add(tempDotVo5);
            DotVo tempDotVo6 = new DotVo("09/02", rand.nextInt((int) mMax));
            temp.add(tempDotVo6);

            mMulListDisDots.add(temp);
        }


    }


    private void initView() {
        mChartline = findViewById(R.id.chartline);
    }
}
