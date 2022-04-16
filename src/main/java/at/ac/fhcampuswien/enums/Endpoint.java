package at.ac.fhcampuswien.enums;

public enum Endpoint {



    EVERYTHING("everything"), TOP_HEADLINES("top-headlines"), SOURCES("top-headlines/sources");

    Endpoint(String urlParameter) {
        this.urlParameter = urlParameter;
    }

    @Override
    public String toString() {
        return this.urlParameter;
    }

    private final String urlParameter;


}
