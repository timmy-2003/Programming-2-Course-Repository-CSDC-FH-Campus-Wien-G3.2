package at.ac.fhcampuswien.exceptions;

public class NewsAPIException extends Exception {

    private final static String ErrorMessage = "A problem happened while getting your specified articles";

    public NewsAPIException() {
        super(ErrorMessage);
    }

    public NewsAPIException(String ErMessage) {
        super(ErMessage);
    }
}