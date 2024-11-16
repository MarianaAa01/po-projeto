package hva.habitats;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;

import hva.vaccines.Vaccinations;
import hva.vaccines.Vaccine;

public class Animal implements Serializable {
    private String _id;
    private String _name;
    private Species _specie;
    private Habitat _habitat;
    private List<Vaccinations> _vaccinations = new ArrayList<>();
    private List<String> _healthHistoryList = new ArrayList<>();
    //private Species _specie;

    //construtor:
    public Animal(String id, String name, Species specie, Habitat habitat){
        _id = id;
        _name = name;
        _specie = specie;
        _habitat = habitat;
    }

    //getters e setters para _id e _name:
    public String getId() {
        return _id;
    }
    public void setId(String id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }
    public void setName(String name) {
        _name = name;
    }

    public void setSpecie(Species specie){
        _specie = specie;
    }

    public Species getSpecie(){
        return _specie;
    }

    public void setHabitat(Habitat habitat){
        _habitat = habitat;
    }

    public Habitat getHabitat(){
        return _habitat;
    }

    public void addVaccination(Vaccinations vaccination){
        _vaccinations.add(vaccination);
    }

    public int addHealthHistory(Vaccine vaccine){
        int damage = 0;
        if (vaccine.getSpeciesList().contains(_specie)){
            _healthHistoryList.add("NORMAL");
        }
        else{
            damage = vaccine.damage(this);
            if (damage == 0) {
                _healthHistoryList.add("CONFUSÃO");
            }
            else if (damage >= 1 && damage <= 4) {
                _healthHistoryList.add("ACIDENTE");
            }
            else {
                _healthHistoryList.add("ERRO");
            }
        }
        return damage;
    }

    public String getHealthHistory(){
        StringBuilder healthHistory = new StringBuilder();
        for (String history : _healthHistoryList){
            healthHistory.append(history).append(",");
        }
        if (!healthHistory.isEmpty())
            healthHistory.deleteCharAt(healthHistory.length() - 1);
        return healthHistory.toString();
    }

    public String getVaccionations(){
        StringBuilder vaccinations = new StringBuilder();
        for (Vaccinations vaccination : _vaccinations){
            vaccinations.append(vaccination.toString()).append("\n");
        }
        if (!vaccinations.isEmpty())
            vaccinations.deleteCharAt(vaccinations.length() - 1);
        return vaccinations.toString();
    }

    /*Método criado pro teste prático: */
    public int getNumberOfVaccinations(){
        return _vaccinations.size();
    }

    @Override
    public String toString(){
        return "ANIMAL" + "|" 
                + getId() + "|" 
                + getName() + "|"
                + getSpecie().getId() + "|"
                + (!_vaccinations.isEmpty() ? getHealthHistory() : "VOID") + "|"
                + getHabitat().getId();
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Animal){
            Animal animal = (Animal) obj;
            return animal.getId().equals(_id);
        }
        return false;
    }

    /*public Species getSpecie(){
        return _specie;
    }
    public void setSpecie(Specie _specie){
        this._specie = _specie;
    }*/ 
}