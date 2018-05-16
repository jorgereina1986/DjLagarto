package jorgereina1986.c4q.nyc.djlagarto.charts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.databinding.ChartRowBinding;
import jorgereina1986.c4q.nyc.djlagarto.charts.models.Entry;

/**
 * Created by jorgereina on 5/10/18.
 */

public class ChartRvAdapter extends RecyclerView.Adapter<ChartRvAdapter.ChartViewHolder> {

    private Context context;
    private List<Entry> entryList;
    private ListItemClickListener onClickListener;

    public ChartRvAdapter(Context context, List<Entry> entryList, ListItemClickListener listener) {
        this.context = context;
        this.entryList = entryList;
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public ChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ChartRowBinding binding = ChartRowBinding.inflate(inflater, parent, false);
        return new ChartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartViewHolder holder, int position) {

        Entry entry = entryList.get(position);

        holder.binding.setEntry(entry);
        holder.binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public class ChartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ChartRowBinding binding;

        public ChartViewHolder(ChartRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            onClickListener.onListItemClicked(clickedPosition);
        }
    }

    public interface ListItemClickListener {
        void onListItemClicked(int clickedItemIndex);
    }
}
