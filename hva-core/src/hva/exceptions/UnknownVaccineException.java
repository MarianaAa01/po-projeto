package hva.exceptions;

import java.io.Serial;

public class UnknownVaccineException extends Exception {

    @Serial
    private static final long serialVersionUID = 202407081735L;

    private final String key;

    public UnknownVaccineException(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}