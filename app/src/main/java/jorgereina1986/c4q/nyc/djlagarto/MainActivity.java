package jorgereina1986.c4q.nyc.djlagarto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String CLIENT_ID = "abfb0d3714540e9c63a814ac3dd63ec6";
    private ListView mlistview;
    private List<Result> resultList;
    private CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mlistview = (ListView) findViewById(R.id.tracks_list);
        resultList = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        retrofitConnection();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void retrofitConnection(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.soundcloud.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SoundcloudAPI soundcloudApi = retrofit.create(SoundcloudAPI.class);
        final Call<List<TrackResponse>> call = soundcloudApi.tracks(CLIENT_ID);
        call.enqueue(new Callback<List<TrackResponse>>() {
            @Override
            public void onResponse(Response<List<TrackResponse>> response, Retrofit retrofit) {
                Log.d(TAG, "response: " + response.isSuccess());
//                Log.d(TAG, "first: " + response.body().getResults().get(0).getTitle());

                List<Result> results = response.body().get().getResults();
                load(results);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "error: " + t);
            }
        });

    }

    private void load(List<Result> results) {
        resultList.clear();
        resultList.addAll(results);
        adapter.notifyDataSetChanged();
    }
}
