
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

    @SerializedName("im:collection")
    @Expose(serialize = false)
    private ImCollection imCollection;

    @SerializedName("im:price")
    @Expose(serialize = false)
    private ImPrice imPrice;

    @SerializedName("im:contentType")
    @Expose(serialize = false)
    private ImContentType__ imContentType;

    @SerializedName("rights")
    @Expose(serialize = false)
    private Rights rights;

    @SerializedName("title")
    @Expose(serialize = false)
    private Title title;

    @SerializedName("link")
    @Expose(serialize = false)
    private List<Link_> link = new ArrayList<Link_>();

    @SerializedName("id")
    @Expose(serialize = false)
    private Id id;

    @SerializedName("im:artist")
    @Expose
    private ImArtist imArtist;

    @SerializedName("category")
    @Expose(serialize = false)
    private Category category;

    @SerializedName("im:releaseDate")
    @Expose(serialize = false)
    private ImReleaseDate imReleaseDate;

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

    public ImCollection getImCollection() {
        return imCollection;
    }

    public void setImCollection(ImCollection imCollection) {
        this.imCollection = imCollection;
    }

    public ImPrice getImPrice() {
        return imPrice;
    }

    public void setImPrice(ImPrice imPrice) {
        this.imPrice = imPrice;
    }

    public ImContentType__ getImContentType() {
        return imContentType;
    }

    public void setImContentType(ImContentType__ imContentType) {
        this.imContentType = imContentType;
    }

    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public List<Link_> getLink() {
        return link;
    }

    public void setLink(List<Link_> link) {
        this.link = link;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public ImArtist getImArtist() {
        return imArtist;
    }

    public void setImArtist(ImArtist imArtist) {
        this.imArtist = imArtist;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ImReleaseDate getImReleaseDate() {
        return imReleaseDate;
    }

    public void setImReleaseDate(ImReleaseDate imReleaseDate) {
        this.imReleaseDate = imReleaseDate;
    }
}
