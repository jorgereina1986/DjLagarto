package jorgereina1986.c4q.nyc.djlagarto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.R;
import jorgereina1986.c4q.nyc.djlagarto.model.tracks.TrackResponse;

/**
 * Created by c4q-jorgereina on 12/15/15.
 */
public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<TrackResponse> tracks;

    public CustomAdapter(Context context, List<TrackResponse> tracks) {
        this.context = context;
        this.tracks = tracks;
    }

    @Override
    public int getCount() {
        return tracks.size();
    }

    @Override
    public Object getItem(int position) {
        return tracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TrackResponse track = tracks.get(position);

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
            holder.trackHolder = (TextView) convertView.findViewById(R.id.track_title);
            holder.imageHolder = (ImageView) convertView.findViewById(R.id.track_cover);
            holder.durationHolder = (TextView) convertView.findViewById(R.id.track_duration);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //setting track info to row view
        holder.trackHolder.setText(track.getTitle());
        holder.durationHolder.setText(convertTime(track.getDuration()));
        Picasso.with(context)
                .load(track.getArtworkUrl())
                .placeholder(R.drawable.placeholder_album)
                .fit()
                .centerCrop()
                .into(holder.imageHolder);

        return convertView;
    }

    static class ViewHolder {
        TextView trackHolder;
        ImageView imageHolder;
        TextView durationHolder;
    }

    // converting time
    private String convertTime(long millis) {
        StringBuffer buf = new StringBuffer();

        long hours = millis / (1000 * 60 * 60);
        long minutes = (millis % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = ((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000;

        buf
                .append(String.format("%02d", hours))
                .append(":")
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buf.toString();
    }

}
