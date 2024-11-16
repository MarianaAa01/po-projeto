package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownHabitatException;
//FIXME import other classes if needed

class DoShowVetsWithoutResponsabilities extends Command<Hotel> {

    DoShowAnimalsInHabitat(Hotel receiver) {
        super(Label.VETS_WITHOUT_RESPONSABILITIES, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        try {
            _display.popup(_receiver.showVetsWithoutResponsabilities()));
        } catch (UnknownHabitatException e) {}
    }

}
