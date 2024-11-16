package hva.habitats;

import java.io.Serializable;

import java.util.Map;
import java.util.TreeMap;
import hva.trees.Tree;
import hva.work.Keeper;
import hva.CaseInsensitiveComparator;
import java.util.ArrayList;
import java.util.List;


public class Habitat implements Serializable{
    private String _id;
    private String _name;
    private int _area;
    private Map<String, Animal> _animals = new TreeMap<String, Animal>(new CaseInsensitiveComparator());
    private Map<String, Tree> _trees = new TreeMap<String, Tree>(new CaseInsensitiveComparator());
    private Map<String, Keeper> _keepers = new TreeMap<String, Keeper>(new CaseInsensitiveComparator());
    private Map<String, Integer> _influences = new TreeMap<>();

    public Habitat(String id, String name, int area, Tree[] treeList){
        _id = id;
        _name = name;
        _area = area;
        for (Tree tree : treeList) {
            _trees.put(tree.getId(), tree);
        }
    }

    //getters e setters de _id e _name
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

    public int getArea() {
        return _area;
    }

    public void setArea(int area) {
        _area = area;
    }

    public void addAnimal(Animal animal){
        _animals.put(animal.getId(), animal);
    }

    public Animal getAnimal(String animal_id){
        return _animals.get(animal_id);
    }

    public int getPopulation(){
        return _animals.size();
    }

    public void addTree(Tree tree){
        _trees.put(tree.getId(), tree);
    }

    public Tree getTree(String tree_id){
        return _trees.get(tree_id);
    }

    public void addKeeper(Keeper keeper){
        _keepers.put(keeper.getId(), keeper);
    }

    public void removeKeeper(Keeper keeper){
        _keepers.remove(keeper.getId());
    }

    public Keeper getKeeper(String keeper_id){
        return _keepers.get(keeper_id);
    }

    public List<Animal> getAllAnimals(){
        return new ArrayList<Animal>(_animals.values());
    }

    public void removeKeeper(String keeper_id){
        _keepers.remove(keeper_id);
    }

    public int getNWorkers() {
        return _keepers.size();
    }

    public void removeTree(String tree_id){
        _trees.remove(tree_id);
    }

    public void removeAnimal(String animal_id){
        _animals.remove(animal_id);
    }
    
    public void updateSpeciesInfluence(String specieKey, int influenceValue) {
        _influences.put(specieKey, influenceValue);
    }

    public int getSpeciesInfluence(String specieKey) {
        return _influences.getOrDefault(specieKey, 0); 
    }

    public List<Tree> getTrees() {
        return new ArrayList<Tree>(_trees.values());
    }

    public String getAnimals() {
        StringBuilder animals = new StringBuilder();
        for (Animal animal : _animals.values()) {
            animals.append(animal.toString()).append("\n");
        }
        if (!animals.isEmpty())
            animals.deleteCharAt(animals.length() - 1);
        return animals.toString();
    }

    public String getTreeList() {
        StringBuilder treeList = new StringBuilder();
        for (Tree tree : _trees.values()) {
            treeList.append(tree.toString());
            treeList.append("\n");
        }
        if (!treeList.isEmpty())
            treeList.deleteCharAt(treeList.length() - 1);
        return treeList.toString();
    }

    /*Método pra treinar pro teste prático: */
    public int getNumberOfTreesInHabitat(){
        return _trees.size();
    }

    @Override
    public String toString() {
        return "HABITAT" + "|" 
                + getId() + "|" 
                + getName() + "|"
                + getArea() + "|"
                + _trees.size() + (_trees.size() > 0 ? "\n" : "")
                + getTreeList();
    }
}
