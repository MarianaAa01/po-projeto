package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownHabitatException;
//FIXME import other classes if needed

class DoShowAnimalsWithoutVaccine extends Command<Hotel> {

    DoShowAnimalsWithoutVaccine(Hotel receiver) {
        super(Label.ANIMALS_WITHOUT_VACCINE, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        _display.popup(_receiver.showAnimalsWithoutVaccine());
    }

}