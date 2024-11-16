package hva;

//Java imports
import hva.all_satisfaction.AnimalSatisfaction;
import hva.all_satisfaction.KeeperSatisfaction;
import hva.all_satisfaction.VetSatisfaction;
import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.DuplicateEmployeeException;
import hva.exceptions.DuplicateHabitatException;
import hva.exceptions.DuplicateSpeciesException;
import hva.exceptions.DuplicateTreeException;
import hva.exceptions.DuplicateVaccineException;
import hva.exceptions.ImportFileException;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.UnknownTreeException;
import hva.exceptions.UnknownVaccineException;
import hva.exceptions.UnknownVeterinarianException;
import hva.exceptions.UnrecognizedEntryException;
import hva.exceptions.VeterinarianNotAuthorizedException;
import hva.habitats.Animal;
import hva.habitats.Habitat;
import hva.habitats.Species;
import hva.seasons.SeasonMachine;
import hva.trees.Caduca;
import hva.trees.Perene;
import hva.trees.Tree;
import hva.vaccines.Vaccine;
import hva.vaccines.Vaccinations;
import hva.work.Employee;
import hva.work.Keeper;
import hva.work.Vet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Class Hotel implements a hotel
 */
public class Hotel implements Serializable {

    /** Serial number for serialization */
    @Serial
    private static final long serialVersionUID = 202407081733L;

    private boolean _dirty = false;

    /** Animals*/
    private Map<String, Animal> _animals = new TreeMap<>(new CaseInsensitiveComparator());
    /** Species */
    private Map<String, Species> _species = new TreeMap<>(new CaseInsensitiveComparator());
    /** Habitats */
    private Map<String, Habitat> _habitats = new TreeMap<>(new CaseInsensitiveComparator());
    /** Vaccines */
    private Map<String, Vaccine> _vaccines = new TreeMap<>(new CaseInsensitiveComparator());
    /** Trees */
    private Map<String, Tree> _trees = new TreeMap<>(new CaseInsensitiveComparator());
    /** Employees */
    private Map<String, Employee> _employees = new TreeMap<>(new CaseInsensitiveComparator());

    private List<Vaccinations> _vaccinations = new ArrayList<Vaccinations>();

    private List<Vaccinations> _wrongVaccinations = new ArrayList<Vaccinations>();

    private AnimalSatisfaction _animalSatisfaction = new AnimalSatisfaction();
    private KeeperSatisfaction _keeperSatisfaction = new KeeperSatisfaction();
    private VetSatisfaction _vetSatisfaction = new VetSatisfaction();

    public void hotelCheck() {
        System.out.println("Hotel is working");
    }


    /**
     * This method interacts with the {@link SeasonMachine} that tracks 
     * the seasonal state of the hotel and provides acess to 
     * the current season.
     * 
     * @return a {@code String} representing the current 
     * season ("Spring", "Summer", "Autumn" and "Winter").
     */
    private SeasonMachine _seasonMachine = new SeasonMachine();

    /**
     * Returns the current season from the season machine.
     * 
     * @return String       the current season as a string ("SPRING", "SUMMER", "AUTUMN", or "WINTER")
     */
    public String getCurrentSeason(){
    return _seasonMachine.getCurrentSeason();
    }

    /**
     * Advances the season to the next one and updates all trees accordingly.
     * 
     * @return an integer representing the new season:
     *         0 if the new season is "SPRING",
     *         1 if the new season is "SUMMER",
     *         2 if the new season is "AUTUMN",
     *         3 if the new season is "WINTER"
     */
    public int advanceSeason() {
        _seasonMachine.nextSeason();

        for (Tree tree : _trees.values()) {
            tree.advanceSeason(_seasonMachine.getCurrentSeason());
        }
        isDirty();
        if (_seasonMachine.getCurrentSeason().equals("SPRING")) {
            return 0;
            }
        else if (_seasonMachine.getCurrentSeason().equals("SUMMER")) {
            return 1;
        }
        else if (_seasonMachine.getCurrentSeason().equals("AUTUMN")) {
            return 2;
        }
        else {
            return 3;
        }
    }

     /** Lookups */
     
     /**
     * Shows all animals registered in the hotel.
     *
     * @return String           a string representing all animals in the hotel, one per line
     */
     public String showAllAnimals(){
        StringBuilder result = new StringBuilder();
		for (Animal animal : _animals.values()) {
			result.append(animal.toString());
			result.append("\n");
		}
		if (!result.isEmpty()) {
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
    }

    /**
     * Shows all trees registered in the hotel.
     *
     * @return String           a string representing all trees in the hotel, one per line
     */
    public String showAllTrees(){
        StringBuilder result = new StringBuilder();
        for (Tree tree : _trees.values()) {
            result.append(tree.toString());
            result.append("\n");
        }
        if (!result.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    /**
     * Shows all habitats registered in the hotel.
     *
     * @return String           a string representing all habitats in the hotel, one per line
     */
    public String showAllHabitats() {
        StringBuilder result = new StringBuilder();
        for (Habitat habitat : _habitats.values()) {
            result.append(habitat.toString());
            result.append("\n");
        }
        if (!result.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }
 
    /**
     * Shows all employees (keepers and vets) registered in the hotel.
     *
     * @return String           a string representing all employees in the hotel, one per line
     */
    public String showAllEmployees(){
        StringBuilder result = new StringBuilder();
		for (Employee employee : _employees.values()) {
			result.append(employee.toString());
			result.append("\n");
		}
		if (!result.isEmpty()) {
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
    }

    /**
     * Shows all vaccines registered in the hotel.
     *
     * @return String           a string representing all vaccines in the hotel, one per line
     */
    public String showAllVaccines() {
        StringBuilder result = new StringBuilder();
        for (Vaccine vaccine : _vaccines.values()) {
            result.append(vaccine.toString());
            result.append("\n");
        }
        if (!result.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    /**
     * Returns a string with all the vaccinations.
     * 
     * @return String
     */
    public String showAllVaccinations() {
        StringBuilder result = new StringBuilder();
        for (Vaccinations vaccination : _vaccinations) {
            result.append(vaccination.toString());
            result.append("\n");
        }
        if (!result.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    /**
     * Returns a string with all the trees in a habitat.
     * 
     * @param habitatId                         Id of the habitat
     * @return String
     * @throws UnknownHabitatException          if the habitat doesn't exist
     */
    public String showTreesInHabitat(String habitatId) throws UnknownHabitatException {
        if (!_habitats.containsKey(habitatId)) {
            throw new UnknownHabitatException(habitatId);
        }
        Habitat habitat = (Habitat) _habitats.get(habitatId);
        return habitat.getTreeList();
    }

    /**
     * Returns a string with all the animals in a habitat.
     * 
     * @param habitatId                                Id of the habitat
     * @return String
     * @throws UnknownHabitatException          if the habitat doesn't exist
     */
    public String showAnimalsInHabitat(String habitatId) throws UnknownHabitatException {
        if (!_habitats.containsKey(habitatId)) {
            throw new UnknownHabitatException(habitatId);
        }
        Habitat habitat = (Habitat) _habitats.get(habitatId);
        return habitat.getAnimals();
    }

    /**
     * Returns a string with all the vaccinations of a specific animal.
     * 
     * @param animalId                         Id of the animal
     * @return String
     * @throws UnknownAnimalException          if the animal doesn't exist
     */
    public String showAnimalsVaccinations(String animalId) throws UnknownAnimalException {
        if (!_animals.containsKey(animalId)) {
            throw new UnknownAnimalException(animalId);
        }
        Animal animal = (Animal) _animals.get(animalId);
        return animal.getVaccionations();
    }

    /**
     * Returns a string with all the vaccinations that a vet can administrate.
     * 
     * @param vetId                                  Id of the veterinarian
     * @return String
     * @throws UnknownVeterinarianException          if the veterinarian doesn't exist
     */
    public String showVetVaccinations(String vetId) throws UnknownVeterinarianException {
        if (!_employees.containsKey(vetId)) {
            throw new UnknownVeterinarianException(vetId);
        }
        Vet vet = (Vet) _employees.get(vetId);
        return vet.getVaccionations();
    }

    /**
     * Returns a string with the wrong vaccinations using the getWrongVaccinations method.
     * 
     * @return String
     */
    public String showWrongVaccinations() {
        return getWrongVaccionations();
    }

    /**
     * Returns a string with the wrong vaccinations.
     * Each wrong vaccination in a line.
     * 
     * @return String
     */
    public String getWrongVaccionations(){
        StringBuilder vaccinations = new StringBuilder();
        for (Vaccinations vaccination : _wrongVaccinations){
            vaccinations.append(vaccination.toString()).append("\n");
        }
        if (!vaccinations.isEmpty())
            vaccinations.deleteCharAt(vaccinations.length() - 1);
        return vaccinations.toString();
    }

    /**
     * Returns the value of the animal satisfaction.
     * 
     * @return int
     * @throws UnknownAnimalException          if the animal doesn't exist
     */
    public int satisfactionOfAnimal(String id) throws UnknownAnimalException {
        if (!_animals.containsKey(id)) {
            throw new UnknownAnimalException(id);
        }
        return (int) Math.round(_animalSatisfaction.calculateAnimalSatisfaction(_animals.get(id)));
    }

    /**
     * Returns the value of the employee's satisfaction.
     * 
     * @param id                                Id of the employee
     * @return int
     * @throws UnknownEmployeeException          if the employee doesn't exist
     */
    public int satisfactionOfEmployee(String id) throws UnknownEmployeeException {
        if (!_employees.containsKey(id)) {
            throw new UnknownEmployeeException(id);
        }
        if (_employees.get(id) instanceof Keeper keeper) {
            return (int) Math.round(_keeperSatisfaction.calculateKeeperSatisfaction(keeper));
        }
        else {
            Vet vet =(Vet) _employees.get(id);
            return (int) Math.round(_vetSatisfaction.calculateVetSatisfaction(vet));
        }
    }

    /**
     * Returns the value of global satisfaction.
     * 
     * @return int
     * @throws UnknownAnimalException          if the animal doesn't exist
     * @throws UnknownHabitatException         if the habitat doesn't exist
     */
    public int globalSatisfaction() throws UnknownAnimalException, UnknownEmployeeException {
        int satisfaction = 0;
        for (Animal animal : _animals.values()) {
            satisfaction += satisfactionOfAnimal(animal.getId());
        }
        for (Employee employee : _employees.values()) {
            satisfaction += satisfactionOfEmployee(employee.getId());
        }
        return satisfaction;
    }

    /**
     * Reads the input file and processes its entries to create domain entities such as animals, species, habitats, etc.
     *
     * @param filename                         the name of the input file
     * @throws ImportFileException             if an error occurs while reading the file
     * @throws IOException                     if an I/O error occurs
     * @throws UnrecognizedEntryException      if the file contains an unrecognized or malformed entry
     */
    public void importFile(String filename) throws ImportFileException, IOException, UnrecognizedEntryException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                readLine(line);
            }
        } catch (IOException e) {
            throw new ImportFileException(filename);
        }
    }

    /**
     * Registers a new habitat with the given habitatId, name and area.
     * 
     * @param habitatId                        Id of the habitat to register
     * @param name                             name of the habitat
     * @param area                             area value of the habitat we're going to register
     * @throws DuplicateHabitatException       if the habitat already exists
     */
    public void registerHabitatIn(String habitatId, String name, int area) throws DuplicateHabitatException {
        if (_habitats.containsKey(habitatId)) {
            throw new DuplicateHabitatException(habitatId);
        }
        try {
            registerHabitat(habitatId, name, area, new String[] {});
        } catch (UnknownTreeException e) {
            e.printStackTrace();
        }

        isDirty();
    }

    /**
     * Registers an habitat to the hotel.
     * 
     * @param id                               the id of the habitat
     * @param name                             the name of the habitat
     * @param area                             habitat's area
     * @param treeIdList                       list of tree ids
     * @throws DuplicateHabitatException       if the habitat's id already exists
     * @throws UnknownTreeException            if the tree's id is unknown
     */
    public void registerHabitat(String id, String name, int area, String[] treeIdList)
        throws DuplicateHabitatException, UnknownTreeException {
        if (_habitats.containsKey(id)) {
            throw new DuplicateHabitatException(id);
        }
        Tree[] treeList = new Tree[treeIdList.length];
        if (treeIdList.length != 0) {
            for (int key = 0; key < treeIdList.length; key++) {
                if (!_trees.containsKey(treeIdList[key])) {
                    throw new UnknownTreeException(treeIdList[key]);
                }
                treeList[key] = _trees.get(treeIdList[key]);
            }
        }
        _habitats.put(id, new Habitat(id, name, area, treeList));
        isDirty();
    }

    /**
     * Registers an animal to the hotel and adds the animal to the habitat and specie of the animal.
     * 
     * @param id                               the id of the animal
     * @param name                             the name of the animal
     * @param speciesid                        all specie's ids 
     * @param habitat_id                       the id of the habitat where the animal is
     * @throws DuplicateAnimalException        if the animal's id already exists
     * @throws DuplicateHabitatException       if the habitat's id already exists
     * @throws DuplicateSpeciesException       if the animal's specie id already exists
     */
    public void registerAnimal(String id, String name, String speciesid, String habitat_id) throws DuplicateAnimalException, 
        DuplicateSpeciesException, DuplicateHabitatException, UnknownHabitatException, UnknownSpeciesException {
        for (String key : _animals.keySet()) {
            if (key.equalsIgnoreCase(id)) {
                throw new DuplicateAnimalException(id);
            }
        }
        if (!_habitats.containsKey(habitat_id)) {
            throw new UnknownHabitatException(habitat_id);
        }
        if (!_species.containsKey(speciesid)) {
            throw new DuplicateSpeciesException(speciesid);
        }
        if (!_habitats.containsKey(habitat_id)) {
            throw new DuplicateHabitatException(habitat_id);
        }
        if (!_species.containsKey(speciesid)) {
            throw new UnknownSpeciesException(speciesid);
        }
        _animals.put(id, new Animal(id, name, _species.get(speciesid), _habitats.get(habitat_id)));
        _habitats.get(habitat_id).addAnimal(_animals.get(id));
        _species.get(speciesid).addAnimal(_animals.get(id));
        isDirty();
    }

    /**
     * Registers a specie to the hotel.
     * 
     * @param id                               the id of the specie
     * @param name                             the name of the specie
     * @throws DuplicateSpeciesException       if the habitat's id already exists
     */
    public void registerSpecie(String id, String name) throws DuplicateSpeciesException {
        for (String key : _species.keySet()) {
            if (key.equalsIgnoreCase(id)) {
                throw new DuplicateSpeciesException(id);
            }
        }
        for (Species specie : _species.values()) {
            if (specie.getName().equals(name)) {
                throw new DuplicateSpeciesException(name);
            }
        }
        _species.put(id, new Species(id, name));
        isDirty();
    }

    /**
     * Checks if a species with the given Id exists.
     * 
     * @param id              Id of the species to check
     * @return boolean        true if the species exists, false otherwise
     */
    public boolean checkSpecies(String id) {
        return _species.containsKey(id);
    }

    /**
     * Registers a tree to the hotel.
     * 
     * @param id                               the id of the tree
     * @param name                             the name of the tree
     * @param age                              the tree's age
     * @param clean_difficulty                 tree's clean difficulty
     * @param treeType                         tree's type
     * @throws DuplicateTreeException          if the tree's id already exists
     */
    public void registerTree(String id, String name, int age, int clean_difficulty, String treeType) 
        throws DuplicateTreeException {
        if (_trees.get(id) != null) {
            throw new DuplicateTreeException(id);
        }
        String season = _seasonMachine.getCurrentSeason();
        if (treeType.equals("PERENE")) {
            _trees.put(id, new Perene(id, name, age, season, clean_difficulty));
        } else {
            _trees.put(id, new Caduca(id, name, age, season, clean_difficulty));
        }
        isDirty();
    }

    /**
     * Registers a new tree with the given habitatId, treeId, name, age, clean_difficulty, and treeType(PERENE or CADUCA).
     * 
     * @param habitatId                        Id of the vaccine we're going to create(register)
     * @param treeId                           Id of the tree we're going to create
     * @param name                             name of the tree
     * @param age                              age of the tree
     * @param clean_difficulty                 difficulty of clean the tree's leaves
     * @param treeType                         list with the species that can receive this specific vaccine
     * @return String                          a string with the information of the tree
     * @throws DuplicateTreeException          if the tree already exists
     * @throws UnknownHabitatException         if the habitat doesn't exist
     */
    public String registerTreeIn(String habitatId, String treeId, String name, int age, int clean_difficulty, String treeType) 
        throws DuplicateTreeException, UnknownHabitatException {
        for (String key : _trees.keySet()) {
            if (key.equalsIgnoreCase(treeId)) {
                throw new DuplicateTreeException(treeId);
            }
        }
        String season = _seasonMachine.getCurrentSeason();
        Habitat treeHabitat =(Habitat) _habitats.get(habitatId);
        if (treeHabitat == null) {
            throw new UnknownHabitatException(habitatId);
        }
        if (treeType.equals("PERENE")) {
            Perene newTree = new Perene(treeId, name, age, season, clean_difficulty);
            treeHabitat.addTree(newTree);
            _trees.put(treeId, newTree);
            isDirty();
            return newTree.toString();
        } else {
            Caduca newTree = new Caduca(treeId, name, age, season, clean_difficulty);
            treeHabitat.addTree(newTree);
            _trees.put(treeId, newTree);
            isDirty();
            return newTree.toString();
        }
    }

    /**
     * Registers a new vaccine with the given Id, name, and a list of species that can receive it.
     * 
     * @param vaccineId                        Id of the vaccine we're going to create(register)
     * @param name                             name of the vaccine
     * @param speciesList                      list with the species that can receive this specific vaccine
     * @throws DuplicateVaccineException       if the habitat doesn't exist
     * @throws UnknownSpeciesException         if the specie doesn't exist
     */
    public void registerVaccineIn(String vaccineId, String name, String speciesList) throws DuplicateVaccineException, UnknownSpeciesException {
        for (String key : _vaccines.keySet()) {
            if (key.equalsIgnoreCase(vaccineId)) {
                throw new DuplicateVaccineException(vaccineId);
            }
        }
        String[] speciesIds = speciesList.split(",");
        registerVaccine(vaccineId, name, speciesIds);
    }

    /**
     * Registers a vaccine to the hotel and adds the species that can take the vaccine
     * 
     * @param id                               the id of the vaccine
     * @param name                             the name of the vaccine
     * @param speciesIds                       species that can take this vaccine
     * @throws DuplicateVaccineException       if the vaccine's id already exists
     * @throws UnknownSpeciesException         if the species id is unknown
     */
    public void registerVaccine(String id, String name, String[] speciesIds) 
        throws DuplicateVaccineException, UnknownSpeciesException {
        if (_vaccines.containsKey(id)) {
            throw new DuplicateVaccineException(id);
        }

        Species[] speciesList = new Species[speciesIds.length];
        for (int key = 0; key < speciesIds.length; key++) {
            if (!_species.containsKey(speciesIds[key])) {
                throw new UnknownSpeciesException(speciesIds[key]);
            }
            speciesList[key] = _species.get(speciesIds[key]);
            

        }
        _vaccines.put(id, new Vaccine(id, name, speciesList));
        isDirty();
    }

    /**
     * Registers(creates) a new employee(veterinarian(VET) or keeper(TRT))
     * 
     * @param id                                  Id of the new employee
     * @param name                                name of the new employee
     * @param type                                type of employee(veterinarian(VET) or keeper(TRT))
     * @throws DuplicateEmployeeException         if the employee already exists
     * @throws UnknownSpeciesException            if the specie doesn't exist
     * @throws UnknownHabitatException            if the habitat doesn't exist
     */
    public void registerEmpolyee(String id, String name, String type) throws DuplicateEmployeeException, UnknownSpeciesException, UnknownHabitatException {
        if (type.equals("VET")) {
            registerVet(id, name, new String[] {});
        }
        else if (type.equals("TRT")) {
            registerKeeper(id, name, new String[] {});
        }
        isDirty();
    }

    /**
     * Delegates a responsability to a worker(veterinarian or keeper).
     * 
     * @param workerId                         Id of the worker whose responsability is removed
     * @param responsabilityId                 Id of the responsability that's going to be removed
     * @throws UnknownHabitatException         if the habitat doesn't exist
     * @throws UnknownSpeciesException         if the specie doesn't exist
     * @throws UnknownEmployeeException        if the employee doesn't exist
     */
    public void addResponsability(String workerId, String responsabilityId) throws UnknownHabitatException, UnknownSpeciesException, UnknownEmployeeException {
        if (!_employees.containsKey(workerId)) {
            throw new UnknownEmployeeException(workerId);
        }
        
        if (_employees.get(workerId) instanceof Keeper keeper) {
            if (!_habitats.containsKey(responsabilityId)) {
                throw new UnknownHabitatException(responsabilityId);
            }
            Habitat habitat =(Habitat)_habitats.get(responsabilityId);
            keeper.addHabitat(habitat);
            habitat.addKeeper(keeper);
            isDirty();
        }
        else if (_employees.get(workerId) instanceof Vet vet) {
            if (!_species.containsKey(responsabilityId)) {
                throw new UnknownSpeciesException(responsabilityId);
            }
            Species species =(Species)_species.get(responsabilityId);
            vet.addSpecies(species);
            species.addVet(vet);
            isDirty();
        }
    }

    /**
     * Removes a responsability from a worker(veterinarian or keeper).
     * 
     * @param workerId                         Id of the worker whose responsability is removed
     * @param responsabilityId                 Id of the responsability that's going to be removed
     * @throws UnknownHabitatException         if the habitat doesn't exist
     * @throws UnknownSpeciesException         if the specie doesn't exist
     * @throws UnknownEmployeeException        if the employee doesn't exist
     */
    public void removeResponsability(String workerId, String responsabilityId) throws UnknownHabitatException, UnknownSpeciesException, UnknownEmployeeException {
        if (!_employees.containsKey(workerId)) {
            throw new UnknownEmployeeException(workerId);
        }
        
        if (_employees.get(workerId) instanceof Keeper keeper) {
            if (!_habitats.containsKey(responsabilityId)) {
                throw new UnknownHabitatException(responsabilityId);
            }
            Habitat habitat =(Habitat)_habitats.get(responsabilityId);
            keeper.removeHabitat(habitat);
            habitat.removeKeeper(keeper);
            isDirty();
        }
        else if (_employees.get(workerId) instanceof Vet vet) {
            if (!_species.containsKey(responsabilityId)) {
                throw new UnknownSpeciesException(responsabilityId);
            }
            Species species =(Species)_species.get(responsabilityId);
            vet.removeSpecies(species);
            species.removeVet(vet);
            isDirty();
        }
    }

    /**
     * Registers a keeper to the hotel and adds the habitats responsabilities to the keeper
     * 
     * @param id                               the id of the keeper
     * @param name                             the name of the keeper
     * @param habitatsIds                      habitat's id's
     * @throws DuplicateEmployeeException      if the employee's id already exists
     * @throws UnknownTreeException            if the tree id is unknown
     */
    public void registerKeeper(String id, String name, String[] habitatsIds) 
        throws DuplicateEmployeeException, UnknownHabitatException {
        if (_employees.containsKey(id)) {
            throw new DuplicateEmployeeException(id);
        }

        Habitat[] habitatlist = new Habitat[habitatsIds.length];

        for (int key = 0; key < habitatsIds.length; key++) {
            if (!_habitats.containsKey(habitatsIds[key])) {
                throw new UnknownHabitatException(habitatsIds[key]);
            }
            habitatlist[key] = _habitats.get(habitatsIds[key]);
        }
        Keeper keeper = new Keeper(id, name, habitatlist);

        _employees.put(id, keeper);
        for (Habitat habitat : habitatlist) {
            habitat.addKeeper(keeper);
        }
        isDirty();
    }

    /**
     * Registers a veterinarian to the hotel
     * 
     * @param id                               the id of the veterinarian
     * @param name                             the name of the veterinarian
     * @param speciesIds                       list of species ids
     * @throws DuplicateEmployeeException      if the employee's id already exists
     * @throws UnknownSpeciesException         if the species' id is unknown
     */
    public void registerVet(String id, String name, String[] speciesIds) 
        throws DuplicateEmployeeException, UnknownSpeciesException {
        if (_employees.containsKey(id)) {
            throw new DuplicateEmployeeException(id);
        }

        Species[] speciesList = new Species[speciesIds.length];
        for (int key = 0; key < speciesIds.length; key++) {
            if (!_species.containsKey(speciesIds[key])) {
                throw new UnknownSpeciesException(speciesIds[key]);
            }
            speciesList[key] = _species.get(speciesIds[key]);
        }

        Vet vet = new Vet(id, name, speciesList);

        _employees.put(id, vet);
        for (Species species : speciesList) {
            species.addVet(vet);
        }
        isDirty();
    }
    


    /**
     * Reads a line from the file and processes the entry based on its type.
     *
     * @param line                           the line from the file to be processed
     * @throws UnrecognizedEntryException    if the entry type is not recognized
    */
    public void readLine(String line) throws UnrecognizedEntryException {
       //To be implemented
        //Dividir a linha por esta barra:
        String[] fields = line.split("\\|");
        //switch case:
        switch(fields[0]){
            //uma classe para cada coisa e dou os imports lá dentro de cada classe
            case "ANIMAL" -> readAnimal(fields);
            case "ESPÉCIE" -> readSpecies(fields);
            case "ÁRVORE" -> readTree(fields);
            case "VACINA" -> readVaccine(fields);
            case "HABITAT" -> readHabitat(fields);
            case "TRATADOR" -> readKeeper(fields);
            case "VETERINÁRIO" -> readVet(fields);
            default -> throw new UnrecognizedEntryException(line);
        }
    }

    /**
     * Processes an animal's entry.
     *
     * @param fields                        array of fields representing the animal's entry
     * @throws UnrecognizedEntryException   if the animal's entry is malformed or contains invalid data
     */
    public void readAnimal(String[] fields) throws UnrecognizedEntryException {
        if (fields.length == 5) {
            try {
                //Parse dos atributos do animal
                //vão ser Strings
                String idAnimal = fields[1];
                String nomeAnimal = fields[2];
                String idEspecie = fields[3];
                String idHabitat = fields[4];
            
            registerAnimal(idAnimal, nomeAnimal, idEspecie, idHabitat);
            
            } catch (DuplicateAnimalException e) { 
            // Lançamos exceção se o formato não for o esperado
            throw new UnrecognizedEntryException("Linha inválida ou mal formatada: " + fields);
            } catch (DuplicateSpeciesException e) {
                throw new UnrecognizedEntryException(fields[3]);
            } catch (DuplicateHabitatException e) {
                throw new UnrecognizedEntryException(fields[4]);
            } catch (UnknownHabitatException e) {
                throw new UnrecognizedEntryException(fields[4]);
            } catch (UnknownSpeciesException e) {
                throw new UnrecognizedEntryException(fields[3]);
            }
        }

    }

    /**
     * Processes a species' entry.
     *
     * @param fields                        array of fields representing the species' entry
     * @throws UnrecognizedEntryException   if the species' entry is malformed or contains invalid data
     */
    public void readSpecies(String[] fields) throws UnrecognizedEntryException{
        if (fields.length == 3) {
            try {
                String idSpecie = fields[1];
                String nameString = fields[2];
                registerSpecie(idSpecie, nameString);

            } catch (DuplicateSpeciesException e) {
                throw new UnrecognizedEntryException("Linha inválida ou mal formatada: " + fields);
            }

        }
    }

    /**
     * Processes a tree's entry.
     *
     * @param fields                        array of fields representing the tree's entry
     * @throws UnrecognizedEntryException   if the tree's entry is malformed or contains invalid data
     */
    public void readTree(String[] fields) throws UnrecognizedEntryException{
        if (fields.length == 6) {
            try {
                String idTree = fields[1];
                String nameString = fields[2];
                int age = Integer.parseInt(fields[3]);
                int clean_difficulty = Integer.parseInt(fields[4]);
                String treeType = fields[5];
                registerTree(idTree, nameString, age, clean_difficulty, treeType);
            }
            catch (DuplicateTreeException e) {
                throw new UnrecognizedEntryException("Linha inválida ou mal formatada: " + fields);
            }
        }
    }

    /**
     * Processes a vaccine's entry.
     *
     * @param fields                        array of fields representing the vaccine's entry
     * @throws UnrecognizedEntryException   if the vaccine's entry is malformed or contains invalid data
     */
    public void readVaccine(String[] fields) throws UnrecognizedEntryException {
        if (fields.length == 4 || fields.length == 3) {
            try {
                String idVaccine = fields[1];
                String nameString = fields[2];
                String[] Speciesids;
                if (fields.length == 4){
                    Speciesids = fields[3].split(",");
                }
                else {
                    Speciesids = new String[0];
                }
                registerVaccine(idVaccine, nameString, Speciesids);

            }
            catch (UnknownSpeciesException | DuplicateVaccineException e) {
                throw new UnrecognizedEntryException("Linha inválida ou mal formatada: " + fields);
            }
        }
    }

    /**
     * Processes a habitat's entry.
     *
     * @param fields                        array of fields representing the habitat's entry
     * @throws UnrecognizedEntryException   if the habitat's entry is malformed or contains invalid data
     */
    public void readHabitat(String[] fields) throws UnrecognizedEntryException { 
        if (fields.length == 5 || fields.length == 4) {
            try {
                String idHabitat = fields[1];
                String nameString = fields[2];
                int area = Integer.parseInt(fields[3]);
                String[] treeList;
                if (fields.length == 5){
                    treeList = fields[4].split(",");
                }
                else {
                    treeList = new String[0];
                }
                registerHabitat(idHabitat, nameString, area, treeList);

            } catch (UnknownTreeException |DuplicateHabitatException  e) {
                throw new UnrecognizedEntryException("Linha inválida ou mal formatada: " + fields);
            }
        }
    }

    /**
     * Processes a keeper's entry.
     *
     * @param fields                        array of fields representing the keeper's entry
     * @throws UnrecognizedEntryException   if the keeper's entry is malformed or contains invalid data
     */
    public void readKeeper(String[] fields) throws UnrecognizedEntryException{
        if (fields.length == 4 || fields.length == 3) {
            try {
                String idKeeper = fields[1];
                String nameString = fields[2];
                String[] habitatsString;
                if (fields.length == 4){
                    habitatsString = fields[3].split(",");
                }
                else {
                    habitatsString = new String[0];
                }

                registerKeeper(idKeeper, nameString, habitatsString);
            }

            catch (DuplicateEmployeeException | UnknownHabitatException  e) {
                throw new UnrecognizedEntryException("Linha inválida ou mal formatada: " + fields);
            }
        }
    }

    /**
     * Processes a veterinarian's entry.
     *
     * @param fields                        array of fields representing the veterinarian's entry
     * @throws UnrecognizedEntryException   if the veterinarian's entry is malformed or contains invalid data
     */
    public void readVet(String[] fields) throws UnrecognizedEntryException {
        if (fields.length == 4 || fields.length == 3) {
            try {
                String idVet = fields[1];
                String nameString = fields[2];
                String[] speciesList;

                if (fields.length == 4){
                    speciesList = fields[3].split(",");
                }
                else {
                    speciesList = new String[0];
                }

                registerVet(idVet, nameString, speciesList);
            }

            catch (DuplicateEmployeeException | UnknownSpeciesException  e) {
                throw new UnrecognizedEntryException("Linha inválida ou mal formatada: " + fields);
            }
        }
    }

    /**
     * Marks the current object as "dirty", indicating that it has been modified.
     */
    public void isDirty() {_dirty = true;}

    /**
     * Marks the current object as "clean", indicating that it has been saved.
     */
    public void cleanDirty() {_dirty = false;}

    /**
     * Checks if the current object is "dirty", meaning it has unsaved or pending changes.
     * 
     * @return true if the object is marked as dirty, false otherwise
     */
    public boolean getDirty() {return _dirty;}

    /**
     * Transfers a specific animal to a new habitat by deleting that animal from the previous habitat.
     * 
     * @param animalId                      Id of the animal we're going to transfer
     * @param newHabitatId                  Id of the animal's new habitat
     * @throws UnknownAnimalException       If the animal doesn't exist
     * @throws UnknownHabitatException      If the habitat doesn't exist
     */
    public void transferAnimal(String animalId, String newHabitatId) throws UnknownAnimalException, UnknownHabitatException{
        if (!_animals.containsKey(animalId)) {
            throw new UnknownAnimalException(animalId);
        }
        Animal transferredAnimal = _animals.get(animalId);
        
        if (!_habitats.containsKey(newHabitatId)) {
            throw new UnknownHabitatException(newHabitatId);
        }
        Habitat oldHabitat = transferredAnimal.getHabitat();
        oldHabitat.removeAnimal(animalId);

        Habitat newHabitat =(Habitat) _habitats.get(newHabitatId);
        transferredAnimal.setHabitat(newHabitat);

        newHabitat.addAnimal(transferredAnimal);

        isDirty();
    }

    /**
     * Changes the influence value(20, 0 or -20) depending on the influence option string(POS, NEU or NEG).
     * 
     * @param habitatId                       Id of the habitat where the specie is
     * @param specieId                        Id of the specie
     * @param influenceOption                 string with the possible types of inlfuence (POS, NEU or NEG)
     * @throws UnknownHabitatException        if the habitat doesn't exist
     * @throws UnknownSpeciesException        if the specie doesn't exist
     */
    public void changeInfluence(String habitatId, String specieId, String influenceOption)  throws UnknownHabitatException, 
                                UnknownSpeciesException {
        if (!_habitats.containsKey(habitatId)) {
            throw new UnknownHabitatException(habitatId);
        } else if (!_species.containsKey(specieId)){
            throw new UnknownSpeciesException(specieId);
        }

        Habitat habitatThatInfluences =(Habitat) _habitats.get(habitatId);
        if (influenceOption.equals("POS")){
            habitatThatInfluences.updateSpeciesInfluence(specieId, 20);
        } else if (influenceOption.equals("NEU")){
            habitatThatInfluences.updateSpeciesInfluence(specieId, 0);
        } else if (influenceOption.equals("NEG")){
            habitatThatInfluences.updateSpeciesInfluence(specieId, -20);
        }

        isDirty();
    }

    /**
     * Changes the area value of a specific habitat.
     * 
     * @param habitatId                       Id of the habitat whose area we're going to change
     * @param area                            new area value
     */
    public void changeHabitatArea(String habitatId, int area) throws UnknownHabitatException{
        if (!_habitats.containsKey(habitatId)) {
            throw new UnknownHabitatException(habitatId);
        }
        Habitat changedHabitat =(Habitat) _habitats.get(habitatId);  

        changedHabitat.setArea(area);
    }

    /**
     * Returns a string representation of all trees in a specific habitat.
     * Each tree is listed on a new line.
     * If there are no trees, an empty string is returned.
     * 
     * @param habitatId                       Id of the habitat where the trees are located
     * @return String                         a string containing the details of all trees in the habitat, 
     */

    public String showAllTreesInHabitat(String habitatId) {
        Habitat habitat =(Habitat) _habitats.get(habitatId);
        List<Tree> allTrees = habitat.getTrees();
        StringBuilder result = new StringBuilder();
		for (Tree tree : allTrees) {
			result.append(tree.toString());
			result.append("\n");
		}
		if (!result.isEmpty()) {
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
    }

    /**
     * Vaccinates an animal using a specific vaccine.
     * 
     * @return int 
     * @param vaccineId                                Id of the vaccine that's going to be used
     * @param vetId                                    Id of the veterinarian that's going to use the vaccine
     * @param animalId                                 Id of the animal that's going to be vaccinated
     * @throws UnknownVeterinarianException            if the veterinarian doesn't exist
     * @throws UnknownEmployeeException                if the employee doesn't exist
     * @throws UnknownAnimalException                  if the animal doesn't exist
     * @throws VeterinarianNotAuthorizedException      if the veterinarian is not authorized to vaccinate a specie
     * @throws UnknownVaccineException                 if the vaccine doesn't exist
     */
    public int  vaccinateAnimal(String vaccineId, String vetId, String animalId) throws UnknownVeterinarianException, UnknownEmployeeException, UnknownAnimalException, VeterinarianNotAuthorizedException, UnknownVaccineException {
        if (!_employees.containsKey(vetId)) {
            throw new UnknownEmployeeException(vetId);
        }

        if (!(_employees.get(vetId)  instanceof Vet)) {
            throw new UnknownVeterinarianException(vetId);
        }

        if (!_vaccines.containsKey(vaccineId)) {
            throw new UnknownVaccineException(vaccineId);
        }
    
        Vet v = (Vet) _employees.get(vetId);
        if (!v.checkSpecies(_animals.get(animalId).getSpecie().getId())) {
            throw new VeterinarianNotAuthorizedException(vetId, _animals.get(animalId).getSpecie().getId());
        }
    
        Vaccinations vaccination = new Vaccinations(_vaccines.get(vaccineId), vetId, _animals.get(animalId).getSpecie().getId());
    
        _vaccinations.add(vaccination);
        _vaccines.get(vaccineId).addVaccination(vaccination);
        _animals.get(animalId).addVaccination(vaccination);
        Vet vet = (Vet) _employees.get(vetId);
        vet.addVaccination(vaccination);
    
        if (_animals.get(animalId).addHealthHistory(_vaccines.get(vaccineId)) != 0 || !_vaccines.get(vaccineId).getSpeciesList().contains(_animals.get(animalId).getSpecie())) {
            vet.addWrongVaccination(vaccination);
            _wrongVaccinations.add(vaccination);
            return 1;
        }
    
        isDirty();
        return 0;
    }





    /*AS MINHAS ALTERAÇÕES */
    /* 1) No DoShowAllAnimals: */
    public int numberOfAllAnimals(){
        return _animals.size();
    }

    /* 2) MOSTRAR TODOS OS ANIMAIS QUE NÃO FORAM VACINADOS */
    public String animalsWithoutVaccines(){
        StringBuilder result = new StringBuilder();
		for (Animal animal : _animals.values()) {
            if(animal.getNumberOfVaccinations() == 0){
                result.append(animal.toString());
			    result.append("\n");
            }
		}
		if (!result.isEmpty()) {
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
    }
    
    /* 3) */
    public String showHabitatsWithoutTrees(){
        StringBuilder result = new StringBuilder();
        for (Habitat habitat : _habitats.values()) {
            if (habitat.getNumberOfTreesInHabitat() == 0){
                result.append(habitat.toString());
                result.append("\n");
            }
        }
        if (!result.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    /* 4) */
    public String showVetsWithoutResponsibilities(){
        StringBuilder result = new StringBuilder();
        for (Employee employee : _employees.values()) {
            if (employee instanceof Vet) {
                Vet vet = (Vet) employee; 
                if (vet.nSpecies() == 0) {
                    result.append(vet.toString()); 
                    result.append("\n");
                }
            }
        }
        if (!result.isEmpty()) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    /* 5) numero de employees */
    public int numberOfEmployees(){
        return _employees.size();
    }

    /* 6) numero de habitats */
    public int numberOfHabitats(){
        return _habitats.size();
    }

    /* 7) numero de vacinas */
    public int numberOfVaccines(){
        return _vaccines.size();
    }

    /* 8) numero de vaccinations */
    public int numberOfVaccinations(){
        return _vaccinations.size();
    }

    /* 9) KEEPERS SEM RESPONSABILIDADES */
    public String showKeepersWithoutResponsabilities(){
        StringBuilder result = new StringBuilder();
		for (Employee employee : _employees.values()) {
            if (employee instanceof Keeper)
            Keeper keeper = (Keeper) employee;
            if (keeper.numberOfHabitats() == 0){
                result.append(keeper.toString());
			    result.append("\n");
            }	
		}
		if (!result.isEmpty()) {
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
    }

    /* 10) VACINAS SEM USO */
    public String showVaccinesWithNoUse(){
        StringBuilder result = new StringBuilder();
		for (Vaccine vaccine: _vaccines.values()) {
            if (vaccine.numberOfVaccinations() == 0){
                result.append(vaccine.toString());
			    result.append("\n");
            }	
		}
		if (!result.isEmpty()) {
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
    }
}