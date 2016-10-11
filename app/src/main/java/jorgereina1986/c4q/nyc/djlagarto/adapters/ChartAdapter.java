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

    Context context;
    List<Entry> entryList;

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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entry entry = new Entry();

        ViewHolder vh;

        if (convertView == null){

            vh = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.chart_row, parent, false);
            vh.albumCover = (ImageView) convertView.findViewById(R.id.album_iv);
            vh.titleHolder = (TextView) convertView.findViewById(R.id.title_tv);
            vh.artistHolder = (TextView) convertView.findViewById(R.id.artist_tv);

        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.titleHolder.setText(entry.getImName().getLabel());
        vh.artistHolder.setText(entry.getImArtist().getLabel());
        Picasso.with(context).load(entry.getImImage().get(0).getLabel()).into(vh.albumCover);
        return convertView;
    }

    static class ViewHolder{

        TextView titleHolder;
        TextView artistHolder;
        ImageView albumCover;
    }
}
