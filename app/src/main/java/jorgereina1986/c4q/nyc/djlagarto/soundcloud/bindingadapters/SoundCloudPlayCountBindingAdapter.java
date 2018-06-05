package jorgereina1986.c4q.nyc.djlagarto.soundcloud.bindingadapters;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Created by jorgereina on 5/16/18.
 */

public class SoundCloudPlayCountBindingAdapter {

    @BindingAdapter("playcount")
    public static void setPlayCount(TextView textView, long plays) {
        textView.setText(convertPlayCount(plays));
    }

    private static String convertPlayCount(long plays) {
        String shortNmOfPLays;
        if (plays >= 1000000) {
            shortNmOfPLays = String.format("%.1fM", plays / 1000000.0);
        } else if (plays >= 1000 && plays < 1000000) {
            shortNmOfPLays = String.format("%.1fK", plays / 1000.0);
        } else {
            shortNmOfPLays = String.valueOf(plays);
        }
        return shortNmOfPLays;
    }
}
