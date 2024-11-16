package hva.seasons;

import java.io.Serializable;

public class Autumn implements SeasonState, Serializable { 
    @Override
    public SeasonState nextSeason() {
        return new Winter();
    }

    @Override
    public String toString() {
        return "AUTUMN";
    }
}