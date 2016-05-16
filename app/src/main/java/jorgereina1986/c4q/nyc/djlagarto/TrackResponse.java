package jorgereina1986.c4q.nyc.djlagarto;

import java.util.List;

import jorgereina1986.c4q.nyc.djlagarto.model.Track;

/**
 * Created by c4q-jorgereina on 12/15/15.
 */
public class TrackResponse {
    private int resultCount;
    private List<Track> tracks;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
