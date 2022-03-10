/***
 * solving "Error: JavaFX runtime components are missing, and are required to run this application" this error
 */
module newsApp {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens at.ac.fhcampuswien;
}
