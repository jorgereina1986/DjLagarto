package jorgereina1986.c4q.nyc.djlagarto.soundcloud.events;

import jorgereina1986.c4q.nyc.djlagarto.soundcloud.models.Track;

public class SoundCloudEvent {

    private Track track;

    public SoundCloudEvent(Track track) {
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }
}
