
package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImContentType__ {

    @SerializedName("im:contentType")
    @Expose
    private ImContentType___ imContentType;
    @SerializedName("attributes")
    @Expose
    private Attributes______ attributes;

    /**
     * 
     * @return
     *     The imContentType
     */
    public ImContentType___ getImContentType() {
        return imContentType;
    }

    /**
     * 
     * @param imContentType
     *     The im:contentType
     */
    public void setImContentType(ImContentType___ imContentType) {
        this.imContentType = imContentType;
    }

    /**
     * 
     * @return
     *     The attributes
     */
    public Attributes______ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes______ attributes) {
        this.attributes = attributes;
    }

}
