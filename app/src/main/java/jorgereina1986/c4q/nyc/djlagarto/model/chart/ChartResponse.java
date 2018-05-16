
package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.SerializedName;


public class ChartResponse {

    @SerializedName("feed")
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

}
