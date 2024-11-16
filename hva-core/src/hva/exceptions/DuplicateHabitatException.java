package hva.exceptions;

import java.io.Serial;

public class DuplicateHabitatException extends Exception{

	@Serial
	private static final long serialVersionUID = 202407081733L;

	private final String key;

	public DuplicateHabitatException(String key){
		this.key = key;
	}

	public String getkey(){
		return key;
	}
}