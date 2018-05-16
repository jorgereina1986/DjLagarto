package jorgereina1986.c4q.nyc.djlagarto;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Created by jorgereina on 5/16/18.
 */

public class SoundCloudDurationBindingAdapter {

    @BindingAdapter("bind:duration")
    public static void setDuration(TextView textView, long time) {
            textView.setText(convertTime(time));
    }

    private static String convertTime(long millis) {
        StringBuilder sb = new StringBuilder();

        long hours = millis / (1000 * 60 * 60);
        long minutes = (millis % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = ((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000;

        sb.append(String.format("%02d", hours))
                .append(":")
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return sb.toString();
    }
}
