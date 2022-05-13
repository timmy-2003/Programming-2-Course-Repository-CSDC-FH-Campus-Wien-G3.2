package at.ac.fhcampuswien.globalSettings;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadJSON {

    private boolean colorPatternDark;
    private List<String> apiKeysList = new ArrayList<>();

    // tells the compiler that the code to be safe and won't cause unexpected exceptions
    @SuppressWarnings("unchecked")
    public void readSettings() {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        //our file destination
        var fileName = "src/main/resources/globalSettings/settings.json";
        try (
                FileReader reader = new FileReader(fileName)) {

            //Read JSON file
            Object obj = jsonParser.parse(reader);

            //create jsonobject
            JSONObject settingsObject = (JSONObject) obj;

            //get settings colorPattern
            boolean colorPattern = (boolean) settingsObject.get("color_pattern_dark");
            colorPatternDark = colorPattern;

            //get APIKeys from json
            JSONArray apiKeysArray = (JSONArray) settingsObject.get("api_keys");

            //add all elements from array to list
            apiKeysList.addAll(apiKeysArray);

        } catch (ParseException |
                IOException e) {
            e.printStackTrace();
        }

    }

    /***
     * get the boolean of the color GUI
     * @return
     */
    public boolean getColorPattern() {
       return colorPatternDark;
    }

    /***
     * get the list of api keys from json
     * @return
     */
    public List<String> getApiKeys() {
        return apiKeysList;
    }

}
