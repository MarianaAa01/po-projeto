package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowHabitatsWithoutTrees extends Command<Hotel> {

    DoShowHabitatsWithoutTrees(Hotel receiver) {
        super(Label.HABITATS_WITHOUT_TREES, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        _display.popup(_receiver.showHabitatsWithoutTrees());
    }
}