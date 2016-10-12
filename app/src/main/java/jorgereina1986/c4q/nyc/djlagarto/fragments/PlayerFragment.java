package jorgereina1986.c4q.nyc.djlagarto.fragments;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import jorgereina1986.c4q.nyc.djlagarto.BuildConfig;
import jorgereina1986.c4q.nyc.djlagarto.R;

public class PlayerFragment extends android.app.Fragment {

    private static final String TAG = "PlayerFragment";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private TextView mSelectedTrackTitle;
    private ImageView mSelectedTrackImage;
    private MediaPlayer mMediaPlayer;
    private ImageView mPlayerControl;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_player, container, false);
        mSelectedTrackTitle = (TextView) rootView.findViewById(R.id.current_track_tv1);
        mSelectedTrackImage = (ImageView) rootView.findViewById(R.id.current_track_iv1);
        mPlayerControl = (ImageView) rootView.findViewById(R.id.player_control_iv1);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prepMediaPlayer();
        clickListeners();

    }

    private void prepMediaPlayer() {

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
                mPlayerControl.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }
        });
    }

    private void clickListeners() {

        // Play/Pause Button
        mPlayerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
    }

    private void togglePlayPause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPlayerControl.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        } else {
            mMediaPlayer.start();
            mPlayerControl.setImageResource(R.drawable.ic_pause_black_24dp);
        }
    }

    public void updatePlayer(String title, String albumCover, String trackUrl){

        mSelectedTrackTitle.setText(title);

        Picasso.with(getActivity()).load(albumCover).into(mSelectedTrackImage);

        if (mMediaPlayer.isPlaying() && mMediaPlayer != null || !mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.start();
        }


        try {
            mMediaPlayer.setDataSource(trackUrl + "?client_id=" + CLIENT_ID);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Error: " + e, e);

        } catch (IllegalStateException e) {
            e.printStackTrace();
            Log.e(TAG, "Error: " + e, e);
        }

    }
}
