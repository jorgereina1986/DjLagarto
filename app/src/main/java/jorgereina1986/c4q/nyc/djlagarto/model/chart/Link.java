package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("im:duration")
    @Expose
    private ImDuration imDuration;

    /**
     *
     * @return
     *     The attributes
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     *
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    /**
     *
     * @return
     *     The imDuration
     */
    public ImDuration getImDuration() {
        return imDuration;
    }

    /**
     *
     * @param imDuration
     *     The im:duration
     */
    public void setImDuration(ImDuration imDuration) {
        this.imDuration = imDuration;
    }

}