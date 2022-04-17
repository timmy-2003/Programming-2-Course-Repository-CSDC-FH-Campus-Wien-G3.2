package at.ac.fhcampuswien.enums;

public enum Category {
    BUSINESS ("category=business"),
    ENTERTAINMENT ("category=entertainment"),
    GENERAL ("category=general"),
    HEALTH ("category=health"),
    SCIENCE ("category=science"),
    SPORTS ("category=sports"),
    TECHNOLOGY ("category=technology");

    private final String urlParameter;

    Category(String urlParameter) {
        this.urlParameter = urlParameter;
    }


    @Override
    public String toString() {
        return this.urlParameter;
    }
}
