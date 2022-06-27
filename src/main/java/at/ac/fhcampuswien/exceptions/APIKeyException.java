package at.ac.fhcampuswien.exceptions;

import at.ac.fhcampuswien.gui.NewsController;
import at.ac.fhcampuswien.gui.NewsControllerAlertScreen;

import java.io.IOException;

public class APIKeyException extends Exception {

    private final static String ErrorMessage = "Your API Key is invalid or missing!";
    NewsController nC = new NewsController();

    public APIKeyException() throws IOException {
        super(ErrorMessage);
        nC.alert(ErrorMessage);

    }

    public APIKeyException(String msg) throws IOException {
        super(msg); //overwrite the message here
        nC.alert(msg);
    }
}
