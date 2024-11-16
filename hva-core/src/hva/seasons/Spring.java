package hva.seasons;

import java.io.Serializable;

public class Spring implements SeasonState, Serializable{
    
    @Override
    public SeasonState nextSeason() {
        return new Summer();
    }

    @Override
    public String toString() {
        return "SPRING";
    }
}