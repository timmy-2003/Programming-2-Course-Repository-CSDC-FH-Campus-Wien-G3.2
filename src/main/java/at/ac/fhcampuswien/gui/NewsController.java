package at.ac.fhcampuswien.gui;

import at.ac.fhcampuswien.AppController;
import at.ac.fhcampuswien.Article;
import at.ac.fhcampuswien.WriteTXT;
import at.ac.fhcampuswien.apiStuff.NewsApi;
import at.ac.fhcampuswien.downloader.ParallelDownloader;
import at.ac.fhcampuswien.downloader.SequentialDownloader;
import at.ac.fhcampuswien.enums.Country;
import at.ac.fhcampuswien.enums.Endpoint;
import at.ac.fhcampuswien.exceptions.APIKeyException;
import at.ac.fhcampuswien.exceptions.NewsAPIException;
import at.ac.fhcampuswien.exceptions.urlException;
import at.ac.fhcampuswien.globalSettings.ReadJSON;
import at.ac.fhcampuswien.globalSettings.WriteJSON;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;


public class NewsController {
    private AppController ctrl = AppController.getInstance();
    private WriteJSON writeJSON = WriteJSON.getInstance();
    private ReadJSON readJSON = ReadJSON.getInstance();
    private WriteTXT writeTXT = new WriteTXT();

    private boolean isLightMode;
    private double x, y = 0;
    private String cssURL;
    private Hashtable<String, String> hashAPIKey = new Hashtable<>();
    private List<String> apiKeysList;
    private int indexOfSelectedAPIKey;
    private int apiKeysChange = 0; //counter how often API key has changed during runtime
    private static long timeElapsed;
    private static long timeElapsedSeq;


    @FXML
    private AnchorPane parent;

    @FXML
    private Label parallelTimer;

    @FXML
    private Label sequentialTimer;

    @FXML
    private AnchorPane anchormidDashboard;

    @FXML
    private Button exitButton;
    @FXML
    private Button btnCount;

    @FXML
    private Button btnGetUnder15;

    @FXML
    private Button btnGetSortAsc;


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
    private TableColumn<Article, String> description;


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
    private Button btnGetCustomNews;

    @FXML
    private JFXToggleButton colorPatternToggleButton;

    @FXML
    private JFXComboBox<String> cmbAPIKey;
    @FXML
    private JFXComboBox<String> cmbEndPoint;
    @FXML
    private JFXComboBox<String> cmbCountry;
    @FXML
    private TextField txtfieldQuery;


    @FXML
    private Label lblLongestAuthor;

    @FXML
    private Label lblMostArticles;

    @FXML
    private Label lblNYT;

    @FXML
    private Button btnDownload;


    public NewsController() throws IOException {
    }

    /***
     * when the fxml starts this should happen
     */
    @FXML
    void initialize() {
        initalize();
    }

    /***
     * test list for testing, replacing with actual list when ready with filtering "austria"
     * @param event
     */
    @FXML
    void GetTopLinesAustria(ActionEvent event) throws IOException {
        Platform.runLater(() -> {
            try {
                getList(Endpoint.TOP_HEADLINES, "austria", "austria", Country.AT); // Endpoint and Country will not get used so not important what standing there
                countArticles();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /***
     * test list for testing, replacing with actual list when ready with filtering "bitcoin"
     * @param event
     */
    @FXML
    void GetTopLinesBitcoin(ActionEvent event) throws IOException {
        Platform.runLater(() -> {
            try {
                getList(Endpoint.TOP_HEADLINES, "bitcoin", "bitcoin", Country.AT); // Endpoint and Country will not get used so not important what standing there
                countArticles();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /***
     * get custom news from params
     * @param event
     */
    @FXML
    void GetCustomNews(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                // cast strings to enums
                String endpointString = cmbEndPoint.getValue();
                Endpoint endpoint = Endpoint.valueOf(endpointString);
                String countryString = cmbCountry.getValue();
                if (countryString.equals("") || countryString == null) {
                    getList(endpoint, txtfieldQuery.getText(), "custom");
                } else {
                    Country country = Country.valueOf(countryString);
                    getList(endpoint, txtfieldQuery.getText(), "custom", country); // Endpoint and Country will not get used so not important what standing there
                }
                countArticles();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    /***
     * sort list with streams
     * @param event
     */
    @FXML
    void GetSortAsc(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                getList(Endpoint.TOP_HEADLINES, "", "sortasc", Country.AT); // Endpoint and Country will not get used so not important what standing there
                countArticles();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /***
     * get all articles with desc under 15 chars
     * @param event
     */
    @FXML
    void GetUnder15(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                getList(Endpoint.TOP_HEADLINES, "", "under15", Country.AT); // Endpoint and Country will not get used so not important what standing there
                countArticles();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
        writeJSON.SaveSettings(indexOfSelectedAPIKey, apiKeysList, colorPatternToggleButton.isSelected());
    }

    /**
     * every time comboboxcontent get's changed this method hits and change the API key
     *
     * @param event
     */
    @FXML
    void cmbAPIKeyChanged(ActionEvent event) {
        //change index if selected manually (needed for WriteJson() standardvalue of API)
        indexOfSelectedAPIKey = apiKeysList.indexOf(hashAPIKey.get(cmbAPIKey.getValue()));
        // set the API key
        NewsApi.setAPIKEY(hashAPIKey.get(cmbAPIKey.getValue()));
        //save changings
        writeJSON.SaveSettings(indexOfSelectedAPIKey, apiKeysList, colorPatternToggleButton.isSelected());
    }

    /***
     * multithreading downloadurls
     * @param event
     */
    @FXML
    void GetDownloadURls(ActionEvent event) {
        new Thread(this::downloadURLs).start();

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
    private void getList(Enum endpoint, String query, String tag, Enum... args) throws IOException {
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        String message = "";
        switch (tag.toLowerCase()) {
            case "austria" -> tvNews.setItems(getObservableListFromList(ctrl.getTopHeadlinesAustria()));
            case "bitcoin" -> tvNews.setItems(getObservableListFromList(ctrl.getAllNewsBitcoin()));
            case "custom" -> tvNews.setItems(getObservableListFromList(ctrl.getCustomNews(endpoint, query, args)));
            case "under15" -> tvNews.setItems(getObservableListFromList(ctrl.headLinesUnderFifteenSymbols()));
            case "sortasc" -> tvNews.setItems(getObservableListFromList(ctrl.sortAsc()));
            case "" -> tvNews.setItems(getObservableListFromList(ctrl.getArticles()));
        }

        // get message from NEWS API and give according alert // filter for message

        if (NewsApi.errorMessage != "") {
            if (NewsApi.errorMessage.contains("parameterInvalid")) {
                try {
                    throw new urlException("Not supported. Note: Endpoint \"Everything\" not compatible with country search");
                } catch (urlException e) {
                    System.out.println("invalid parameters");
                }
            } else if (NewsApi.errorMessage.contains("apiKeyInvalid")) {
                try {
                    throw new APIKeyException();
                } catch (APIKeyException e) {
                    System.out.println("Your API Key is invalid, please check API Key!");
                }
            } else if (NewsApi.errorMessage.contains("parametersMissing")) {
                try {
                    throw new urlException("Parameters missing - try searching with a query!");
                } catch (urlException e) {
                    System.out.println("Required params are missing");
                }
            } else if (NewsApi.errorMessage.contains("apiKeyDisabled")) {
                try {
                    throw new APIKeyException("Your api key has been disabled!");
                } catch (APIKeyException e) {
                    System.out.println("Api key has been disabled");
                }
            } else if (NewsApi.errorMessage.contains("unexpectedError")) {
                try {
                    throw new NewsAPIException("An unexpected error occured");
                } catch (NewsAPIException e) {
                    System.out.println("Unexpected error");
                }
            }
            NewsApi.errorMessage = "";
        }

        //when returned no list change API Key automatically && we want to change maximum all possibilities once (4API keys -> change max 3 times)
       /* else if (tvNews.getItems().size() == 0 && apiKeysChange < apiKeysList.size() - 1) {
            apiKeysChange++;
            //get index of selected API key in list
            indexOfSelectedAPIKey = apiKeysList.indexOf(hashAPIKey.get(cmbAPIKey.getValue()));
            //go to next API Key -> if exceeded go to first one again
            indexOfSelectedAPIKey = (indexOfSelectedAPIKey + 1) % 4;
            //set new API key in GUI
            cmbAPIKey.getSelectionModel().select(indexOfSelectedAPIKey);
            //recursive request again with a different API key
            getList(endpoint,query, tag, args);
        }
        //when all API keys has been tested and we still got no information tell the user with alert
        else if (tvNews.getItems().size() == 0 && apiKeysChange >= apiKeysList.size() - 1) {
            alert("All API Keys have no requests anymore for today!");
        }*/

        //get source with most articles
        lblMostArticles.setText(ctrl.sourceWithMostArticles());

        //for checking through all sources in a sorted manner
        /*
        for (Article a: ctrl.getArticles().stream()
                .sorted(Comparator.comparing(a -> a.getSource().getName()))
                .collect(Collectors.toList())) {
            System.out.println(a.getSourceName());
        }*/

        //get longest author name
        lblLongestAuthor.setText(ctrl.longestAuthorName());

        //get all news with the source "New York Times"
        if (ctrl.sourceNewYorkTimes().size() > 0) {
            for (Article a : ctrl.sourceNewYorkTimes()) {
                lblNYT.setText(a.getTitle());
            }
        } else {
            lblNYT.setText("NO ARTICLES FROM NYT");
        }

        //get all news under 40
        for (Article a : ctrl.headLinesUnderFifteenSymbols()) {
            //System.out.println("UNDER 15: " + a.getTitle());

        }
        //sort asc
        for (Article a : ctrl.sortAsc()) {
            //System.out.println(a.getLength() + "sort " + a.getDescription());
        }
        ctrl.saveOriginalArticles();
    }

    /***
     * count articles which are displayed
     */
    private void countArticles() throws IOException {
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
        //set index from .json into runtime indexOfSelectedAPIKey
        indexOfSelectedAPIKey = readJSON.getIndexOfSelectedAPIKey();
    }

    /***
     * translation from GUI field to actual API key (GUI TEXT <->  APIKEY)
     */
    private void createDictionary() {
        //get API keys from read input of json
        apiKeysList = readJSON.getApiKeys();

        //translation from key to value
        hashAPIKey.put("APIKEY 1", apiKeysList.get(0));
        hashAPIKey.put("APIKEY 2", apiKeysList.get(1));
        hashAPIKey.put("APIKEY 3", apiKeysList.get(2));
        hashAPIKey.put("APIKEY 4", apiKeysList.get(3));
    }

    /***
     * initialize methods
     */
    private void initalize() {
        // read global settings
        readJSON();
        //create out dictionary for API key translation
        createDictionary();
        //the standard text gets replaced by this placeholder
        tvNews.setPlaceholder(new Label(""));
        for (Country country : Country.values()) {
            cmbCountry.getItems().add(country.name());
        }
        cmbCountry.setValue("");
        cmbCountry.getItems().add("");
        cmbEndPoint.getItems().add("EVERYTHING");
        cmbEndPoint.getItems().add("TOP_HEADLINES");

        //adding our standard API key to combobox
        cmbAPIKey.getItems().add("APIKEY 1");
        //adding our other API keys to combobox
        cmbAPIKey.getItems().add("APIKEY 2");
        cmbAPIKey.getItems().add("APIKEY 3");
        cmbAPIKey.getItems().add("APIKEY 4");
        //select our standard API key
        cmbAPIKey.getSelectionModel().select(indexOfSelectedAPIKey);
        cmbEndPoint.getSelectionModel().select(0);
        // start with this css
        parent.getStylesheets().add(String.valueOf(getClass().getResource(cssURL)));
        //set API Key
        NewsApi.setAPIKEY(hashAPIKey.get(cmbAPIKey.getValue()));
    }

    /***
     * load alert in thread
     * @param alertMessage
     */
    public void alert(String alertMessage) {
        try {
            new Thread(() -> {
                loadAlert(alertMessage);
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * load alert window
     * @param alertMessage
     */
    private void loadAlert(String alertMessage) {
        Platform.runLater(() -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/newsappalertscreen.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            NewsControllerAlertScreen alertcontroller = loader.getController();
            alertcontroller.setAlertMessage(alertMessage);

            //create the scene
            Stage stage = new Stage();
            Scene scene = new Scene(root, 600, 354);
            scene.setFill(Color.TRANSPARENT);
            stage.centerOnScreen();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);

            // setting the icon image
            Image icon = new Image(String.valueOf(getClass().getResource("/images/logo.png")));
            stage.getIcons().add(icon);
            stage.show();

            //center the splash screen into the mid
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        });
    }

    /***
     * downloadurls with stop time in UI
     * @return
     */
    private void downloadURLs() {
        try {
            long start = System.currentTimeMillis();
            ctrl.downloadURLs(new ParallelDownloader());
            long finish = System.currentTimeMillis();
            timeElapsed = finish - start;

            System.out.println(timeElapsed);
            //start time in ms -> for stopping time
            long startSequential = System.currentTimeMillis();
            ctrl.downloadURLs(new SequentialDownloader());
            long finishSequential = System.currentTimeMillis();
            timeElapsedSeq = finishSequential - startSequential;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    sequentialTimer.setText("Sequential: " + timeElapsedSeq + " ms");
                }
            });


        } catch (NewsAPIException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }
        finally {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    parallelTimer.setText("Parallel: " + timeElapsed + " ms");
                    sequentialTimer.setText("Sequential: " + timeElapsedSeq + " ms");
                }
            });

            System.out.println("Parallel: " + timeElapsed + " ms");
            System.out.println("Sequential: " + timeElapsedSeq + " ms");
        }

    }
}