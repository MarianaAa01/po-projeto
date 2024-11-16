package hva.exceptions;

import java.io.Serial;

public class DuplicateSpeciesException extends Exception{

	@Serial
	private static final long serialVersionUID = 202407081733L;

	private final String key;

	public DuplicateSpeciesException(String key){
		this.key = key;
	}

	public String getkey(){
		return key;
	}
}