package jorgereina1986.c4q.nyc.djlagarto.player;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import jorgereina1986.c4q.nyc.djlagarto.BuildConfig;
import jorgereina1986.c4q.nyc.djlagarto.R;

public class PlayerFragment extends android.app.Fragment {

    private static final String TAG = "PlayerFragment";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private TextView selectedTrackTitle;
    private ImageView selectedTrackImage;
    private MediaPlayer mediaPlayer;
    private ImageView playerControl;
    private TextView time;
    private Timer timer;
    private SeekBar seekBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_player, container, false);
        selectedTrackTitle = rootView.findViewById(R.id.current_track_tv1);
        selectedTrackImage = rootView.findViewById(R.id.current_track_iv1);
        playerControl = rootView.findViewById(R.id.player_control_iv1);
        time = rootView.findViewById(R.id.time_tv);
        seekBar =  rootView.findViewById(R.id.seekbar);
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
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                togglePlayPause();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playerControl.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }
        });
    }

    private void clickListeners() {

        // Play/Pause Button
        playerControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
    }

    private void togglePlayPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playerControl.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        } else {
            mediaPlayer.start();
            playerControl.setImageResource(R.drawable.ic_pause_black_24dp);
        }
    }

    public void addDataToPlayer(String title, String albumCover, String trackUrl, int duration){

        selectedTrackTitle.setText(title);
        Picasso.with(getActivity()).load(albumCover).into(selectedTrackImage);
        displayCurrentPosition();

        if (mediaPlayer.isPlaying() && mediaPlayer != null || !mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.start();
        }


        try {
            mediaPlayer.setDataSource(trackUrl + "?client_id=" + CLIENT_ID);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Error: " + e, e);

        } catch (IllegalStateException e) {
            e.printStackTrace();
            Log.e(TAG, "Error: " + e, e);
        }

        addSeekBar();


    }

    private String convertTime(long millis) {
        StringBuffer buf = new StringBuffer();

        long hours = millis / (1000 * 60 * 60);
        long minutes = (millis % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = ((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000;

        buf
                .append(String.format("%02d", hours))
                .append(":")
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buf.toString();
    }

    private void displayCurrentPosition(){
        if (mediaPlayer != null) {
            mediaPlayer.start();
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                                time.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        time.setText(mediaPlayer.getCurrentPosition());
                                    }
                                });
                            } else {
                                timer.cancel();
                                timer.purge();
                            }
                        }
                    });
                }
            }, 0, 1000);
        }
    }

    private void addSeekBar(){


//        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
