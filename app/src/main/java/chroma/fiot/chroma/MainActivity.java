package chroma.fiot.chroma;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import ru.igla.widget.AutoSizeTextView;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private TextView tvFlow;
    private View flowUnderBar;

    private TextView tvFill;
    private View fillUnderBar;

    private TextView tvBurst;
    private View burstUnderBar;

    ViewGroup.LayoutParams selectedLayoutParams;
    RelativeLayout.LayoutParams unselectedLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoSizeTextView progress = (AutoSizeTextView) findViewById(R.id.tv_progress);
        AutoSizeTextView battery = (AutoSizeTextView) findViewById(R.id.tv_battery);

        TextView chooseMode = (TextView) findViewById(R.id.tv_choose_mode);

        tvFlow = (TextView) findViewById(R.id.tv_flow);
        tvFill = (TextView) findViewById(R.id.tv_fill);
        tvBurst = (TextView) findViewById(R.id.tv_burst);
        flowUnderBar = findViewById(R.id.view_flow);
        fillUnderBar = findViewById(R.id.view_fill);
        burstUnderBar = findViewById(R.id.view_burst);

        TextView brightness = (TextView) findViewById(R.id.tv_brightness);
        final TextView brightnessPercent = (TextView) findViewById(R.id.tv_brightness_percent);
        SeekBar brightnessSeekbar = (SeekBar) findViewById(R.id.brightness_seekbar);

        Typeface tf = Typeface.createFromAsset(getAssets(),"Sansation_Regular.ttf");
        progress.setTypeface(tf,Typeface.NORMAL);
        battery.setTypeface(tf,Typeface.NORMAL);
        chooseMode.setTypeface(tf, Typeface.NORMAL);
        tvFlow.setTypeface(tf, Typeface.NORMAL);
        tvFill.setTypeface(tf, Typeface.NORMAL);
        tvBurst.setTypeface(tf, Typeface.NORMAL);
        brightness.setTypeface(tf, Typeface.NORMAL);
        brightnessPercent.setTypeface(tf, Typeface.NORMAL);

        brightnessSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    brightnessPercent.setText(progress + "%");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        unSelectedBurst();
        unSelectedFill();
        unSelectedFlow();
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private void unSelectedFlow() {
        tvFlow.setTextColor(getResources().getColor(R.color.colorText));
        flowUnderBar.setBackgroundColor(getResources().getColor(R.color.colorText));

        RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(flowUnderBar.getLayoutParams());
        newParams.addRule(RelativeLayout.BELOW, tvFlow.getId());
        newParams.topMargin = dpToPx(3);
        newParams.leftMargin = dpToPx(8);
        newParams.rightMargin = dpToPx(8);
        newParams.height = dpToPx(1);

        flowUnderBar.setLayoutParams(newParams);
    }

    private void selectedFlow() {
        tvFlow.setTextColor(Color.parseColor("#dddddd"));
        flowUnderBar.setBackgroundColor(Color.parseColor("#27C95C"));

        RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(flowUnderBar.getLayoutParams());
        newParams.addRule(RelativeLayout.BELOW, tvFlow.getId());
        newParams.topMargin = dpToPx(3);
        newParams.leftMargin = dpToPx(8);
        newParams.rightMargin = dpToPx(8);
        newParams.height = dpToPx(3);

        flowUnderBar.setLayoutParams(newParams);
    }

    private void unSelectedFill() {
        tvFill.setTextColor(getResources().getColor(R.color.colorText));
        fillUnderBar.setBackgroundColor(getResources().getColor(R.color.colorText));

        RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(fillUnderBar.getLayoutParams());
        newParams.addRule(RelativeLayout.BELOW, tvFill.getId());
        newParams.topMargin = dpToPx(3);
        newParams.leftMargin = dpToPx(8);
        newParams.rightMargin = dpToPx(8);
        newParams.height = dpToPx(1);

        fillUnderBar.setLayoutParams(newParams);
    }

    private void selectedFill() {
        tvFill.setTextColor(Color.parseColor("#dddddd"));
        fillUnderBar.setBackgroundColor(Color.parseColor("#27C95C"));

        RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(fillUnderBar.getLayoutParams());
        newParams.addRule(RelativeLayout.BELOW, tvFill.getId());
        newParams.topMargin = dpToPx(3);
        newParams.leftMargin = dpToPx(8);
        newParams.rightMargin = dpToPx(8);
        newParams.height = dpToPx(3);

        fillUnderBar.setLayoutParams(newParams);
    }

    private void unSelectedBurst() {
        tvBurst.setTextColor(getResources().getColor(R.color.colorText));
        burstUnderBar.setBackgroundColor(getResources().getColor(R.color.colorText));

        RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(burstUnderBar.getLayoutParams());
        newParams.addRule(RelativeLayout.BELOW, tvBurst.getId());
        newParams.topMargin = dpToPx(3);
        newParams.leftMargin = dpToPx(8);
        newParams.rightMargin = dpToPx(8);
        newParams.height = dpToPx(1);

        burstUnderBar.setLayoutParams(newParams);
    }

    private void selectedBurst() {
        tvBurst.setTextColor(Color.parseColor("#dddddd"));
        burstUnderBar.setBackgroundColor(Color.parseColor("#27C95C"));

        RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(burstUnderBar.getLayoutParams());
        newParams.addRule(RelativeLayout.BELOW, tvBurst.getId());
        newParams.topMargin = dpToPx(3);
        newParams.leftMargin = dpToPx(8);
        newParams.rightMargin = dpToPx(8);
        newParams.height = dpToPx(3);

        burstUnderBar.setLayoutParams(newParams);
    }

    public void clickFlow(View v) {
        Log.i(TAG, "clickFlow: ");

        selectedFlow();
        unSelectedFill();
        unSelectedBurst();
    }

    public void clickFill(View v) {
        Log.i(TAG, "clickFill: ");

        selectedFill();
        unSelectedFlow();;
        unSelectedBurst();
    }

    public void clickBurst(View v) {
        Log.i(TAG, "clickBurst: ");

        selectedBurst();
        unSelectedFlow();
        unSelectedFill();
    }

}

