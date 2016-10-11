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
import jorgereina1986.c4q.nyc.djlagarto.model.chart.Entry;

public class ChartAdapter extends BaseAdapter {

    private Context context;
    private List<Entry> entryList;

    public ChartAdapter(Context context, List<Entry> entryList) {
        this.context = context;
        this.entryList = entryList;
    }

    @Override
    public int getCount() {
        return entryList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entry entry = entryList.get(position);

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.chart_row, parent, false);
            holder.albumCover = (ImageView) convertView.findViewById(R.id.album_iv);
            holder.titleHolder = (TextView) convertView.findViewById(R.id.title_tv);
            holder.artistHolder = (TextView) convertView.findViewById(R.id.artist_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleHolder.setText(entry.getImName().getLabel());
        holder.artistHolder.setText(entry.getImArtist().getLabel());
        Picasso.with(context)
                .load(entry.getImImage().get(0).getLabel())
                .into(holder.albumCover);

        return convertView;
    }

    static class ViewHolder {

        TextView titleHolder;
        TextView artistHolder;
        ImageView albumCover;
    }
}
