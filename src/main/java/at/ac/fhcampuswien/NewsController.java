package at.ac.fhcampuswien;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Timer;
import java.util.TimerTask;


//needed: 4 BUTTONS
// ELEMENT for "get top headlines austria || ELEMENT for "get all news btc" || LABEL or smth for display Count Articles || QUIT Program - maybe also button and Alert -> "quitting text"
public class NewsController {
    private AppController ctrl = new AppController();
    Timer timer = new Timer();

    @FXML
    private Button exitButton;
    @FXML private Button countButton;

    @FXML
    private Button btnGetToplinesAustria;

    @FXML
    private TableView<Article> tvNews;
    @FXML
    private TableColumn<Article, String> title;
    @FXML
    private TableColumn<Article, String> author;

    ObservableList<Article> list = FXCollections.observableArrayList(
            new Article("Daniel", "BTC"),
            new Article("Anna", "ETH"),
            new Article("xx", "Austria"),
            new Article("yy", "Germany"));

    ObservableList<Article> list2 = FXCollections.observableArrayList(
            new Article("CNN", "BTC"),
            new Article("BBC", "BTC"),
            new Article("Krone", "DOGE"),
            new Article("Zeitimbild", "Austria"));


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

    @FXML
    void showArticleCount(){
        if (ctrl.getArticleCount() == 1){
            countButton.setText("  1 article");
        } else {
            countButton.setText("  " + String.valueOf(ctrl.getArticleCount()) + " articles");}
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
        author.setCellValueFactory(new PropertyValueFactory<Article,String>("Author"));
        title.setCellValueFactory(new PropertyValueFactory<Article,String>("Title"));
        tvNews.setItems(list);
    }

    /***
     * test list for testing, replacing with actual list when ready with filtering "btc"
     * @param event
     */
    @FXML
    void GetTopLinesBitcoin(ActionEvent event) {
        author.setCellValueFactory(new PropertyValueFactory<Article,String>("Author"));
        title.setCellValueFactory(new PropertyValueFactory<Article,String>("Title"));
        tvNews.setItems(list2);
    }

}