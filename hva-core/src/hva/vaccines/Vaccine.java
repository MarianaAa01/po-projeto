package hva.vaccines;

import java.io.Serializable;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

import hva.CaseInsensitiveComparator;
import hva.habitats.Animal;
import hva.habitats.Species;

public class Vaccine implements Serializable {
    private String _id;
    private String _name;
    private List<Vaccinations> _vaccinations = new ArrayList<Vaccinations>();
    private Map<String, Species> _species = new TreeMap<String, Species>(new CaseInsensitiveComparator());

    public Vaccine(String id, String name, Species[] species){
        _id = id;
        _name = name;
        for(Species specie : species){
            _species.put(specie.getId(), specie);
        }
    }

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

    public void addVaccination(Vaccinations vaccination){
        _vaccinations.add(vaccination);
    }

    public List<Species> getSpeciesList(){
        return new ArrayList<Species>(_species.values());
    }

    public int nameSize(Species specie1, Species specie2){
        return Math.max(specie1.getName().length(), specie2.getName().length());
    }

    public int commonCharacters(Species specie1, Species specie2){
        int count = 0;
        String name1 = specie1.getName();
        String name2 = specie2.getName();
        for (int i = 0; i < name1.length(); i++){
            if (name2.contains(String.valueOf(name1.charAt(i)))){
                count++;
            }
        }
        return count;
    }

    public int damage(Animal animal){
        Species specie = animal.getSpecie();
        int max = 0;
        int a = 0;
        for (Species s : _species.values()){
            a = nameSize(specie, s) - commonCharacters(specie, s);
            if (a > max){
                max = a;
            }
        }
        return max;
    }

    public String getSpecies() {
        StringBuilder species = new StringBuilder();
        for (Species specie : _species.values()){
            species.append(specie.getId()).append(",");
        }
        if (!species.isEmpty())
            species.deleteCharAt(species.length() - 1);
        return species.toString();
    }

    public int numberOfVaccinations(){
        return
    }

    @Override
    public String toString(){
        return "VACINA" + "|"
                + getId() + "|"
                + getName() + "|"
                + _vaccinations.size() + (_species.isEmpty() ? "" : "|")
                + getSpecies();
    }
    
}
