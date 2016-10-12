package jorgereina1986.c4q.nyc.djlagarto.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.R;
import jorgereina1986.c4q.nyc.djlagarto.adapters.ChartAdapter;
import jorgereina1986.c4q.nyc.djlagarto.model.chart.ChartResponse;
import jorgereina1986.c4q.nyc.djlagarto.model.chart.Entry;
import jorgereina1986.c4q.nyc.djlagarto.retrofit.ChartsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChartsFragment extends Fragment {

    private static final String TAG = "Charts Activity --->";
    private static final String BASE_URL = "https://itunes.apple.com";

    private ChartAdapter chartAdapter;
    private List<Entry> entryList;
    private Context context;
    private ListView chartLv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.chart_fragment, container, false);
        chartLv = (ListView) rootView.findViewById(R.id.chart_list_view);
        entryList = new ArrayList<>();
        return rootView;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        networkRequest();
    }

    private void networkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChartsApi service = retrofit.create(ChartsApi.class);

        Call<ChartResponse> chartResponseCall = service.chartResponse();

        chartResponseCall.enqueue(new Callback<ChartResponse>() {
            @Override
            public void onResponse(Call<ChartResponse> call, Response<ChartResponse> response) {

                Log.d(TAG, response.body().getFeed().getEntry().get(0).getImName().getLabel()+"");
                entryList = response.body().getFeed().getEntry();
                chartAdapter = new ChartAdapter(getContext(), entryList);
                chartLv.setAdapter(chartAdapter);

            }

            @Override
            public void onFailure(Call<ChartResponse> call, Throwable t) {

            }
        });
    }
}
