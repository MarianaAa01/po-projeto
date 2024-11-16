package hva.seasons;

import java.io.Serializable; 

public class SeasonMachine implements Serializable {
    private SeasonState _currentSeason;

    public SeasonMachine() {
        _currentSeason = new Spring();
    }

    public String getCurrentSeason() {
        return _currentSeason.toString();
    }

    public void nextSeason() {
        _currentSeason = _currentSeason.nextSeason();
    }
}
