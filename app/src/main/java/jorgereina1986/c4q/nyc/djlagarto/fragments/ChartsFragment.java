package jorgereina1986.c4q.nyc.djlagarto.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.R;
import jorgereina1986.c4q.nyc.djlagarto.adapters.ChartRvAdapter;
import jorgereina1986.c4q.nyc.djlagarto.databinding.ChartFragmentBinding;
import jorgereina1986.c4q.nyc.djlagarto.model.chart.ChartResponse;
import jorgereina1986.c4q.nyc.djlagarto.model.chart.Entry;
import jorgereina1986.c4q.nyc.djlagarto.retrofit.ChartsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChartsFragment extends Fragment implements ChartRvAdapter.ListItemClickListener {

    private static final String TAG = "jorge";
    private static final String BASE_URL = "https://itunes.apple.com";

    private ChartFragmentBinding binding;
    private RecyclerView chartRv;
    private ChartRvAdapter adapter;
    private List<Entry> entryList = new ArrayList<>();
    private PlayerCommunicator playerCommunicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.chart_fragment, container, false);
        View view = binding.getRoot();
        chartRv = view.findViewById(R.id.chart_rv);
        adapter = new ChartRvAdapter(getContext(), entryList, this);
        chartRv.setLayoutManager(new LinearLayoutManager(getContext()));
        chartRv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playerCommunicator = (PlayerCommunicator) getActivity();
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
        String title = entry.getImName().getLabel();
        String album = entry.getImImage().get(2).getLabel();
        String url = entry.getLink().get(1).getAttributes().getHref();
        String durationS = entry.getLink().get(1).getImDuration().getLabel();
        int duration = Integer.parseInt(durationS);

        playerCommunicator.updatePlayer(title, album, url, duration);
    }
}
