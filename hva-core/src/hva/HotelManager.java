package hva;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import hva.exceptions.UnavailableFileException;
import hva.exceptions.ImportFileException;
import hva.exceptions.UnrecognizedEntryException;
import hva.exceptions.MissingFileAssociationException;



/**
 * Class that represents the hotel application.
 */
public class HotelManager {

    /** This is the current hotel. */
    private Hotel _hotel = new Hotel();

    //We store the files here:
    private String _filename = "";

    // FIXME maybe add more fields if needed

    public Hotel getHotel() {
        return _hotel;
    }

    public void setHotel(Hotel hotel) {
        _hotel = hotel;
    }

    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
        if (_filename == null || _filename.equals("")) {
      		throw new MissingFileAssociationException();
        }
        if (_hotel.getDirty()) {
		    try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(_filename)))) {
    	    oos.writeObject(_hotel);
            _hotel.cleanDirty();
            }
        }
    }

    public void createFile() {
        _filename = "";
        setHotel(new Hotel());
    }

    /**
     * Saves the serialized application's state into the file associated to the current network.
     *
     * @throws FileNotFoundException if for some reason the file cannot be created or opened.
     * @throws MissingFileAssociationException if the current network does not have a file.
     * @throws IOException if there is some error while serializing the state of the network to disk.
     */
    public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
        _filename = filename;
        save();
    }

    /**
     * @param filename name of the file containing the serialized application's state
     *        to load.
     * @throws UnavailableFileException if the specified file does not exist or there is
     *         an error while processing this file.
     */
    public void load(String filename) throws UnavailableFileException {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            _hotel = (Hotel)ois.readObject();
            _filename = filename;
        } catch (IOException | ClassNotFoundException e) {
          throw new UnavailableFileException(filename);
        }
    }
    
    /**
    * Read text input file.
    *
    * @param filename name of the text input file
    * @throws ImportFileException
    */
    public void importFile(String filename) throws ImportFileException {
        try {
            _hotel.importFile(filename);
        }
        catch (IOException | UnrecognizedEntryException e){
            throw new ImportFileException(filename, e);
        }
    }
}

   