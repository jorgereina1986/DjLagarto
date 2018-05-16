package jorgereina1986.c4q.nyc.djlagarto.soundcloud.network;

import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.soundcloud.models.Track;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SoundCloudApi {
    //https://api.soundcloud.com/users/1920278/tracks?client_id=
    @GET("users/1920278/tracks")
    Call<List<Track>> trackResponse(@Query("client_id") String clientId);
}
