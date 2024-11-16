package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.exceptions.UnknownAnimalException;
import hva.app.exceptions.UnknownAnimalKeyException;
//FIXME import other classes if needed

class DoShowSatisfactionOfAnimal extends Command<Hotel> {

    DoShowSatisfactionOfAnimal(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
        addStringField("animalKey", Prompt.animalKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _display.popup(_receiver.satisfactionOfAnimal(stringField("animalKey")));
        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(stringField("animalKey"));
        }
    }

}

/*apresentar o valor da satisfação deste animal arredondado com o Math.round */