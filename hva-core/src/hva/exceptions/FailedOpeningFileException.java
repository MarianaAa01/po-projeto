package hva.exceptions;

import java.io.Serial;


public class FailedOpeningFileException extends Exception {

	@Serial
	private static final long serialVersionUID = 202208091753L;

	String _filename;

	/**
	 * @param filename 
	 */
	public FailedOpeningFileException(String filename) {
	  super("Erro a processar ficheiro " + filename);
	  _filename = filename;
	}

	/**
	 * @return the requested filename
	 */
	public String getFilename() {
		return _filename;
	}

}