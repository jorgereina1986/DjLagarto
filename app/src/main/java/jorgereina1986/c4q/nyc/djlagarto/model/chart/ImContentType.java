
package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImContentType {

    @SerializedName("im:contentType")
    @Expose
    private ImContentType_ imContentType;
    @SerializedName("attributes")
    @Expose
    private Attributes___ attributes;

    /**
     * 
     * @return
     *     The imContentType
     */
    public ImContentType_ getImContentType() {
        return imContentType;
    }

    /**
     * 
     * @param imContentType
     *     The im:contentType
     */
    public void setImContentType(ImContentType_ imContentType) {
        this.imContentType = imContentType;
    }

    /**
     * 
     * @return
     *     The attributes
     */
    public Attributes___ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes___ attributes) {
        this.attributes = attributes;
    }

}
