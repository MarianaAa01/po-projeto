package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownHabitatException;
//FIXME import other classes if needed

class DoShowVaccinesWithNoUse extends Command<Hotel> {

    DoShowVaccinesWithNoUse(Hotel receiver) {
        super(Label.VACCINES_WITH_NO_USE, receiver);
    }

    @Override
    protected void execute() throws CommandException {
            _display.popup(_receiver.showVaccinesWithNoUse());
    }

}