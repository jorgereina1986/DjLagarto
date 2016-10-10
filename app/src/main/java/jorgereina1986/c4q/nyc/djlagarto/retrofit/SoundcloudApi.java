package jorgereina1986.c4q.nyc.djlagarto.retrofit;

import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.model.tracks.TrackResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SoundcloudApi {
    //https://api.soundcloud.com/users/1920278/tracks?client_id=
    @GET("users/1920278/tracks")
    Call<List<TrackResponse>> trackResponse(@Query("client_id") String clientId);
}
