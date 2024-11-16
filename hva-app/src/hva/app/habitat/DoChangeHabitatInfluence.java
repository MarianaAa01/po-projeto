package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;


class DoChangeHabitatInfluence extends Command<Hotel> {

    DoChangeHabitatInfluence(Hotel receiver) {
        super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addStringField("specieKey", hva.app.animal.Prompt.speciesKey());
        addOptionField("habitatInfluence", hva.app.habitat.Prompt.habitatInfluence(), new String[] {"POS", "NEU", "NEG"} );
    }

    @Override
    protected void execute() throws CommandException {
        try {
        _receiver.changeInfluence(stringField("habitatKey"), 
                                  stringField("specieKey"),
                                  optionField("habitatInfluence"));
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(stringField("habitatKey"));
        } catch (UnknownSpeciesException e) {
            throw new UnknownSpeciesKeyException(stringField("specieKey"));
        }
    }
}