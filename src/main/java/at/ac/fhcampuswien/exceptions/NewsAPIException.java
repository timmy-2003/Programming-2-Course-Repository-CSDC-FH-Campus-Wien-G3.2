package at.ac.fhcampuswien.exceptions;

public class NewsAPIException extends Exception {

    private final static String ErrorMessage = "A problem happened while getting your specified articles";

    NewsAPIException() {
        super(ErrorMessage);
    }

    NewsAPIException(String ErMessage) {
        super(ErMessage);
    }
}