package at.ac.fhcampuswien.globalSettings;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadJSON {

    private boolean colorPatternDark;

    // tells the compiler that the code to be safe and won't cause unexpected exceptions
    @SuppressWarnings("unchecked")
    public void readSettings() {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        var fileName = "src/main/resources/globalSettings/settings.json";
        try (
                FileReader reader = new FileReader(fileName)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray globalSettingsList = (JSONArray) obj;

            //Iterate over settings array
            globalSettingsList.forEach(set -> parseGlobalSettingsObject((JSONObject) set));

        } catch (ParseException |
                IOException e) {
            e.printStackTrace();
        }

    }

    public boolean getColorPattern() {
       return colorPatternDark;
    }

    private void parseGlobalSettingsObject(JSONObject settings) {
        //Get settings object within list
        JSONObject settingsObject = (JSONObject) settings.get("settings");

        //Get settings colorPattern
        boolean colorPattern = (boolean) settingsObject.get("colorPatternDark");
        colorPatternDark = colorPattern;
    }
}
