package jorgereina1986.c4q.nyc.djlagarto.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.BuildConfig;
import jorgereina1986.c4q.nyc.djlagarto.R;
import jorgereina1986.c4q.nyc.djlagarto.model.soundcloud.Track;
import jorgereina1986.c4q.nyc.djlagarto.retrofit.SoundCloudApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoundCloudFragment extends android.support.v4.app.Fragment implements SoundCloudAdapter.TrackSelectedListener {

    private static final String TAG = "MainActivity";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private static final String BASE_URL = "https://api.soundcloud.com/";

    private RecyclerView soundCloudRv;
    private List<Track> resultList = new ArrayList<>();
    private SoundCloudAdapter rvAdapter;
    private PlayerCommunicator playerCommunicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.soundcloud_fragment, container, false);
        rvAdapter = new SoundCloudAdapter(getContext(), resultList, this);
        soundCloudRv = rootView.findViewById(R.id.soundcloud_rv);
        soundCloudRv.setAdapter(rvAdapter);
        soundCloudRv.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playerCommunicator = (PlayerCommunicator) getActivity();
        getSoundCloudTracks();
    }

    private void getSoundCloudTracks() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SoundCloudApi service = retrofit.create(SoundCloudApi.class);

        Call<List<Track>> tracksCall = service.trackResponse(CLIENT_ID);
        tracksCall.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {

                Log.d(TAG, "response: " + response.body().get(0).getTitle());
                Log.d(TAG, "onResponse: " + response.body().get(0).getArtworkUrl());
                resultList.addAll(response.body());
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTrackSelectedListener(int clickedItemIndex) {
        Track track = resultList.get(clickedItemIndex);
        playerCommunicator.updatePlayer(track.getTitle(), track.getArtworkUrl(), track.getStreamUrl(), track.getDuration());
    }
}
