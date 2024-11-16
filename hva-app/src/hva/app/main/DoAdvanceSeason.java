package hva.app.main;

import hva.HotelManager;
import pt.tecnico.uilib.menus.Command;
//FIXME import other classes if needed

class DoAdvanceSeason extends Command<HotelManager> {
    DoAdvanceSeason(HotelManager receiver) {
        super(Label.ADVANCE_SEASON, receiver);
    }

    @Override
    protected final void execute() {
        _display.popup(_receiver.getHotel().advanceSeason());
    }
}