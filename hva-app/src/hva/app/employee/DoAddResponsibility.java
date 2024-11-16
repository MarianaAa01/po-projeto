package hva.app.employee;

import hva.Hotel;
import hva.app.exceptions.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;



//FIXME import other classes if needed

class DoAddResponsibility extends Command<Hotel> {

    DoAddResponsibility(Hotel receiver) {
        super(Label.ADD_RESPONSABILITY, receiver);
        addStringField("WorkerId", Prompt.employeeKey());
        addStringField("ResponsabilityId", Prompt.responsibilityKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            _receiver.addResponsability(stringField("WorkerId"), 
                                        stringField("ResponsabilityId"));
        } catch (UnknownEmployeeException e) {
            throw new UnknownEmployeeKeyException(stringField("WorkerId"));
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(stringField("ResponsabilityId"));
        } catch (UnknownSpeciesException e) {
            throw new UnknownSpeciesKeyException(stringField("ResponsabilityId"));
        }
    } 

}
