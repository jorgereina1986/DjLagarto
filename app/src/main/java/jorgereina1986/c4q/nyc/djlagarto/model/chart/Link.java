package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("attributes")
    private Attributes attributes;

    @SerializedName("im:duration")
    private Duration duration;

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}