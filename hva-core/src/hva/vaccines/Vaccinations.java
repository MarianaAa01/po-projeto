package hva.vaccines;

import java.io.Serializable;

public class Vaccinations implements Serializable {
    private Vaccine _vaccine;
    private String _vet_id;
    private String _species_id;

    public Vaccinations(Vaccine vaccine, String vet_id, String species_id){
        _vet_id = vet_id;
        _species_id = species_id;
        _vaccine = vaccine;
    }

    public String getVetId() {
        return _vet_id;
    }

    public void setVetId(String vet_id) {
        _vet_id = vet_id;
    }

    public String getSpeciesId() {
        return _species_id;
    }

    public void setAnimalId(String species_id) {
        _species_id = species_id;
    }

    public Vaccine getVaccineId() {
        return _vaccine;
    }

    public void setVaccineId(Vaccine vaccine) {
        _vaccine = vaccine;
    }

    @Override
    public String toString() {
        return  "REGISTO-VACINA" + "|"
                + _vaccine.getId() + "|"
                + _vet_id + "|"
                + _species_id;
    }
}
