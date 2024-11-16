package hva.exceptions;

import java.io.Serial;

public class DuplicateEmployeeException extends Exception{

	@Serial
	private static final long serialVersionUID = 202407081733L;

	private final String key;

	public DuplicateEmployeeException(String key){
		this.key = key;
	}

	public String getkey(){
		return key;
	}
}