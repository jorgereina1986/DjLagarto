package jorgereina1986.c4q.nyc.djlagarto;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.Util;

public class Main2Activity extends AppCompatActivity {

    private static final int BUFFER_SEGMENT_SIZE = 64 * 1024;
    private static final int BUFFER_SEGMENT_COUNT = 256;
    private ExoPlayer exoPlayer, exoPlayer2;
    private Button play, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        MediaCodecAudioTrackRenderer audioRenderer;

        exoPlayer = ExoPlayer.Factory.newInstance(1);
        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);

        // String with the url of the radio you want to play
        String url = "https://api.soundcloud.com/tracks/56535658/stream&client_id=abfb0d3714540e9c63a814ac3dd63ec6";
        Uri radioUri = Uri.parse(url);
        // Settings for exoPlayer
//        Allocator allocator = new DefaultAllocator(BUFFER_SEGMENT_SIZE);
//        String userAgent = Util.getUserAgent(getApplicationContext(), "ExoPlayerDemo");
//        DataSource dataSource = new DefaultUriDataSource(getApplicationContext(), null, userAgent);
//        ExtractorSampleSource sampleSource = new ExtractorSampleSource(radioUri, dataSource, allocator, BUFFER_SEGMENT_SIZE * BUFFER_SEGMENT_COUNT);
//        SampleSource sampleSource1 = new SingleSampleSource(radioUri, dataSource, null);
//        audioRenderer = new MediaCodecAudioTrackRenderer());
//        // Prepare ExoPlayer
//        exoPlayer.prepare(audioRenderer);

        // otro

        exoPlayer2 = ExoPlayer.Factory.newInstance(1,1000, 5000);

        Allocator allocator = new DefaultAllocator(BUFFER_SEGMENT_SIZE);

        String userAgent = Util.getUserAgent(getApplicationContext(),"ExoPlayerDemo");

        DataSource dataSource = new DefaultUriDataSource(this, null, userAgent);

        ExtractorSampleSource sampleSource = new ExtractorSampleSource(
                radioUri, dataSource, allocator, BUFFER_SEGMENT_COUNT * BUFFER_SEGMENT_SIZE);

        MediaCodecAudioTrackRenderer audioRenderer1 = new MediaCodecAudioTrackRenderer(
                sampleSource, MediaCodecSelector.DEFAULT);

        exoPlayer2.prepare(audioRenderer1);
        exoPlayer2.setPlayWhenReady(true);


    }
}
