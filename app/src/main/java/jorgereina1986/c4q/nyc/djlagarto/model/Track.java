package jorgereina1986.c4q.nyc.djlagarto.model;

/**
 * Created by c4q-jorgereina on 5/16/16.
 */
public class Track {

    private String title;
    private String imageUrl;
    private long trackDuration;

    public long getTrackDuration() {
        return trackDuration;
    }

    public void setTrackDuration(long trackDuration) {
        this.trackDuration = trackDuration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
