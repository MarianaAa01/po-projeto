package hva.exceptions;

import java.io.Serial;

public class DuplicateAnimalException extends Exception{

	@Serial
	private static final long serialVersionUID = 202407081733L;

	private final String key;

	public DuplicateAnimalException(String key){
		this.key = key;
	}

	public String getkey(){
		return key;
	}
}
