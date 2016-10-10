
package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("attributes")
    @Expose
    private Attributes_ attributes;

    /**
     * 
     * @return
     *     The attributes
     */
    public Attributes_ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes_ attributes) {
        this.attributes = attributes;
    }

}
