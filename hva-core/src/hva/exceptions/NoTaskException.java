package hva.exceptions;

import java.io.Serial;

public class NoTaskException extends Exception {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    public NoTaskException(String employeeKey, String responsibilityKey) {
        super("Responsabilidade (habitat ou espécie) '" + responsibilityKey +
                "' não atribuída ao funcionário '" + employeeKey + "'.");
    }
}
