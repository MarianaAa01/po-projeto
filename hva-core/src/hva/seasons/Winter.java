package hva.seasons;

import java.io.Serializable;

public class Winter implements SeasonState, Serializable {
    
    @Override
    public SeasonState nextSeason() {
        return new Spring();
    }

    @Override
    public String toString() {
        return "WINTER";
    }
}