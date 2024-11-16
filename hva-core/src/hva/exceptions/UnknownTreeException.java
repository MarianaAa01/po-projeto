package hva.exceptions;

import java.io.Serial;

public class UnknownTreeException extends Exception {

    @Serial
    private static final long serialVersionUID = 202407081735L;

    private final String key;

    public UnknownTreeException(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}