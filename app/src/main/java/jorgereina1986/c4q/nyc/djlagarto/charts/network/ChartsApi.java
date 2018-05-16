package jorgereina1986.c4q.nyc.djlagarto.charts.network;

import jorgereina1986.c4q.nyc.djlagarto.charts.models.ChartResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ChartsApi {
    //https://itunes.apple.com/us/rss/topsongs/limit=10/explicit=true/json
    //https://rss.itunes.apple.com/api/v1/us/itunes-music/hot-tracks/all/10/explicit.json
    @GET("/us/rss/topsongs/limit=10/explicit=true/json")
    Call<ChartResponse> listCharts();

}
