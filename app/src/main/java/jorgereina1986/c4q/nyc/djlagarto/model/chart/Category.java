
package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("attributes")
    @Expose
    private Attributes__________ attributes;

    /**
     * 
     * @return
     *     The attributes
     */
    public Attributes__________ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes__________ attributes) {
        this.attributes = attributes;
    }

}
