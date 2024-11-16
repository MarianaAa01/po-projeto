package hva.exceptions;

import java.io.Serial;

public class VeterinarianNotAuthorizedException extends Exception {

    @Serial
    private static final long serialVersionUID = 202407081735L;

    private final String vetId;
    private final String animalSpecieId;

    public VeterinarianNotAuthorizedException(String vetId, String animalSpecieId) {
        this.vetId = vetId;
        this.animalSpecieId = animalSpecieId;
    }

    @Override
    public String getMessage() {
        return "Veterinarian with ID " + vetId + " is not authorized to treat animal species with ID " + animalSpecieId;
    }
}