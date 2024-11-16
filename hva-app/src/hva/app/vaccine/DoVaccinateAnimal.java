package hva.app.vaccine;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownVeterinarianException;
import hva.exceptions.VeterinarianNotAuthorizedException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownVaccineException;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.app.exceptions.UnknownVaccineKeyException;
import hva.app.exceptions.UnknownVeterinarianKeyException;
//FIXME import other classes if needed

class DoVaccinateAnimal extends Command<Hotel> {

    DoVaccinateAnimal(Hotel receiver) {
        super(Label.VACCINATE_ANIMAL, receiver);
       addStringField("VaccineId", Prompt.vaccineKey());
       addStringField("VetId", Prompt.veterinarianKey());
       addStringField("AnimalId", hva.app.animal.Prompt.animalKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _display.popup(_receiver.vaccinateAnimal(stringField("VaccineId"),
                                      stringField("VetId"),
                                      stringField("AnimalId")) != 0 ? 
                                    Message.wrongVaccine(stringField("VaccineId"), stringField("AnimalId")) : "");
            } catch (UnknownAnimalException e) {
                throw new UnknownAnimalKeyException(stringField("AnimalId"));
            } catch (VeterinarianNotAuthorizedException e) {
                throw new hva.app.exceptions.VeterinarianNotAuthorizedException(stringField("VetId"), stringField("VaccineId"));
            } catch (UnknownEmployeeException e) {
                throw new hva.app.exceptions.UnknownEmployeeKeyException(stringField("VetId"));
            } catch (UnknownVeterinarianException e) {
                throw new UnknownVeterinarianKeyException(stringField("VetId"));
            } catch (UnknownVaccineException e) {
                throw new UnknownVaccineKeyException(stringField("VaccineId"));
            }
    }

}
