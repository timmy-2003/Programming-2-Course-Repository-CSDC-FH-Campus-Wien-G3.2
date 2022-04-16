package at.ac.fhcampuswien.enums;

public enum SortBy {

    PUBLISHEDAT("sortBy=publishedAt"),  //default
    RELEVANCY("sortBy=relevancy"),
    POPULARITY("sortBy=popularity"),;

    private final String urlParameter;

    SortBy(String urlParameter) {
        this.urlParameter = urlParameter;
    }


    @Override
    public String toString() {
        return this.urlParameter;
    }
}
