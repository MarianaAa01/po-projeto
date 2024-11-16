package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.app.exceptions.DuplicateHabitatKeyException;
import hva.exceptions.DuplicateHabitatException;


class DoRegisterHabitat extends Command<Hotel> {

    DoRegisterHabitat(Hotel receiver) {
        super(Label.REGISTER_HABITAT, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addStringField("habitatName", Prompt.habitatName());
        addIntegerField("habitatArea", Prompt.habitatArea());
    }

    @Override
    protected void execute() throws CommandException {
        try {
			_receiver.registerHabitatIn(
					stringField("habitatKey"),
					stringField("habitatName"),
				    integerField("habitatArea"));
		} catch (DuplicateHabitatException e) {
			throw new DuplicateHabitatKeyException(stringField("habitatKey"));
		}
    }

} 