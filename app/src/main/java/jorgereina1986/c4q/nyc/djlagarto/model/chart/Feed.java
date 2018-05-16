
package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Feed {

    @SerializedName("entry")
    private List<Entry> entry = new ArrayList<>();

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

}
