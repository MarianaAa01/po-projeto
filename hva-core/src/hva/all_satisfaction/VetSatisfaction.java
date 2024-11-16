package hva.all_satisfaction;

import hva.work.Vet;
import hva.habitats.Species;

public class VetSatisfaction extends Satisfaction{
    
    public double work(Vet v) {
        int work = 0;
        for (Species s : v.getSpeciesList()) {
            work += s.getPopulation() / s.getNVets();
        }
        return work;
    }

    @Override
    public double calculateVetSatisfaction(Vet v) {
        return 20 - work(v);
    }

    /* Métodos:
    public int vetSatisfaction(Vet v){}

    public int work(Keeper k){}

    public int population(Species s){}

    public int nVets(Species s){}
    */

    /*satisfaction(v)=20-trabalho(v)
     * 
     * trabalho(v)= somatorio de ((todas as especies que o vet pode vacinar)/(numero de vets com a mesma responsabilidade))
     */
}
