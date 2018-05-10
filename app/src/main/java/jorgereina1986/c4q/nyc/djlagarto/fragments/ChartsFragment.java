package jorgereina1986.c4q.nyc.djlagarto.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.R;
import jorgereina1986.c4q.nyc.djlagarto.adapters.ChartRvAdapter;
import jorgereina1986.c4q.nyc.djlagarto.model.chart.ChartResponse;
import jorgereina1986.c4q.nyc.djlagarto.model.chart.Entry;
import jorgereina1986.c4q.nyc.djlagarto.retrofit.ItunesApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChartsFragment extends Fragment {

    private static final String TAG = "jorge";
    private static final String BASE_URL = "https://itunes.apple.com";


    private RecyclerView chartRv;
    private ChartRvAdapter adapter;
    private List<Entry> entryList =  new ArrayList<>();
    private PlayerCommunicator playerCommunicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.chart_fragment, container, false);
        chartRv = rootView.findViewById(R.id.chart_rv);
        adapter = new ChartRvAdapter(getContext(), entryList);
        chartRv.setLayoutManager(new LinearLayoutManager(getContext()));
        chartRv.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playerCommunicator = (PlayerCommunicator) getActivity();

        networkRequest();
    }

    private void networkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ItunesApi service = retrofit.create(ItunesApi.class);

        Call<ChartResponse> chartResponseCall = service.listCharts();

        chartResponseCall.enqueue(new Callback<ChartResponse>() {
            @Override
            public void onResponse(Call<ChartResponse> call, Response<ChartResponse> response) {

                entryList.addAll(response.body().getFeed().getEntry());
                adapter.notifyDataSetChanged();
//                onTrackClicked();
            }

            @Override
            public void onFailure(Call<ChartResponse> call, Throwable t) {

            }
        });
    }

//    private void onTrackClicked() {
//
//        chartLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Entry entry = entryList.get(position);
//
//                String title = entry.getImName().getLabel();
//                String album = entry.getImImage().get(2).getLabel();
//                String url = entry.getLink().get(1).getAttributes().getHref();
//                String durationS = entry.getLink().get(1).getImDuration().getLabel();
//                int duration = Integer.parseInt(durationS);
//
//                playerCommunicator.updatePlayer(title, album, url, duration);
//            }
//        });
    }

