
package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("title")
    private String title;

    @SerializedName("href")
    private String href;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
