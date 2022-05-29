package at.ac.fhcampuswien.exceptions;

public class HttpException extends Exception {

    private final static String ErrorMessage = "Status of API Response is not 'ok'!";
    public HttpException() {
        super(ErrorMessage);
    }

    HttpException(String message) {
        super(ErrorMessage);
    }

    public static boolean checkStatus(String response) throws HttpException {
        //check if status is "ok"
        String rString = response.replaceAll("[^a-zA-Z]+", "").toLowerCase();
        return rString.contains("statusok");
    }
}
