
package jorgereina1986.c4q.nyc.djlagarto.model.chart;

import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("label")
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
