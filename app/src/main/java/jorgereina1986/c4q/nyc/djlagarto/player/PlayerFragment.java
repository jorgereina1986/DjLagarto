package jorgereina1986.c4q.nyc.djlagarto.player;

import android.databinding.DataBindingUtil;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import jorgereina1986.c4q.nyc.djlagarto.BuildConfig;
import jorgereina1986.c4q.nyc.djlagarto.R;
import jorgereina1986.c4q.nyc.djlagarto.charts.events.ChartsEvent;
import jorgereina1986.c4q.nyc.djlagarto.databinding.FragmentPlayerBinding;
import jorgereina1986.c4q.nyc.djlagarto.soundcloud.events.SoundCloudEvent;

public class PlayerFragment extends android.app.Fragment {

    private static final String TAG = "PlayerFragment";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;

    private FragmentPlayerBinding binding;
    private MediaPlayer mediaPlayer;
    private Timer timer;


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false);
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prepMediaPlayer();
        playPauseClickListener();
    }

    private void prepMediaPlayer() {

        // Media Player setup
        AudioAttributes audioAttributes = new AudioAttributes
                .Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(audioAttributes);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                togglePlayPause();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                binding.mediaPlayerPlayPauseIv.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            }
        });
    }

    private void playPauseClickListener() {

        // Play/Pause Button
        binding.mediaPlayerPlayPauseIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlayPause();
            }
        });
    }

    private void togglePlayPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            binding.mediaPlayerPlayPauseIv.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        } else {
            mediaPlayer.start();
            binding.mediaPlayerPlayPauseIv.setImageResource(R.drawable.ic_pause_black_24dp);
        }
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

    private void displayCurrentPosition() {
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
                                binding.timeTv.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        binding.timeTv.setText(mediaPlayer.getCurrentPosition());
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

    //TODO: Implement this
    private void addSeekBar() {


//        seekBar.setMax(mediaPlayer.getDuration());
        binding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

    @Subscribe
    public void onSoundCloudEvent(SoundCloudEvent event) {

        String title = event.getTrack().getTitle();
        String artUrl = event.getTrack().getArtworkUrl();
        String streamUrl = event.getTrack().getStreamUrl();

        addTrackInfoToPlayer(title, artUrl, streamUrl);

        addSeekBar();
    }

    @Subscribe
    public void onChartsEvent(ChartsEvent event) {

        String title = event.getEntry().getTitle().getLabel();
        String artUrl = event.getEntry().getArtwork().get(2).getLabel();
        String streamUrl = event.getEntry().getLink().get(1).getAttributes().getHref();
        String durationS = event.getEntry().getLink().get(1).getDuration().getLabel();
        int duration = Integer.parseInt(durationS);

        addTrackInfoToPlayer(title, artUrl, streamUrl);

        addSeekBar();
    }

    private void addTrackInfoToPlayer(String title, String artUrl, String streamUrl) {

        binding.currentTrackTv.setText(title);
        Picasso.with(getActivity()).load(artUrl).into(binding.currentTrackIv);
        displayCurrentPosition();

        if (mediaPlayer.isPlaying() && mediaPlayer != null || !mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.start();
        }

        try {
            mediaPlayer.setDataSource(streamUrl + "?client_id=" + CLIENT_ID);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Error: " + e, e);

        } catch (IllegalStateException e) {
            e.printStackTrace();
            Log.e(TAG, "Error: " + e, e);
        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
