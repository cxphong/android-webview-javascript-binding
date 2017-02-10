package chroma.fiot.chroma;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import fr.rolandl.carousel.CarouselItem;

/**
 * Created by caoxuanphong on 2/10/17.
 */

public class ColorItem extends CarouselItem<ImageView> {
    ImageView image;

    public ColorItem(Context context) {
        super(context, R.layout.color);
    }

    @Override
    public void extractView(View view) {
        image = (ImageView) view.findViewById(R.id.img_color);
    }

    @Override
    public void update(ImageView arg0) {
    }
}
