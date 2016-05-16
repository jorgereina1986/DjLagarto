package jorgereina1986.c4q.nyc.djlagarto;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.model.Track;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String CLIENT_ID = "abfb0d3714540e9c63a814ac3dd63ec6";
    private ListView mlistview;
    private List<Track> resultList;
    private CustomAdapter adapter;
    private MediaPlayer mediaPlayer;
    private Context context;
    //private String url = "https://api.soundcloud.com/tracks/1920278/stream";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mlistview = (ListView) findViewById(R.id.tracks_list);
        resultList = new ArrayList<>();

        new MovieTask().execute("https://api.soundcloud.com/users/1920278/tracks?client_id=abfb0d3714540e9c63a814ac3dd63ec6");

//        retrofitConnection();
    }


//    public void retrofitConnection(){
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.soundcloud.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        SoundcloudAPI soundcloudApi = retrofit.create(SoundcloudAPI.class);
//
//        final Call<List<TrackResponse>> call = soundcloudApi.tracks(CLIENT_ID);
//        call.enqueue(new Callback<List<TrackResponse>>() {
//            @Override
//            public void onResponse(Response<List<TrackResponse>> response, Retrofit retrofit) {
//                //Log.d(TAG, "response: " + response.isSuccess());
//
//                List<Track> tracks = response.body().get(0).getTracks();
//                Log.d(TAG, "first: " + tracks.toString());
//
////                load(tracks);
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.e(TAG, "error: " + t);
//            }
//        });
//
//    }

    private void load(List<Track> tracks) {
        resultList.clear();
        resultList.addAll(tracks);
        adapter.notifyDataSetChanged();
    }

    public class MovieTask extends AsyncTask<String,Void, List<Track> > {


        @Override
        protected List<Track> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line ="";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONArray array = new JSONArray(finalJson);

                List<Track> trackList = new ArrayList<>();

                for(int i=0; i<array.length(); i++) {
                    JSONObject finalObject = array.getJSONObject(i);

                    Track track = new Track();
                    track.setTitle(finalObject.getString("title"));
                    track.setImageUrl(finalObject.getString("artwork_url"));


                    // adding the final object in the list
                    trackList.add(track);
                }
                return trackList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return  null;
        }

        @Override
        protected void onPostExecute(final List<Track> result) {
            super.onPostExecute(result);
            if(result != null) {
                adapter = new CustomAdapter(getApplicationContext(), result);
                mlistview.setAdapter(adapter);

//                lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Movie movieModel = result.get(position);
//                        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
//                        intent.putExtra("title", movieModel.getTitle());
//                        startActivity(intent);
//                        Toast.makeText(getApplicationContext(), "You clicked on "+ movieModel.getTitle(), Toast.LENGTH_SHORT).show();
//                    }
//                });
            } else {
                Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
