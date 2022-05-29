package at.ac.fhcampuswien.exceptions;

import at.ac.fhcampuswien.gui.NewsController;
import at.ac.fhcampuswien.gui.NewsControllerAlertScreen;

public class APIKeyException extends Exception {

    private final static String ErrorMessage = "Your API Key reached it's limit, please check API Key!";

    public APIKeyException() {
        super(ErrorMessage);



    }
}
