package hva.trees;

import java.io.Serializable;

public class Tree implements Serializable {
    private String _id;
    private String _name;
    private int _age;
    private String _season_origin;
    private String _currentSeason;
    private int _base_difficulty;
    private int _after_season = 0;

    public Tree(String id, String name, int age, String season_origin, int base_difficulty){
        _id = id;
        _name = name;
        _age = age;
        _season_origin = season_origin;
        _base_difficulty = base_difficulty;
        _currentSeason = season_origin;
    }
    
    //getters e setters 
    public String getId(){
        return _id;
    }
    public void setId(String id){
        _id = id;
    }


    public String getName(){
        return _name;
    }
    public void setName(String name){
        _name = name;
    }

    public int getAge(){
        return _age;
    }
    public void setAge(int age){
        _age = age;
    }

    public String getSeasonOrigin(){
        return _season_origin;
    }
    public void setSeasonOrigin(String season_origin){
        _season_origin = season_origin;
    }

    public int getBaseDifficulty(){
        return _base_difficulty;
    }
    public void setBaseDifficulty(int base_difficulty){
        _base_difficulty = base_difficulty;
    }

    public String getCurrentSeason(){
        return _currentSeason;
    }

    public void setCurrentSeason(String currentSeason){
        _currentSeason = currentSeason;
    }

    public int getSeasonalEffort() {return 0;}

    public String getbiologicalCycle(String season) {return "";}

    public void incrementAge(){
        _age++;
    }

    public void advanceSeason(String currentSeason) {
        setCurrentSeason(currentSeason);
        if (_after_season == 4) {
            _after_season = 0;
            incrementAge();
        }
        else {
            _after_season++;
        }
    }
}
