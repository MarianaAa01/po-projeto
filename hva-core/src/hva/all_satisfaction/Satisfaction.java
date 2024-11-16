package hva.all_satisfaction;

import java.io.Serializable;

import hva.habitats.Animal;
import hva.work.Keeper;
import hva.work.Vet;

public abstract class Satisfaction implements Serializable{

    public double calculateKeeperSatisfaction(Keeper k){return 0.0;}

    public double calculateAnimalSatisfaction(Animal a){return 0.0;}

    public double calculateVetSatisfaction(Vet v){return 0.0;}

}
