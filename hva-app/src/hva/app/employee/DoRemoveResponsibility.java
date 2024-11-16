package hva.app.employee;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.app.exceptions.NoResponsibilityException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;

class DoRemoveResponsibility extends Command<Hotel> {

    DoRemoveResponsibility(Hotel receiver) {
        super(Label.REMOVE_RESPONSABILITY, receiver);
        addStringField("EmployeeId", Prompt.employeeKey());
        addStringField("responsibilityId", Prompt.responsibilityKey());

    }

    @Override
    protected void execute() throws CommandException {
        try {
            _receiver.removeResponsability(stringField("EmployeeId"), 
                                           stringField("responsibilityId"));
        } catch (UnknownEmployeeException e) {
            throw new NoResponsibilityException(stringField("EmployeeId"), stringField("responsibilityId"));
        } catch (UnknownHabitatException e) {
            throw new NoResponsibilityException(stringField("EmployeeId"), stringField("responsibilityId"));
        } catch (UnknownSpeciesException e) {
            throw new NoResponsibilityException(stringField("EmployeeId"), stringField("responsibilityId"));
        }
    }

}
