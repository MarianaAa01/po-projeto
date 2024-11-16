package hva.app.main;

import hva.HotelManager;
import hva.app.exceptions.FileOpenFailedException;
import hva.exceptions.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoOpenFile extends Command<HotelManager> {
    DoOpenFile(HotelManager receiver) {
        super(Label.OPEN_FILE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            if (_receiver.getHotel().getDirty()) {
                if (!Form.confirm(Prompt.saveBeforeExit())) {
                    _receiver.getHotel().cleanDirty();
                } else {
                    new DoSaveFile(_receiver).execute();
                }
            }
            String filename = Form.requestString(Prompt.openFile());
            _receiver.load(filename);
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
    }
}
