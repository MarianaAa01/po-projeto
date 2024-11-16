package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownHabitatException;
//FIXME import other classes if needed

class DoShowAnimalsInHabitat extends Command<Hotel> {

    DoShowAnimalsInHabitat(Hotel receiver) {
        super(Label.ANIMALS_IN_HABITAT, receiver);
        addStringField("HabitatKey", hva.app.habitat.Prompt.habitatKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            _display.popup(_receiver.showAnimalsInHabitat(stringField("HabitatKey")));
        } catch (UnknownHabitatException e) {}
    }

}