package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


import hva.exceptions.DuplicateAnimalException;
import hva.app.exceptions.DuplicateAnimalKeyException;
import hva.exceptions.DuplicateSpeciesException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.DuplicateHabitatException;
import hva.app.exceptions.UnknownHabitatKeyException;

class DoRegisterAnimal extends Command<Hotel> {
    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
        addStringField("animalKey", Prompt.animalKey());
        addStringField("animalName", Prompt.animalName());
        addStringField("animalSpecieKey", Prompt.speciesKey());
        addStringField("animalHabitat", hva.app.habitat.Prompt.habitatKey());
    }

    @Override
    protected final void execute() throws CommandException{
        try {
            if (!_receiver.checkSpecies(stringField("animalSpecieKey"))) {
                String speciesName = Form.requestString(Prompt.speciesName());
                try {
                _receiver.registerSpecie(stringField("animalSpecieKey"), speciesName);
                } catch (DuplicateSpeciesException e1) {}
            }
			_receiver.registerAnimal(
					stringField("animalKey"),
					stringField("animalName"),
				    stringField("animalSpecieKey"),
                    stringField("animalHabitat"));
		} catch (DuplicateAnimalException e) {
			throw new DuplicateAnimalKeyException(stringField("animalKey"));
		} catch (DuplicateSpeciesException e) {
        } catch (DuplicateHabitatException e) {
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(stringField("animalHabitat"));
        } catch (UnknownSpeciesException e) {
        }

    }
}