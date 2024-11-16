package hva.app.employee;

import hva.Hotel;
import hva.exceptions.DuplicateEmployeeException;
import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
class DoRegisterEmployee extends Command<Hotel> {

    DoRegisterEmployee(Hotel receiver) {
        super(Label.REGISTER_EMPLOYEE, receiver);
        addStringField("employeeId", Prompt.employeeKey());
        addStringField("employeeName", Prompt.employeeName());
        addOptionField("employeeType", Prompt.employeeType(), new String[] {"VET", "TRT"});
    }

    @Override
    protected void execute() throws CommandException {
        try {
        _receiver.registerEmpolyee( stringField("employeeId"), 
                                    stringField("employeeName"), 
                                    stringField("employeeType"));
        } catch (DuplicateEmployeeException e) {
            throw new DuplicateEmployeeKeyException(stringField("employeeId"));
        } catch (UnknownSpeciesException e) {}
         catch (UnknownHabitatException e) {}
    }
}
