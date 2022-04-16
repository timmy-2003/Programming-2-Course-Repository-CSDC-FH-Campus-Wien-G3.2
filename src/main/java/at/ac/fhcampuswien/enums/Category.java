package at.ac.fhcampuswien.enums;

public enum Category {
    BUSINESS ("business"),
    ENTERTAINMENT ("entertainment"),
    GENERAL ("general"),
    HEALTH ("health"),
    SCIENCE ("science"),
    SPORTS ("sports"),
    TECHNOLOGY ("technology");

    private final String urlParameter;

    Category(String urlParameter) {
        this.urlParameter = urlParameter;
    }


    @Override
    public String toString() {
        return this.urlParameter;
    }
}
