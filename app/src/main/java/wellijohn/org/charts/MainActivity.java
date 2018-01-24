package wellijohn.org.charts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import wellijohn.org.charts.bean.ChildBean;

public class MainActivity extends AppCompatActivity {

    private Button mButtonPie;
    private Button mButtonBeiz;
    private Button mButtonHorBar;
    private Button mButtonOverlayBar;
    private Button mButtonLine;

    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mButtonPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PercentActivity.class);
                ChildBean childBean = new ChildBean();
                childBean.setChildName("WelliJohn");
                childBean.setPrice(99.9);
                childBean.setName("JianMin");
                intent.putExtra("key",childBean);
                startActivity(intent);
            }
        });
        mButtonBeiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BeizCurveActivity.class));
            }
        });
        mButtonHorBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HorBarActivity.class));
            }
        });
        mButtonOverlayBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OverlayBarActivity.class));
            }
        });
        mButtonLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LineActivity.class));
            }
        });


    }


    private void initView() {
        mButtonPie = (Button) findViewById(R.id.button_pie);
        mButtonBeiz = (Button) findViewById(R.id.button_beiz);
        mButtonHorBar = (Button) findViewById(R.id.button_hor_bar);
        mButtonOverlayBar = (Button) findViewById(R.id.button_overlay_bar);
        mButtonLine = (Button) findViewById(R.id.button_line);
    }
}
