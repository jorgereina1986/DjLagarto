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
import jorgereina1986.c4q.nyc.djlagarto.model.soundcloud.Track;

/**
 * Created by jorgereina on 5/10/18.
 */

public class SoundCloudAdapter extends RecyclerView.Adapter<SoundCloudAdapter.TrackViewHolder> {

    private Context context;
    private List<Track> trackList;
    TrackSelectedListener listener;

    public SoundCloudAdapter(Context context, List<Track> trackList, TrackSelectedListener listener) {
        this.context = context;
        this.trackList = trackList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.soundcloud_row, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {

        Track track = trackList.get(position);
        Picasso.with(context).load(track.getArtworkUrl()).placeholder(R.mipmap.ic_launcher).into(holder.trackImage);
        holder.trackTitle.setText(track.getTitle());
        holder.trackDuration.setText(convertTime(track.getDuration()));
        holder.numOfPlays.setText(convertNumOfPlays(track.getPlaybackCount()));
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView trackTitle;
        private ImageView trackImage;
        private TextView trackDuration;
        private TextView numOfPlays;

        public TrackViewHolder(View itemView) {
            super(itemView);

            trackTitle = itemView.findViewById(R.id.track_title_tv);
            trackImage = itemView.findViewById(R.id.track_cover_tv);
            trackDuration = itemView.findViewById(R.id.track_duration_tv);
            numOfPlays = itemView.findViewById(R.id.num_of_plays_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            listener.onTrackSelectedListener(clickedPosition);

        }
    }

    // converting time
    private String convertTime(long millis) {
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

    private String convertNumOfPlays(long plays) {
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

    public interface TrackSelectedListener {
        void onTrackSelectedListener(int clickedItemIndex);
    }
}
