package jorgereina1986.c4q.nyc.djlagarto.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.BuildConfig;
import jorgereina1986.c4q.nyc.djlagarto.R;
import jorgereina1986.c4q.nyc.djlagarto.adapters.TrackAdapter;
import jorgereina1986.c4q.nyc.djlagarto.model.tracks.TrackResponse;
import jorgereina1986.c4q.nyc.djlagarto.retrofit.SoundcloudApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoundcloudFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "MainActivity";
    private static final String CLIENT_ID = BuildConfig.CLIENT_ID;
    private static final String BASE_URL = "https://api.soundcloud.com/";
    private ListView mListView;
    private List<TrackResponse> resultList;
    private TrackAdapter adapter;
//    private Toolbar mToolbar;

    PlayerCommunicator playerCommunicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.soundcloud_fragment, container, false);

        mListView = (ListView) rootView.findViewById(R.id.tracks_list1);
        resultList = new ArrayList<>();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        playerCommunicator = (PlayerCommunicator) getActivity();
        if (!isNetworkConnected(getContext())) {
            Toast.makeText(getContext(), "Network error. Please make sure you are connected to the internet", Toast.LENGTH_LONG).show();
        } else {
            networkRequest();
        }
    }


    private boolean isNetworkConnected(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    private void onTrackClicked() {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TrackResponse track = resultList.get(position);

                playerCommunicator.updatePlayer(track.getTitle(),track.getArtworkUrl(), track.getStreamUrl());


            }


        });


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
                adapter = new TrackAdapter(getContext(), resultList);
                mListView.setAdapter(adapter);

                onTrackClicked();
            }

            @Override
            public void onFailure(Call<List<TrackResponse>> call, Throwable t) {

                Toast.makeText(getContext(), t + "", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
