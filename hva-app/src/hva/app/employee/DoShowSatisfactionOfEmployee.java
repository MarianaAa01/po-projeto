package hva.app.employee;

import hva.Hotel;
import hva.app.exceptions.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.exceptions.UnknownEmployeeException;

//FIXME import other classes if needed

class DoShowSatisfactionOfEmployee extends Command<Hotel> {

    DoShowSatisfactionOfEmployee(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
        addStringField("EmployeeId", Prompt.employeeKey());

    }

    @Override
    protected void execute() throws CommandException {
        try {
            _display.popup(_receiver.satisfactionOfEmployee(stringField("EmployeeId")));
        } catch (UnknownEmployeeException e) {
            throw new UnknownEmployeeKeyException(stringField("EmployeeId"));
        }
    }

}
