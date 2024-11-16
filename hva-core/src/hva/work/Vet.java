package hva.work;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

import hva.vaccines.Vaccinations;
import hva.vaccines.Vaccine;
import hva.CaseInsensitiveComparator;
import hva.habitats.Species;

public class Vet extends Employee { 

    private Map<String, Vaccine> _vaccines = new TreeMap<String, Vaccine>(new CaseInsensitiveComparator());
    private Map<String, Species> _species = new TreeMap<String, Species>(new CaseInsensitiveComparator());
    private List<Vaccinations> _vaccinations = new ArrayList<Vaccinations>();
    private List<Vaccinations> _wrongVaccinations = new ArrayList<Vaccinations>();

    public Vet(String id, String name, Species[] species){
        super(id, name);
        for(Species specie : species){
            _species.put(specie.getId(), specie);
        }
    }

    public void addSpecies(Species specie){
        _species.put(specie.getId(), specie);
    }

    public Species getSpecies(String specie_id){
        return _species.get(specie_id);
    }

    public List<Species> getSpeciesList(){
        return new ArrayList<Species>(_species.values());
    }

    public int nSpecies(){
        return _species.size();
    }

    public void removeSpecies(Species specie) {
        _species.remove(specie.getId());
    }

    public void addVaccine(Vaccine vaccine){
        _vaccines.put(vaccine.getId(), vaccine);
    }

    public void addVaccine(String vaccine_id, Vaccine vaccine){
        _vaccines.put(vaccine_id, vaccine);
    }

    public boolean checkSpecies(String specie_id){
        return _species.containsKey(specie_id);
    }

    public void addVaccination(Vaccinations vaccination){
        _vaccinations.add(vaccination);
    }

    public void addWrongVaccination(Vaccinations vaccination){
        _wrongVaccinations.add(vaccination);
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

    public String getWrongVaccionations(){
        StringBuilder vaccinations = new StringBuilder();
        for (Vaccinations vaccination : _wrongVaccinations){
            vaccinations.append(vaccination.toString()).append("\n");
        }
        if (!vaccinations.isEmpty())
            vaccinations.deleteCharAt(vaccinations.length() - 1);
        return vaccinations.toString();
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

    @Override
    public String toString(){
        return "VET" + "|"
                + getId() + "|"
                + getName() + (_species.isEmpty() ? "" : "|")
                + getSpecies();
    }

}
