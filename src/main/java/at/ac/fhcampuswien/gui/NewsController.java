package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import at.ac.fhcampuswien.apiStuff.NewsApi;
import at.ac.fhcampuswien.globalSettings.ReadJSON;
import at.ac.fhcampuswien.globalSettings.WriteJSON;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
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
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;


public class NewsController {
    private AppController ctrl = new AppController();
    private WriteJSON writeJSON = new WriteJSON();
    private ReadJSON readJSON = new ReadJSON();

    private boolean isLightMode;
    private double x, y = 0;
    private String cssURL;
    Hashtable<String, String> hashAPIKey = new Hashtable<>();

    @FXML
    private AnchorPane parent;

    @FXML
    private AnchorPane anchormidDashboard;

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
    private ImageView imgViewAustria;

    @FXML
    private ImageView imgViewBitcoin;

    @FXML
    private ImageView imgViewExit;

    @FXML
    private ImageView imgViewMinimize;

    @FXML
    private ImageView imgViewWindow;

    @FXML
    private ImageView imgViewDashboard;

    @FXML
    private ImageView imgViewSettings;

    @FXML
    private Tab tabDashboard;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabSettings;

    @FXML
    private JFXToggleButton colorPatternToggleButton;

    @FXML
    private JFXComboBox<String> cmbAPIKey;

    public NewsController() throws IOException {
    }

    /***
     * when the fxml starts this should happen
     */
    @FXML
    void initialize() {
        //create out dictionairy for API key translation
        createDictionary();
        //the standard text gets replaced by this placeholder
        tvNews.setPlaceholder(new Label(""));
        //adding our standard API key to combobox
        cmbAPIKey.getItems().add("APIKEY 1");
        //adding our other API keys to combobox
        cmbAPIKey.getItems().add("APIKEY 2");
        cmbAPIKey.getItems().add("APIKEY 3");
        cmbAPIKey.getItems().add("APIKEY 4");
        //select our standard API key
        cmbAPIKey.getSelectionModel().select(0);
        // read global settings
        readJSON();
        // start with this css
        parent.getStylesheets().add(String.valueOf(getClass().getResource(cssURL)));
        //set API Key
        NewsApi.setAPIKEY(hashAPIKey.get(cmbAPIKey.getValue()));
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
            setLightMode(false);
        } else {
            setDarkMode(false);
        }
    }

    /***
     * switch the tabs within the tabPane to Dashboard
     * @param event
     */
    @FXML
    void dashboardSwitch(ActionEvent event) {
        tabPane.getSelectionModel().select(0);
        lblDashboard.setText("Dashboard");
    }

    /***
     * switch the tabs within the tabPane to Settings
     * @param event
     */
    @FXML
    void dashboardSwitchSettings(ActionEvent event) {
        tabPane.getSelectionModel().select(1);
        lblDashboard.setText("Settings");
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
        draggingWindow(event);
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
        draggingWindow(event);
    }

    /***
     * get x,y coordinates of anchormidDashboard when pressed
     * @param event
     */
    @FXML
    void windowPressed2(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /***
     * set new positions of window when getting dragged
     * @param event
     */
    @FXML
    void windowDragged3(MouseEvent event) {
        draggingWindow(event);
    }

    /***
     * get x,y coordinates of anchormidSettings when pressed
     * @param event
     */
    @FXML
    void windowPressed3(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /***
     * depending on toggleButton position write into settings.json (true=isSelected()=dark)
     * @param event
     */
    @FXML
    void colorPatternToggleButtonChanged(ActionEvent event) {
        writeJSON.SaveSettings(colorPatternToggleButton.isSelected());
    }

    /**
     * every time comboboxcontent get's changed this method hits and change the API key
     * @param event
     */
    @FXML
    void cmbAPIKeyChanged(ActionEvent event) {
        // set the API key
        NewsApi.setAPIKEY(hashAPIKey.get(cmbAPIKey.getValue()));
    }

    /***
     * apply the light mode to the main GUI
     */
    private void setLightMode(boolean init) {
        if (!init) {
            parent.getStylesheets().remove(String.valueOf(getClass().getResource("/css/darkmode.css")));
            parent.getStylesheets().add(String.valueOf(getClass().getResource("/css/lightmode.css")));
        }
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/at_light.png")));
        imgViewAustria.setImage(image);
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bitcoin_light.png")));
        imgViewBitcoin.setImage(image2);

        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/exitwindow_light.png")));
        imgViewExit.setImage(image4);
        Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/maximize_light.png")));
        imgViewWindow.setImage(image5);
        Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/minimize_light.png")));
        imgViewMinimize.setImage(image6);

        Image image7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/umbrella_light.png")));
        imgViewDashboard.setImage(image7);
        Image image8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/settings_light.png")));
        imgViewSettings.setImage(image8);

    }

    /***
     * apply the dark mode to the main GUI
     */
    private void setDarkMode(boolean init) {
        if (!init) {
            parent.getStylesheets().remove(String.valueOf(getClass().getResource("/css/lightmode.css")));
            parent.getStylesheets().add(String.valueOf(getClass().getResource("/css/darkmode.css")));
        }
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/at2.png")));
        imgViewAustria.setImage(image);
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/bitcoin.png")));
        imgViewBitcoin.setImage(image2);

        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/exitwindow.png")));
        imgViewExit.setImage(image4);
        Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/maximize.png")));
        imgViewWindow.setImage(image5);
        Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/minimize.png")));
        imgViewMinimize.setImage(image6);

        Image image7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/umbrella_64px.png")));
        imgViewDashboard.setImage(image7);
        Image image8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/settings.png")));
        imgViewSettings.setImage(image8);
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
     * count all articles in list
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

    /***
     * dragging window
     * @param event
     */
    private void draggingWindow(MouseEvent event) {
        Stage stage = (Stage) anchormidDashboard.getScene().getWindow();
        // prevents dragging a maximized window
        if (stage.isMaximized()) {
        } else {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        }
    }

    /***
     * read from settings.json and select the css (dark or light)
     */
    private void readJSON() {
        readJSON.readSettings();
        // set the toggleButton to right position (0 or 1)
        colorPatternToggleButton.setSelected(readJSON.getColorPattern());
        if (colorPatternToggleButton.isSelected()) {
            cssURL = "/css/darkmode.css";
            //set the boolean isLightMode to right boolean value according to css for runtime switch between colorPattern mode
            isLightMode = false;
            //set the images to right mode
            setDarkMode(true);
        } else {
            cssURL = "/css/lightmode.css";
            isLightMode = true;
            setLightMode(true);
        }

    }

    private void createDictionary()
    {
        //translation from key to value
        hashAPIKey.put("APIKEY 1" ,"b44b32e1c7de47c7827d10cdf122365f");
        hashAPIKey.put("APIKEY 2","0ff6dda6d57f4f1d8401ba6dec619975");
        hashAPIKey.put("APIKEY 3","c0eefde7fa834ff3b423058ddf3d94df");
        hashAPIKey.put("APIKEY 4","b89ea3f55c0045b987c709bc16d7f849");

    }
}