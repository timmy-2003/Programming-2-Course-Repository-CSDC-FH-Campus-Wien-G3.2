package at.ac.fhcampuswien.exceptions;

import at.ac.fhcampuswien.gui.NewsController;

import java.io.IOException;

public class urlException extends Exception {


    private static final String exceptionMessage = "Check API request URL! Parameters may not have been set properly.";

    NewsController nC = new NewsController();

    public urlException() throws IOException {
        super(exceptionMessage);
        nC.alert(exceptionMessage);
    }

    public urlException(String msg) throws IOException {
        super(msg);
        nC.alert(msg);
    }


}
