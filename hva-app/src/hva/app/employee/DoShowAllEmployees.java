package hva.app.employee;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowAllEmployees extends Command<Hotel> {

    DoShowAllEmployees(Hotel receiver) {
        super(Label.SHOW_ALL_EMPLOYEES, receiver);
    }

    @Override
    protected void execute() throws CommandException {
        /*Método pra treinar pro teste prático: */
        _display.popup(_receiver.numberOfEmployees());
        /*_____________________________________ */
        _display.popup(_receiver.showAllEmployees());
    }

}
