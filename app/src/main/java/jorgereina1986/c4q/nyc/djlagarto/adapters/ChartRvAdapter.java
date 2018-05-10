package jorgereina1986.c4q.nyc.djlagarto.adapters;

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
import jorgereina1986.c4q.nyc.djlagarto.model.chart.Entry;

/**
 * Created by jorgereina on 5/10/18.
 */

public class ChartRvAdapter extends RecyclerView.Adapter<ChartRvAdapter.ChartViewHolder> {

    private Context context;
    private List<Entry> entryList;

    public ChartRvAdapter(Context context, List<Entry> entryList) {
        this.context = context;
        this.entryList = entryList;
    }

    @NonNull
    @Override
    public ChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chart_row, parent, false);
        return new ChartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartViewHolder holder, int position) {

        Entry entry = entryList.get(position);

        Picasso.with(context).load(entry.getImImage().get(0).getLabel()).into(holder.albumCover);
        holder.titleHolder.setText(entry.getImName().getLabel());
        holder.artistHolder.setText(entry.getImArtist().getLabel());

    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public class ChartViewHolder extends RecyclerView.ViewHolder {

        private TextView titleHolder;
        private TextView artistHolder;
        private ImageView albumCover;

        public ChartViewHolder(View itemView) {
            super(itemView);

            titleHolder = itemView.findViewById(R.id.title_tv);
            artistHolder = itemView.findViewById(R.id.artist_tv);
            albumCover = itemView.findViewById(R.id.album_iv);
        }
    }
}
