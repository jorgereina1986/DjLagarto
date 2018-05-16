package jorgereina1986.c4q.nyc.djlagarto;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by jorgereina on 5/16/18.
 */

public class SoundCloudImageBindingAdapter {

    @BindingAdapter("bind:getArtworkUrl")
    public static void loadImage(ImageView imageView, String url) {
        if (url != "") {
            Picasso.with(imageView.getContext()).load(url).into(imageView);
        }
    }
}
