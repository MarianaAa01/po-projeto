package hva.app.vaccine;

import hva.Hotel;
import java.awt.DisplayMode;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowAllVaccines extends Command<Hotel> {

    DoShowAllVaccines(Hotel receiver) {
        super(Label.SHOW_ALL_VACCINES, receiver);
	//FIXME add command fields if needed
    }

    @Override
    protected final void execute() {
        /*Método para treinar pro teste prático: */
        _display.popup(_receiver.numberOfVaccines());
        /*______________________________________ */
        _display.popup(_receiver.showAllVaccines());
    }
}
