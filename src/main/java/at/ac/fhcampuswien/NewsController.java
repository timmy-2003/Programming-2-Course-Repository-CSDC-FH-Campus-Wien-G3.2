package at.ac.fhcampuswien;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


//needed: 4 BUTTONS
// ELEMENT for "get top headlines austria || ELEMENT for "get all news btc" || LABEL or smth for display Count Articles || QUIT Program - maybe also button and Alert -> "quitting text"
public class NewsController {
    private AppController ctrl = new AppController();
    Timer timer = new Timer();

    @FXML
    private AnchorPane parent;

    @FXML
    private Button exitButton;
    @FXML
    private Button countButton;

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

    private boolean isLightMode = true;

    /***
     * when the fxml starts the standard text gets replaced by this placeholder
     */
    @FXML
    void initialize() {
        tvNews.setPlaceholder(new Label(""));
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

        if (ctrl.getArticleCount() == 1) {
            countButton.setText("  1 article");
        } else {
            countButton.setText("  " + String.valueOf(ctrl.getArticleCount()) + " articles");
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    countButton.setText("  Count articles");
                });
            }
        }, 2000l);
    }

    /***
     * test list for testing, replacing with actual list when ready with filtering "austria"
     * @param event
     */
    @FXML
    void GetTopLinesAustria(ActionEvent event) {
        getList("austria");
    }

    /***
     * test list for testing, replacing with actual list when ready with filtering "bitcoin"
     * @param event
     */
    @FXML
    void GetTopLinesBitcoin(ActionEvent event) {
        getList("bitcoin");
    }

    /***
     * switch to a different color pattern inside the GUI e.g. dark/white mode (not finished)
     * @param event
     */
    @FXML
    void changeColorPattern(ActionEvent event) {

        isLightMode = !isLightMode;
        parent.getStylesheets().add(String.valueOf(getClass().getResource("/css/lightmode.css")));
        if (isLightMode) {
            setLightMode();
        } else {
            setDarkMode();
        }
    }

    private void setLightMode() {
        parent.getStylesheets().remove(String.valueOf(getClass().getResource("/css/darkmode.css")));
        parent.getStylesheets().add(String.valueOf(getClass().getResource("/css/lightmode.css")));
        //parent.getStylesheets().set(String.valueOf(getClass().getResource("/css/lightmode.css")));
        System.out.println("LIGHT");

    }
    private void setDarkMode() {
        parent.getStylesheets().remove(String.valueOf(getClass().getResource("/css/lightmode.css")));
        parent.getStylesheets().add(String.valueOf(getClass().getResource("/css/darkmode.css")));
        System.out.println("DARK");
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
        }
    }

}