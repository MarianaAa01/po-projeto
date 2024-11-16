package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
//FIXME import other classes if needed
import pt.tecnico.uilib.menus.CommandException;

class DoShowAllAnimals extends Command<Hotel> {

    DoShowAllAnimals(Hotel receiver) {
        super(Label.SHOW_ALL_ANIMALS, receiver);
    }

    @Override
    protected final void execute() throws CommandException{
        /*Mudança do teste prático: */
        _display.popup(_receiver.numberOfAnimals());
        /*_________________ */
        _display.popup(_receiver.showAllAnimals());
    }
}
