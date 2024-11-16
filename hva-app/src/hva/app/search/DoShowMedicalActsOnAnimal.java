package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownAnimalException;
import hva.app.exceptions.UnknownAnimalKeyException;
//FIXME import other classes if needed

class DoShowMedicalActsOnAnimal extends Command<Hotel> {

    DoShowMedicalActsOnAnimal(Hotel receiver) {
        super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
        addStringField("AnimalId", hva.app.animal.Prompt.animalKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            _display.popup(_receiver.showAnimalsVaccinations(stringField("AnimalId")));
        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(stringField("AnimalId"));
        }
    }

}
