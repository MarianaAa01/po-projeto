package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.UnknownHabitatKeyException;


class DoChangeHabitatArea extends Command<Hotel> {

    DoChangeHabitatArea(Hotel receiver) {
        super(Label.CHANGE_HABITAT_AREA, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addIntegerField("habitatArea", Prompt.habitatArea());
    }

    @Override
    protected void execute() throws CommandException {
        try {
        _receiver.changeHabitatArea(stringField("habitatKey"),
                                    integerField("habitatArea"));
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(stringField("habitatKey"));
        }
    }
}
