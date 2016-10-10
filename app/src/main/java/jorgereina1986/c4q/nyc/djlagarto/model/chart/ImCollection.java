
package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImCollection {

    @SerializedName("im:name")
    @Expose
    private ImName_ imName;
    @SerializedName("link")
    @Expose
    private Link link;
    @SerializedName("im:contentType")
    @Expose
    private ImContentType imContentType;

    /**
     * 
     * @return
     *     The imName
     */
    public ImName_ getImName() {
        return imName;
    }

    /**
     * 
     * @param imName
     *     The im:name
     */
    public void setImName(ImName_ imName) {
        this.imName = imName;
    }

    /**
     * 
     * @return
     *     The link
     */
    public Link getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(Link link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The imContentType
     */
    public ImContentType getImContentType() {
        return imContentType;
    }

    /**
     * 
     * @param imContentType
     *     The im:contentType
     */
    public void setImContentType(ImContentType imContentType) {
        this.imContentType = imContentType;
    }

}
