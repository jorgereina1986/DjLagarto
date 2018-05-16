package jorgereina1986.c4q.nyc.djlagarto.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.databinding.SoundcloudRowBinding;
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
        LayoutInflater inflater = LayoutInflater.from(context);
        SoundcloudRowBinding binding = SoundcloudRowBinding.inflate(inflater, parent, false);
        return new TrackViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        Track track = trackList.get(position);
        holder.binding.setTrack(track);
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SoundcloudRowBinding binding;

        public TrackViewHolder(SoundcloudRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.executePendingBindings();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            listener.onTrackSelectedListener(clickedPosition);
        }
    }

    public interface TrackSelectedListener {
        void onTrackSelectedListener(int clickedItemIndex);
    }
}
