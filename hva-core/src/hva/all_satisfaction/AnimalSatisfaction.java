package hva.all_satisfaction;

import hva.habitats.Habitat;
import hva.habitats.Animal;


import java.util.List;

public class AnimalSatisfaction extends Satisfaction {

    
    /* Métodos:
    public int animal_satisfaction(Animal a){}

    public int adequacy(Animal a, Habitat h){}
    */

    public int sameSpecieAnimal(Animal a, Habitat h){
        int sameSpecieAnimals=0;
        for (Animal animal : h.getAllAnimals()) {
            if (animal.getSpecie().equals(a.getSpecie())){
                sameSpecieAnimals++;
            }
        }
        return sameSpecieAnimals-1;
    }

    public int differentSpecieAnimal(Animal a, Habitat h){
        List<Animal> allAnimals = h.getAllAnimals();
        return allAnimals.size()-sameSpecieAnimal(a, h)-1;
    }

    public int area(Habitat h){
        return h.getArea();
    }

    public int population(Habitat h){
        return h.getPopulation();
    }

    public int adquaecy(Animal a, Habitat h){
        return h.getSpeciesInfluence(a.getSpecie().getId());
    }

    @Override
    public double calculateAnimalSatisfaction(Animal a){
        Habitat h = a.getHabitat();
        return 20+(3*sameSpecieAnimal(a, h))-(2*differentSpecieAnimal(a, h))+ (area(h)/population(h) + adquaecy(a, h));
    }
}