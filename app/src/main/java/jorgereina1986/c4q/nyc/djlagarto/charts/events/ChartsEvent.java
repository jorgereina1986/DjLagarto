package jorgereina1986.c4q.nyc.djlagarto.charts.events;

import jorgereina1986.c4q.nyc.djlagarto.charts.models.Entry;

public class ChartsEvent {

    private Entry entry;

    public ChartsEvent(Entry entry) {
        this.entry = entry;
    }

    public Entry getEntry() {
        return entry;
    }
}
