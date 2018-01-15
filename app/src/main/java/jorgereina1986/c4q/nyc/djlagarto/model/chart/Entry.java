
package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Entry {

    @SerializedName("im:name")
    @Expose
    private ImName imName;

    @SerializedName("im:image")
    @Expose
    private List<ImImage> imImage = new ArrayList<ImImage>();

    @SerializedName("im:artist")
    @Expose
    private ImArtist imArtist;

    @SerializedName("link")
    @Expose(serialize = false)
    private List<Link> link = new ArrayList<Link>();

    public ImName getImName() {
        return imName;
    }

    public void setImName(ImName imName) {
        this.imName = imName;
    }

    public List<ImImage> getImImage() {
        return imImage;
    }

    public void setImImage(List<ImImage> imImage) {
        this.imImage = imImage;
    }

    public ImArtist getImArtist() {
        return imArtist;
    }

    public void setImArtist(ImArtist imArtist) {
        this.imArtist = imArtist;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }
}
