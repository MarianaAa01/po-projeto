package hva.exceptions;

public class WrongVaccineException extends Exception {
    public WrongVaccineException() {
        super();
    }

    public WrongVaccineException(String message) {
        super(message);
    }

    public WrongVaccineException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongVaccineException(Throwable cause) {
        super(cause);
    }
}