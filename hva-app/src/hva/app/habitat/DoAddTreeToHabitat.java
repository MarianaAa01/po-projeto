package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.exceptions.DuplicateTreeException;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.DuplicateTreeKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;

class DoAddTreeToHabitat extends Command<Hotel> {

    DoAddTreeToHabitat(Hotel receiver) {
        super(Label.ADD_TREE_TO_HABITAT, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addStringField("treeKey", Prompt.treeKey());
        addStringField("treeName", Prompt.treeName());
        addIntegerField("treeAge", Prompt.treeAge());
        addIntegerField("treeDifficulty", Prompt.treeDifficulty());
        addStringField("treeType", Prompt.treeType());
    }

    @Override
    protected void execute() throws CommandException {
        try{
            _display.popup(_receiver.registerTreeIn(stringField("habitatKey"), 
                                stringField("treeKey"), 
                                stringField("treeName"), 
                                integerField("treeAge"), 
                                integerField("treeDifficulty"), 
                                stringField("treeType")));
        } catch (DuplicateTreeException e){
            throw new DuplicateTreeKeyException(stringField("treeKey"));
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(stringField("habitatKey"));
        }
    }

}
