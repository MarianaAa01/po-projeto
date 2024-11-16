package hva.all_satisfaction;

import hva.habitats.Habitat;
import hva.work.Keeper;
import hva.trees.Tree;
import java.lang.Math;


public class KeeperSatisfaction extends Satisfaction{

    public double cleaning_effort(Tree t) {
        return t.getBaseDifficulty()*t.getSeasonalEffort()*Math.log(t.getAge());
    }

    public double work_in_habitat(Habitat h) {
        double sum = 0;
        for(Tree t : h.getTrees()) {
            sum += cleaning_effort(t);
        }

        return h.getArea()+ 3* h.getPopulation() + sum;
    }

    public double work(Keeper k) {
        double sum = 0;
        for (Habitat h : k.getHabitats()) {
            sum += work_in_habitat(h)/h.getNWorkers();
        }

        return sum;
    }

    @Override
    public double calculateKeeperSatisfaction(Keeper k) {
        return 300 - work(k);
    }



    /* Métodos:
    public int keeper_satisfaction(Keeper k){}

    public int work(Keeper k){}

    public int habitat_work(Habitat h){}

    public int area(Habitat h){}

    public int population(Habitat h){}

    public int cleaning_work(Trees T){}
    */

    /*______________________________________PARA O TEMPLATE METHOD_______________________________________
    @Override
    protected double calculateKeeperSatisfaction() {
        Implementa o cálculo específico da satisfação do veterinário
        return 90.0;
    }

     */
}
