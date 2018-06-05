package jorgereina1986.c4q.nyc.djlagarto.charts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.R;
import jorgereina1986.c4q.nyc.djlagarto.charts.events.ChartsEvent;
import jorgereina1986.c4q.nyc.djlagarto.databinding.ChartFragmentBinding;
import jorgereina1986.c4q.nyc.djlagarto.charts.models.ChartResponse;
import jorgereina1986.c4q.nyc.djlagarto.charts.models.Entry;
import jorgereina1986.c4q.nyc.djlagarto.charts.network.ChartsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChartsFragment extends Fragment implements ChartRvAdapter.ListItemClickListener {

    private static final String TAG = "jorge";
    private static final String BASE_URL = "https://itunes.apple.com";

    private ChartFragmentBinding binding;
    private ChartRvAdapter adapter;
    private List<Entry> entryList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.chart_fragment, container, false);
        View view = binding.getRoot();
        adapter = new ChartRvAdapter(getContext(), entryList, this);
        binding.chartRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.chartRv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getChartTracks();
    }

    private void getChartTracks() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChartsApi service = retrofit.create(ChartsApi.class);

        Call<ChartResponse> chartResponseCall = service.listCharts();
        chartResponseCall.enqueue(new Callback<ChartResponse>() {
            @Override
            public void onResponse(Call<ChartResponse> call, Response<ChartResponse> response) {
                entryList.addAll(response.body().getFeed().getEntry());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ChartResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {

        Entry entry = entryList.get(clickedItemIndex);
        String title = entry.getTitle().getLabel();
        String album = entry.getArtwork().get(2).getLabel();
        String url = entry.getLink().get(1).getAttributes().getHref();
        String durationS = entry.getLink().get(1).getDuration().getLabel();
        int duration = Integer.parseInt(durationS);


        EventBus.getDefault().post(new ChartsEvent(entry));
    }
}
