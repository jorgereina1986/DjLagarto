package jorgereina1986.c4q.nyc.djlagarto;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.model.TrackResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private static final String BASE_URL = "https://api.soundcloud.com/";
    private ListView mListView;
    private List<TrackResponse> resultList;
    private CustomAdapter adapter;
    private Context context;
    private Toolbar mToolbar;
    private Toolbar mMediaPlayerToolbar;
    private TextView mSelectedTrackTitle;
    private ImageView mSelectedTrackImage;
    private MediaPlayer mMediaPlayer;
    private ImageView mPlayerControl;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Media Player setup
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                togglePlayPause();
            }
        });

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayerControl.setImageResource(R.drawable.ic_play_circle_outline_white_18dp);
            }
        });

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mMediaPlayerToolbar = (Toolbar) findViewById(R.id.toolbar2);
        mSelectedTrackTitle = (TextView) findViewById(R.id.current_track_tv);
        mSelectedTrackImage = (ImageView) findViewById(R.id.current_track_iv);
        mPlayerControl = (ImageView) findViewById(R.id.player_control_iv);

        // Play/Pause Button
        mPlayerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });

        mListView = (ListView) findViewById(R.id.tracks_list);
        resultList = new ArrayList<>();

        networkRequest();

    }

    private void togglePlayPause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPlayerControl.setImageResource(R.drawable.ic_play_circle_outline_white_18dp);
        } else {
            mMediaPlayer.start();
            mPlayerControl.setImageResource(R.drawable.ic_pause_circle_outline_white_18dp);
        }
    }

    private void networkRequest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SoundcloudApi service = retrofit.create(SoundcloudApi.class);

        Call<List<TrackResponse>> tracksCall = service.trackResponse(CLIENT_ID);
        tracksCall.enqueue(new Callback<List<TrackResponse>>() {
            @Override
            public void onResponse(Call<List<TrackResponse>> call, Response<List<TrackResponse>> response) {

                Log.d(TAG, "response: " + response.body().get(1).getTitle());
                resultList = response.body();
                adapter = new CustomAdapter(getApplicationContext(), resultList);
                mListView.setAdapter(adapter);

                onTrackClicked();
            }

            @Override
            public void onFailure(Call<List<TrackResponse>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t+"", Toast.LENGTH_SHORT);
            }
        });
    }

    private void onTrackClicked() {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TrackResponse track = resultList.get(position);

                // Loading info to Media Player
                mSelectedTrackTitle.setText(track.getTitle());
                Picasso.with(context).load(track.getArtworkUrl()).into(mSelectedTrackImage);
                Toast.makeText(getApplicationContext(), "You clicked on " + track.getStreamUrl(), Toast.LENGTH_SHORT).show();

                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    mMediaPlayer.reset();
                    mMediaPlayer.start();
                }

                try {
                    mMediaPlayer.setDataSource(track.getStreamUrl() + "?client_id=" + CLIENT_ID);
                    mMediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
    }


}
