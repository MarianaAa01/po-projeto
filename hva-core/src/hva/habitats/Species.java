package hva.habitats;

import java.io.Serializable;

import java.util.Map;
import java.util.TreeMap;

import hva.CaseInsensitiveComparator;
import hva.work.Vet;

public class Species implements Serializable{
    private String _id;
    private String _name;
    private Map<String, Animal> _animals = new TreeMap<String, Animal>(new CaseInsensitiveComparator());
    private Map<String, Vet> _vets = new TreeMap<String, Vet>(new CaseInsensitiveComparator());

    //construtor
    public Species(String id, String name){
        _id = id;
        _name = name;
    }

    //getters e setters
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public void addAnimal(Animal animal){
        _animals.put(animal.getId(), animal);
    }

    public Animal getAnimal(String animal_id){
        return _animals.get(animal_id);
    }

    public void addVet(Vet vet){
        _vets.put(vet.getId(), vet);
    }

    public void removeVet(Vet vet){
        _vets.remove(vet.getId());
    }

    public Vet getVet(String vet_id){
        return _vets.get(vet_id);
    }

    public int getPopulation(){
        return _animals.size();
    }

    public int getNVets(){
        return _vets.size();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Species)) {
            return false;
        }
        Species specie = (Species) o;
        
        return _id.equals(specie.getId()) && _name.equals(specie.getName());
    }
}