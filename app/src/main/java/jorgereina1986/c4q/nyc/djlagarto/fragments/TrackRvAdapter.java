package jorgereina1986.c4q.nyc.djlagarto.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.R;
import jorgereina1986.c4q.nyc.djlagarto.model.tracks.Track;

/**
 * Created by jorgereina on 5/10/18.
 */

public class TrackRvAdapter extends RecyclerView.Adapter<TrackRvAdapter.TrackViewHolder>{

    private Context context;
    private List<Track> trackList;

    public TrackRvAdapter(Context context, List<Track> trackList) {
        this.context = context;
        this.trackList = trackList;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.track_row, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {

        Track track = trackList.get(position);

        Picasso.with(context).load(track.getArtworkUrl()).into(holder.trackImage);
        holder.trackTitle.setText(track.getTitle());
        holder.trackDuration.setText(convertTime(track.getDuration()));
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder{

        TextView trackTitle;
        ImageView trackImage;
        TextView trackDuration;

        public TrackViewHolder(View itemView) {
            super(itemView);

            trackTitle = itemView.findViewById(R.id.track_title);
            trackImage = itemView.findViewById(R.id.track_cover);
            trackDuration = itemView.findViewById(R.id.track_duration);
        }
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
