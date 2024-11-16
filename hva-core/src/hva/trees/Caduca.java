package hva.trees;

public class Caduca extends Tree {
    
    private int[] _seasonalEffort = {0, 1, 2, 5};

    public Caduca(String id, String name, int age, String season_origin, int base_difficulty){
        super(id, name, age, season_origin, base_difficulty);
    }

    @Override
    public int getSeasonalEffort() {
        if (getCurrentSeason().equals("WINTER")) {return _seasonalEffort[0];}
        else if (getCurrentSeason().equals("SPRING")) {return _seasonalEffort[1];}
        else if (getCurrentSeason().equals("SUMMER")) {return _seasonalEffort[2];}
        else {return _seasonalEffort[3];}
    }

    @Override
    public String getbiologicalCycle(String season){
        if (season.equals("WINTER")) {return "SEMFOLHAS";}
        else if (season.equals("SPRING")) {return "GERARFOLHAS";}
        else if (season.equals("SUMMER")) {return "COMFOLHAS";}
        else {return "LARGARFOLHAS";}
    }

    @Override
    public String toString() {
        return "ÁRVORE" + "|"
                + super.getId() + "|"
                + super.getName() + "|"
                + super.getAge() + "|"
                + super.getBaseDifficulty() + "|"
                + "CADUCA" + "|"
                + getbiologicalCycle(getCurrentSeason());
    }

}
