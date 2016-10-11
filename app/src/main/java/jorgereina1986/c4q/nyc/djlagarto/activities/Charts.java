package jorgereina1986.c4q.nyc.djlagarto.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

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

public class Charts extends AppCompatActivity {

    private static final String TAG = "Charts Activity --->";
    private static final String BASE_URL = "https://itunes.apple.com";

    private ListView topSongsLv;
    private ChartAdapter chartAdapter;
    private List<Entry> entryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        initViews();
        networkRequest();
    }

    private void initViews() {
        topSongsLv = (ListView) findViewById(R.id.chart_lv);
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
                chartAdapter = new ChartAdapter(getApplicationContext(), entryList);
                topSongsLv.setAdapter(chartAdapter);

            }

            @Override
            public void onFailure(Call<ChartResponse> call, Throwable t) {

            }
        });
    }

}
