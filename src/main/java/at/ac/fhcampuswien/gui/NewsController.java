package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class NewsController {
    private AppController ctrl = new AppController();
    private boolean isLightMode = false;
    Timer timer = new Timer();
    private double x, y = 0;

    @FXML
    private AnchorPane parent;

    @FXML
    private AnchorPane anchormid;

    @FXML
    private Button exitButton;
    @FXML
    private Button btnCount;

    @FXML
    private Button logoButton;

    @FXML
    private AnchorPane topanchor;

    @FXML
    private VBox vboxleftside;

    @FXML
    private Button btnGetToplinesAustria;

    @FXML
    private Button btnGetToplinesBitcoin;

    @FXML
    private TableView<Article> tvNews;
    @FXML
    private TableColumn<Article, String> title;
    @FXML
    private TableColumn<Article, String> author;

    @FXML
    private Label lblCount;

    @FXML
    private Label lblDashboard;

    @FXML
    private GridPane gridPane;


    @FXML
    private ImageView imgCountArticles;

    @FXML
    private ImageView imgViewAustria;

    @FXML
    private ImageView imgViewBitcoin;

    @FXML
    private ImageView imgViewCounter;

    @FXML
    private Tab tabDashboard;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabSettings;

    /***
     * when the fxml starts the standard text gets replaced by this placeholder
     */
    @FXML
    void initialize() {
        tvNews.setPlaceholder(new Label(""));
        // start with this css
        parent.getStylesheets().add(String.valueOf(getClass().getResource("/css/darkmode.css")));
    }

    /***
     * when you press count articles you get the articles displayed
     */
    @FXML
    void showArticleCount() throws IOException {

        countArticles(0); //count articles in full list
    }

    /***
     * test list for testing, replacing with actual list when ready with filtering "austria"
     * @param event
     */
    @FXML
    void GetTopLinesAustria(ActionEvent event) throws IOException {
        getList("austria");
        countArticles(1); //count articles displayed
    }

    /***
     * test list for testing, replacing with actual list when ready with filtering "bitcoin"
     * @param event
     */
    @FXML
    void GetTopLinesBitcoin(ActionEvent event) throws IOException {
        getList("bitcoin");
        countArticles(1); //count articles displayed
    }

    /***
     * switch to a different color pattern inside the GUI e.g. dark/white mode (not finished)
     * @param event
     */
    @FXML
    void changeColorPattern(ActionEvent event) {

        isLightMode = !isLightMode;
        if (isLightMode) {
            setLightMode();
        } else {
            setDarkMode();
        }
    }

    /***
     * switch the tabs within the tabPane to Dashboard
     * @param event
     */
    @FXML
    void dashboardSwitch(ActionEvent event) {
        tabPane.getSelectionModel().select(0);
    }

    /***
     * switch the tabs within the tabPane to Settings
     * @param event
     */
    @FXML
    void dashboardSwitchSettings(ActionEvent event) {
        tabPane.getSelectionModel().select(1);
    }

    /***
     * calls function exitWindow() when clicking on exit button
     * @param event
     */
    @FXML
    void exitWindow(ActionEvent event) {
        exitWindow();
    }

    /***
     * window get's maximized or windowed when clicking the window button
     * @param event
     */
    @FXML
    void maximizeWindow(MouseEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (currentStage.isMaximized())
            currentStage.setMaximized(false);
        else
            currentStage.setMaximized(true);
    }

    /***
     * window get's minimized when clicking the minimize button
     * @param event
     */
    @FXML
    void minimizeWindow(MouseEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setIconified(true);
    }

    /***
     * set new positions of window when getting dragged
     * @param event
     */
    @FXML
    void windowDragged(MouseEvent event) {
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    /***
     * get x,y coordinates of parent anchorpane when pressed
     * @param event
     */
    @FXML
    void windowPressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /***
     * set new positions of window when getting dragged
     * @param event
     */
    @FXML
    void windowDragged2(MouseEvent event) {
        Stage stage = (Stage) anchormid.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    /***
     * get x,y coordinates of anchormid when pressed
     * @param event
     */
    @FXML
    void windowPressed2(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /***
     * apply the light mode to the main GUI
     */
    private void setLightMode() {
        parent.getStylesheets().remove(String.valueOf(getClass().getResource("/css/darkmode.css")));
        parent.getStylesheets().add(String.valueOf(getClass().getResource("/css/lightmode.css")));
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/at_light.png")));
        imgViewAustria.setImage(image);
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bitcoin_light.png")));
        imgViewBitcoin.setImage(image2);
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/counter_light.png")));
        imgViewCounter.setImage(image3);

    }

    /***
     * apply the dark mode to the main GUI
     */
    private void setDarkMode() {
        parent.getStylesheets().remove(String.valueOf(getClass().getResource("/css/lightmode.css")));
        parent.getStylesheets().add(String.valueOf(getClass().getResource("/css/darkmode.css")));
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/at2.png")));
        imgViewAustria.setImage(image);
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bitcoin.png")));
        imgViewBitcoin.setImage(image2);
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/counter2.png")));
        imgViewCounter.setImage(image3);
    }


    /***
     * convert a List into an ObservableList
     * @param articleList
     * @return
     */
    private ObservableList<Article> getObservableListFromList(List<Article> articleList) {
        ObservableList<Article> articleListO = FXCollections.observableArrayList(articleList);
        return articleListO;
    }

    /***
     * get List based on headline the user want
     * @param query
     */
    private void getList(String query) throws IOException {
        author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        switch (query.toLowerCase()) {
            case "austria" -> tvNews.setItems(getObservableListFromList(ctrl.getTopHeadlinesAustria()));
            case "bitcoin" -> tvNews.setItems(getObservableListFromList(ctrl.getAllNewsBitcoin()));
            case "" -> tvNews.setItems(getObservableListFromList(ctrl.getArticles()));
        }
    }

    /***
     * count articles based on which articles you want to count
     * @param tableview
     */
    private void countArticles(int tableview) throws IOException {

        switch (tableview) {
            case 0 -> countArticlesInFullList();
            case 1 -> countArticlesDisplayed();
            default -> countArticlesInFullList();
        }
    }

    /***
     * count all articles in mocklist
     */
    private void countArticlesInFullList() throws IOException {
        if (ctrl.getArticleCount() == 1) {
            lblCount.setText("  1 article");
        } else {
            lblCount.setText("  " + String.valueOf(ctrl.getArticleCount()) + " articles");
        }
        getList("");
    }

    /***
     * count all articles displayed in tableview
     */
    private void countArticlesDisplayed() {
        if (tvNews.getItems().size() == 1) {
            lblCount.setText("  1 article");
        } else {
            lblCount.setText("  " + String.valueOf(tvNews.getItems().size()) + " articles");
        }
    }

    /***
     * exit window
     */
    private void exitWindow() {
        System.exit(1);
    }
}