package at.ac.fhcampuswien;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class NewsController {
    private AppController ctrl = new AppController();
    private boolean isLightMode = false;
    Timer timer = new Timer();

    @FXML
    private AnchorPane parent;

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
    private ImageView imgCountArticles;

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
     * when you press exit then the application get's closed with delay
     */
    @FXML
    public void exitApplication() {
        exitButton.setText("See you soon!");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    System.exit(0);
                });
            }
        }, 1000l);
    }


    /***
     * when you press count articles you get the articles displayed
     */
    @FXML
    void showArticleCount() {

        countArticles(0); //count articles in full list
    }

    /***
     * test list for testing, replacing with actual list when ready with filtering "austria"
     * @param event
     */
    @FXML
    void GetTopLinesAustria(ActionEvent event) {
        getList("austria");
        countArticles(1); //count articles displayed
    }

    /***
     * test list for testing, replacing with actual list when ready with filtering "bitcoin"
     * @param event
     */
    @FXML
    void GetTopLinesBitcoin(ActionEvent event) {
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

    @FXML
    void dashboardSwitch(ActionEvent event) {
        tabPane.getSelectionModel().select(0);
    }

    /***
     * apply the light mode to the main GUI
     */
    private void setLightMode() {
        parent.getStylesheets().remove(String.valueOf(getClass().getResource("/css/darkmode.css")));
        parent.getStylesheets().add(String.valueOf(getClass().getResource("/css/lightmode.css")));
        //Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/articleCounter.png")));
       // imgCountArticles.setImage(image);

    }

    /***
     * apply the dark mode to the main GUI
     */
    private void setDarkMode() {
        parent.getStylesheets().remove(String.valueOf(getClass().getResource("/css/lightmode.css")));
        parent.getStylesheets().add(String.valueOf(getClass().getResource("/css/darkmode.css")));
        //Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/newsapp.png")));
        //imgCountArticles.setImage(image);
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
    private void getList(String query) {
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
    private void countArticles(int tableview) {

        switch (tableview) {
            case 0 -> countArticlesInFullList();
            case 1 -> countArticlesDisplayed();
            default -> countArticlesInFullList();
        }
    }

    /***
     * count all articles in mocklist
     */
    private void countArticlesInFullList() {
        if (ctrl.getArticleCount() == 1) {
            lblCount.setText("  1 article");
        } else {
            lblCount.setText("  " + String.valueOf(ctrl.getArticleCount()) + " articles");
        }
        getList("");
        /*timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    lblCount.setText("  Count articles");
                });
            }
        }, 2000l);*/
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
}