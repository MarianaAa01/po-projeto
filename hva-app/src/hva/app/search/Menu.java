package hva.app.search;

public class Menu extends pt.tecnico.uilib.menus.Menu {

    public Menu(hva.Hotel receiver) {
        super(Label.TITLE, //
                new DoShowAnimalsInHabitat(receiver),
                new DoShowMedicalActsOnAnimal(receiver),
                new DoShowMedicalActsByVeterinarian(receiver),
                new DoShowWrongVaccinations(receiver),
                new DoShowAnimalsWithoutVaccine(receiver),
                new DoShowHabitatsWithoutTrees(receiver),
                new DoShowKeepersWithoutResponsabilities(receiver),
                new DoShowVetsWithoutResponsabilities(receiver)
        );
    }

}
