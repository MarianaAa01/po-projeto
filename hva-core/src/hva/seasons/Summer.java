package hva.seasons;

import java.io.Serializable;

public class Summer implements SeasonState, Serializable {
        
        @Override
        public SeasonState nextSeason() {
            return new Autumn();
        }
    
        @Override
        public String toString() {
            return "SUMMER";
        }
}