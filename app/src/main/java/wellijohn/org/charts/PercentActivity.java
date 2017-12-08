package wellijohn.org.charts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import wellijohn.org.varchart.circlepercentchart.CirclePercentChart;
import wellijohn.org.varchart.circlepercentchart.exception.PercentOverFlowException;
import wellijohn.org.varchart.circlepercentchart.vo.ArcVo;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/22-14:34
 * @email:
 * @desc:
 */
public class PercentActivity extends AppCompatActivity {
    private CirclePercentChart mCirclePercentChart;

    private List<ArcVo> mListDisArcs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);
        initView();
        initTestData();

        try {
            mCirclePercentChart.setDisArcList(mListDisArcs).reDraw();
        } catch (PercentOverFlowException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        mCirclePercentChart = findViewById(R.id.circle_percent_chart);
    }


    private void initTestData() {
        mListDisArcs = new ArrayList<>();
        ArcVo tempDotVo = new ArcVo(Color.RED, .42f);
        mListDisArcs.add(tempDotVo);
        ArcVo tempDotVo1 = new ArcVo(Color.MAGENTA, .38f);
        mListDisArcs.add(tempDotVo1);
//        ArcVo tempDotVo2 = new ArcVo(Color.GREEN, .09f);
//        mListDisArcs.add(tempDotVo2);
        ArcVo tempDotVo3 = new ArcVo(Color.BLACK, .1f);
        mListDisArcs.add(tempDotVo3);
        ArcVo tempDotVo4 = new ArcVo(Color.BLUE, .1f);
        mListDisArcs.add(tempDotVo4);


    }
}
