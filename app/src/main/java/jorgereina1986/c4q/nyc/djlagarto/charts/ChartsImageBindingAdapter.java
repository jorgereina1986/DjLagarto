package jorgereina1986.c4q.nyc.djlagarto.charts;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by jorgereina on 5/16/18.
 */

public class ChartsImageBindingAdapter {

    @BindingAdapter({"bind:artwork"})
    public static void loadImage(ImageView imageView, String url) {
        if (url!="") {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }
}
