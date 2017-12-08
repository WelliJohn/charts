package wellijohn.org.charts;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import wellijohn.org.varchart.beizcurve.BeizCurve;

/**
 * @author: JiangWeiwei
 * @time: 2017/11/24-9:53
 * @email:
 * @desc:
 */
public class BeizCurveActivity extends AppCompatActivity {
    private BeizCurve mBeizCurve;

    private ArrayList<Point> mListPoints;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beiz_curve);
        initView();
        initPoints();
        mBeizCurve.setPointList(mListPoints);
    }

    private void initPoints() {
        mListPoints = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i < 10; i++) {
            Point point = new Point(random.nextInt(10)+i*100, random.nextInt(100)+i%2*200);
            mListPoints.add(point);
        }
    }

    private void initView() {
        mBeizCurve = findViewById(R.id.beiz_curve);
    }
}
