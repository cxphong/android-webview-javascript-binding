package chroma.fiot.chroma;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import ru.igla.widget.AutoSizeTextView;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoSizeTextView progress = (AutoSizeTextView) findViewById(R.id.tv_progress);
        AutoSizeTextView battery = (AutoSizeTextView) findViewById(R.id.tv_battery);

        TextView chooseMode = (TextView) findViewById(R.id.tv_choose_mode);
        TextView flow = (TextView) findViewById(R.id.tv_flow);
        TextView fill = (TextView) findViewById(R.id.tv_fill);
        TextView burst = (TextView) findViewById(R.id.tv_burst);
        TextView brightness = (TextView) findViewById(R.id.tv_brightness);

        Typeface tf = Typeface.createFromAsset(getAssets(),"Sansation_Regular.ttf");
        progress.setTypeface(tf,Typeface.NORMAL);
        battery.setTypeface(tf,Typeface.NORMAL);


        chooseMode.setTypeface(tf, Typeface.NORMAL);
        flow.setTypeface(tf, Typeface.NORMAL);
        fill.setTypeface(tf, Typeface.NORMAL);
        burst.setTypeface(tf, Typeface.NORMAL);
        brightness.setTypeface(tf, Typeface.NORMAL);
    }

}

