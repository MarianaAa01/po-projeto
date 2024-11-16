package hva.app.main;

import hva.HotelManager;
import hva.exceptions.UnknownAnimalException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.exceptions.UnknownEmployeeException;

class DoShowGlobalSatisfaction extends Command<HotelManager> {
    DoShowGlobalSatisfaction(HotelManager receiver) {
        super(hva.app.main.Label.SHOW_GLOBAL_SATISFACTION, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _display.popup(_receiver.getHotel().globalSatisfaction());
        } catch (UnknownAnimalException e) {}
        catch (UnknownEmployeeException e) {}
    }
}
