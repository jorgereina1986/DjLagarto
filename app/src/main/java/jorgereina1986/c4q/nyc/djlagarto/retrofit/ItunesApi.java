package jorgereina1986.c4q.nyc.djlagarto.retrofit;

import jorgereina1986.c4q.nyc.djlagarto.model.chart.ChartResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ItunesApi {

    //https://itunes.apple.com/us/rss/topsongs/limit=10/explicit=true/json
    @GET("/us/rss/topsongs/limit=10/explicit=true/json")
    Call<ChartResponse> listCharts();

}
