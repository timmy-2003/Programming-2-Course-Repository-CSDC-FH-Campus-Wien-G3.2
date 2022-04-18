package at.ac.fhcampuswien.globalSettings;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class WriteJSON {


    @SuppressWarnings("unchecked")
    public void SaveSettings(boolean dark)
    {

        //Create json object for colorPattern settings
        JSONObject colorPattern = new JSONObject();
        colorPattern.put("colorPatternDark", dark);

        // create settingsObject
        JSONObject settingsObject = new JSONObject();
        settingsObject.put("settings", colorPattern);


        //Add settings into list
        JSONArray globalSettingsList = new JSONArray();
        globalSettingsList.add(settingsObject);

        var fileName = "src/main/resources/globalSettings/settings.json";
        //Write JSON file
        try (FileWriter file = new FileWriter(fileName)) {

            file.write(globalSettingsList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
