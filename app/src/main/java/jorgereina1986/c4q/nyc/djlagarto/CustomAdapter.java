package jorgereina1986.c4q.nyc.djlagarto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by c4q-jorgereina on 12/15/15.
 */
public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<Result> results;

    public CustomAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Result result = results.get(position);
        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder.trackHolder = (TextView) convertView.findViewById(R.id.track_title);
            //holder.imageHolder = (ImageView) convertView.findViewById(R.id.album_cover);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        //setting track name to row view
        holder.trackHolder.setText(result.getTitle());

        //setting image to row view
        //Picasso.with(context).load(result.getArtworkUrl100()).resize(80, 80).into(holder.imageHolder);

        return convertView;
    }

    static class ViewHolder{
        TextView trackHolder;
        ImageView imageHolder;
    }
}
