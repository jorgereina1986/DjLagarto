package jorgereina1986.c4q.nyc.djlagarto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.model.Track;

/**
 * Created by c4q-jorgereina on 12/15/15.
 */
public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<Track> tracks;
    public CustomAdapter(Context context, List<Track> tracks) {
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
        Track track = tracks.get(position);
        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
            holder.trackHolder = (TextView) convertView.findViewById(R.id.track_title);
            //holder.imageHolder = (ImageView) convertView.findViewById(R.id.album_cover);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        //setting track name to row view
        holder.trackHolder.setText(track.getTitle());

        //setting image to row view
        //Picasso.with(context).load(result.getArtworkUrl100()).resize(80, 80).into(holder.imageHolder);

        return convertView;
    }

    static class ViewHolder{
        TextView trackHolder;
        ImageView imageHolder;
    }
}
