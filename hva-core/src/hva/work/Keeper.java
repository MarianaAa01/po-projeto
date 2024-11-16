package hva.work;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

import hva.CaseInsensitiveComparator;
import hva.habitats.Habitat;

public class Keeper extends Employee {

    private Map<String, Habitat> _habitats = new TreeMap<String, Habitat>(new CaseInsensitiveComparator());

    public Keeper(String id, String name, Habitat[] habitats){
        super(id, name);
        for (Habitat habitat : habitats) {
            _habitats.put(habitat.getId(), habitat);
        }
    }
    public void addHabitat( Habitat habitat){
        _habitats.put(habitat.getId(), habitat);
    }

    public void removeHabitat(Habitat habitat){
        _habitats.remove(habitat.getId());
    }

    public Habitat getHabitat(String habitat_id){
        return _habitats.get(habitat_id);
    }

    public List<Habitat> getHabitats(){
        return new ArrayList<>(_habitats.values());
    }

    public String getResponsabilities() {
        StringBuilder responsabilities = new StringBuilder();
        for (Habitat habitat : _habitats.values()){
            responsabilities.append(habitat.getId()).append(",");
        }
        if (!responsabilities.isEmpty())
            responsabilities.deleteCharAt(responsabilities.length() - 1);
        return responsabilities.toString();
    }

    /* método pra treinar pro teste prático: */
    public int numberOfHabitats(){
        return _habitats.size();
    }

    @Override
    public String toString(){
        return "TRT" + "|"
                + getId() + "|"
                + getName() + (_habitats.isEmpty() ? "" : "|")
                + getResponsabilities();
    }
}
