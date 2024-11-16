package hva.app.vaccine;

import hva.Hotel;
import hva.app.exceptions.DuplicateVaccineKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;
import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.DuplicateVaccineException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoRegisterVaccine extends Command<Hotel> {

    DoRegisterVaccine(Hotel receiver) {
        super(Label.REGISTER_VACCINE, receiver);
	    addStringField("VaccineId", Prompt.vaccineKey());
        addStringField("VaccineName", Prompt.vaccineName());
        addStringField("SpeciesList", Prompt.listOfSpeciesKeys());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
        _receiver.registerVaccineIn(stringField("VaccineId"), 
                                    stringField("VaccineName"), 
                                    stringField("SpeciesList"));
        } catch (DuplicateVaccineException e) {
            throw new DuplicateVaccineKeyException(stringField("VaccineId"));
        } catch (UnknownSpeciesException e) {
            throw new UnknownSpeciesKeyException(e.getKey());
        }
    }
}
