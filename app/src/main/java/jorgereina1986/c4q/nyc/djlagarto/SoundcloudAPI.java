package jorgereina1986.c4q.nyc.djlagarto;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by c4q-jorgereina on 12/15/15.
 */
//https://api.soundcloud.com/users/1920278/tracks?client_id=abfb0d3714540e9c63a814ac3dd63ec6
public interface SoundcloudAPI {
    @GET("/users/1920278/tracks")
    Call<List<TrackResponse>> tracks(@Query("client_id") String clientId);
}
