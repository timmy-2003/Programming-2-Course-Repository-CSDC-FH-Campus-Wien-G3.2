/***
 * solving "Error: JavaFX runtime components are missing, and are required to run this application" this error
 */
module newsApp {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;
    requires com.jfoenix;
    requires json.simple;


    opens at.ac.fhcampuswien;
    opens at.ac.fhcampuswien.apiStuff;
    opens at.ac.fhcampuswien.enums;
    opens at.ac.fhcampuswien.gui;
}
