package chroma.fiot.chroma;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.suke.widget.SwitchButton;

import java.util.ArrayList;
import java.util.List;

import fr.rolandl.carousel.Carousel;
import fr.rolandl.carousel.CarouselAdapter;
import fr.rolandl.carousel.CarouselBaseAdapter;
import fr.rolandl.carousel.CarouselItem;
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

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Notification> notifications = new ArrayList<>();

    private boolean isSMSZoomOut;
    private boolean isCallZoomOut;
    private boolean isFacebookZoomOut;

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
        Button addMore = (Button) findViewById(R.id.bt_add_more);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        TextView notification = (TextView) findViewById(R.id.tv_notifications);
        TextView leftSeekbar = (TextView) findViewById(R.id.tv_0_percent);
        TextView rightSeekbar = (TextView) findViewById(R.id.tv_100_percent);

        Typeface tf = Typeface.createFromAsset(getAssets(), "Sansation_Regular.ttf");
        progress.setTypeface(tf, Typeface.NORMAL);
        battery.setTypeface(tf, Typeface.NORMAL);
        chooseMode.setTypeface(tf, Typeface.NORMAL);
        tvFlow.setTypeface(tf, Typeface.NORMAL);
        tvFill.setTypeface(tf, Typeface.NORMAL);
        tvBurst.setTypeface(tf, Typeface.NORMAL);
        brightness.setTypeface(tf, Typeface.NORMAL);
        brightnessPercent.setTypeface(tf, Typeface.NORMAL);
        addMore.setTypeface(tf, Typeface.NORMAL);
        notification.setTypeface(tf, Typeface.NORMAL);
        leftSeekbar.setTypeface(tf, Typeface.NORMAL);
        rightSeekbar.setTypeface(tf, Typeface.NORMAL);

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

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        Notification no = new Notification();
        no.imageResourceId = R.drawable.facebook;
        no.name = "Facebook";
        no.color = Color.parseColor("#ffff00");
        no.enabled = true;

        notifications.add(no);
        notifications.add(no);

        mAdapter = new MyAdapter(notifications);
        mRecyclerView.setAdapter(mAdapter);
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
        unSelectedFlow();
        ;
        unSelectedBurst();
    }

    public void clickBurst(View v) {
        Log.i(TAG, "clickBurst: ");

        selectedBurst();
        unSelectedFlow();
        unSelectedFill();
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<Notification> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public ImageView image;
            public TextView name;
            public View color;
            public com.suke.widget.SwitchButton sw;


            public View view;

            public ViewHolder(View v) {
                super(v);

                view = v;

                image = (ImageView) v.findViewById(R.id.no_image);
                name = (TextView) v.findViewById(R.id.no_name);
                color = v.findViewById(R.id.no_color);
                sw = (SwitchButton) v.findViewById(R.id.no_sw);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, (Integer) v.getTag() + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<Notification> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.notification, parent, false);

            v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mRecyclerView.getHeight() / 2));
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            Notification no = mDataset.get(position);

            holder.image.setBackgroundResource(no.imageResourceId);
            holder.name.setText(no.name);
            Typeface tf = Typeface.createFromAsset(getAssets(), "Sansation_Regular.ttf");
            holder.name.setTypeface(tf, Typeface.NORMAL);

            GradientDrawable bgShape = (GradientDrawable) holder.color.getBackground();
            bgShape.setColor(no.color);

            holder.sw.setChecked(no.enabled);

            holder.view.setTag(position);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    public void clickAddMore(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.notification_dialog);

        Typeface tf = Typeface.createFromAsset(getAssets(), "Sansation_Regular.ttf");
        ((TextView) dialog.findViewById(R.id.tv_add_notification)).setTypeface(tf, Typeface.NORMAL);
        ((TextView) dialog.findViewById(R.id.tv_choose_notification_icon)).setTypeface(tf, Typeface.NORMAL);
        ((TextView) dialog.findViewById(R.id.tv_choose_notification_color)).setTypeface(tf, Typeface.NORMAL);
        ((Button) dialog.findViewById(R.id.bt_add_notification)).setTypeface(tf, Typeface.NORMAL);
        ImageButton close = (ImageButton) dialog.findViewById(R.id.dialog_bt_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        final Animation animZoomIn, animZoomOut;
        animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_in);
        animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom_out);

        final ImageView smsImg = (ImageView) dialog.findViewById(R.id.img_sms);
        final ImageView callImg = (ImageView) dialog.findViewById(R.id.img_call);
        final ImageView facebookImg = (ImageView) dialog.findViewById(R.id.img_facebook);

        smsImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isSMSZoomOut) {
                    smsImg.startAnimation(animZoomOut);

                    if (isCallZoomOut) {
                        callImg.startAnimation(animZoomIn);

                        isCallZoomOut = !isCallZoomOut;
                    }

                    if (isFacebookZoomOut) {
                        facebookImg.startAnimation(animZoomIn);

                        isFacebookZoomOut = !isFacebookZoomOut;
                    }
                } else {
                    smsImg.startAnimation(animZoomIn);
                }

                isSMSZoomOut = !isSMSZoomOut;
            }
        });


        callImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isCallZoomOut) {
                    callImg.startAnimation(animZoomOut);

                    if (isSMSZoomOut) {
                        smsImg.startAnimation(animZoomIn);

                        isSMSZoomOut = !isSMSZoomOut;
                    }

                    if (isFacebookZoomOut) {
                        facebookImg.startAnimation(animZoomIn);

                        isFacebookZoomOut = !isFacebookZoomOut;
                    }
                } else {
                    callImg.startAnimation(animZoomIn);
                }

                isCallZoomOut = !isCallZoomOut;
            }
        });

        facebookImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isFacebookZoomOut) {
                    facebookImg.startAnimation(animZoomOut);

                    if (isCallZoomOut) {
                        callImg.startAnimation(animZoomIn);

                        isCallZoomOut = !isCallZoomOut;
                    }

                    if (isSMSZoomOut) {
                        smsImg.startAnimation(animZoomIn);

                        isSMSZoomOut = !isSMSZoomOut;
                    }

                } else {
                    facebookImg.startAnimation(animZoomIn);
                }

                isFacebookZoomOut = !isFacebookZoomOut;
            }
        });


        final Carousel carousel = (Carousel) dialog.findViewById(R.id.carousel);
        final List<ImageView> photos = new ArrayList<>();
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));
        photos.add(new ImageView(MainActivity.this));

        final CarouselAdapter adapter = new ColorAdapter(this, photos);
        carousel.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        carousel.setOnItemClickListener(new CarouselBaseAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(CarouselBaseAdapter<?> carouselBaseAdapter, View view, int position, long id) {
                carousel.scrollToChild(position);
            }

        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                isSMSZoomOut = false;
                isFacebookZoomOut = false;
                isCallZoomOut = false;
            }
        });
    }

}

class ColorAdapter extends CarouselAdapter<ImageView> {

    public ColorAdapter(Context context, List<ImageView> photos) {
        super(context, photos);
    }

    @Override
    public CarouselItem<ImageView> getCarouselItem(Context context) {
        return new ColorItem(context);
    }

}


