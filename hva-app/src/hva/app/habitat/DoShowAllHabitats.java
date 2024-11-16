package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoShowAllHabitats extends Command<Hotel> {

    DoShowAllHabitats(Hotel receiver) {
        super(Label.SHOW_ALL_HABITATS, receiver);
    }

    @Override
    protected void execute() throws CommandException{
        /*Método pra treinar pro teste prático: */
        _display.popup(_receiver.numberOfHabitats());
        /*_____________________________________ */
        _display.popup(_receiver.showAllHabitats());
    }
}
