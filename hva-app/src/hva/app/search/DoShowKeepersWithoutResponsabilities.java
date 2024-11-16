package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownHabitatException;
//FIXME import other classes if needed

class DoShowKeepersWithoutResponsabilities extends Command<Hotel> {

    DoShowKeepersWithoutResponsabilities(Hotel receiver) {
        super(Label.KEEPERS_WITHOUT_RESPONSABILITIES, receiver);
    }

    @Override
    protected void execute() throws CommandException {
            _display.popup(_receiver.DoShowKeepersWithoutResponsabilities());
    }

}