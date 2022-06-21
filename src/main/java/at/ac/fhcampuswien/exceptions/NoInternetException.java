package at.ac.fhcampuswien.exceptions;

import at.ac.fhcampuswien.gui.NewsController;

import java.io.IOException;

public class NoInternetException extends Exception {

    private static final String exceptionMessage = "There was a problem regarding your internet connection.";

    NewsController nC = new NewsController();

    public NoInternetException() throws IOException {
        super(exceptionMessage);
        nC.alert(exceptionMessage);
    }

    public NoInternetException(String msg) throws IOException {
        super(msg);
        nC.alert(msg);
    }

}


