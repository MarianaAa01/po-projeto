package hva.seasons;

public interface SeasonState {
    SeasonState nextSeason();
    String toString();
}