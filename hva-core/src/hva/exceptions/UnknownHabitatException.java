package hva.exceptions;

import java.io.Serial;

public class UnknownHabitatException extends Exception {

    @Serial
    private static final long serialVersionUID = 202407081735L;

    private final String key;

    public UnknownHabitatException(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}