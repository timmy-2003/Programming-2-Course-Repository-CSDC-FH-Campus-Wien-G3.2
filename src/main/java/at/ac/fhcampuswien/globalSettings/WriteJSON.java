package at.ac.fhcampuswien.globalSettings;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteJSON {


    // tells the compiler that the code to be safe and won't cause unexpected exceptions
    @SuppressWarnings("unchecked")
    public void SaveSettings(int indexOfSelectedAPIKey,List<String> apiKeysList, boolean dark) {
        //Create json object for colorPattern settings
        JSONObject obj = new JSONObject();
        obj.put("color_pattern_dark", dark);

        // Create a new JSONArray object for our keys
        JSONArray apiKeys = new JSONArray();

        // for each element in apiKeyList add the apiKeys to jsonArray
        apiKeys.addAll(apiKeysList);

        //adding the list to our JSON Object
        obj.put("api_keys", apiKeys);

        //Create json object for colorPattern settings
        obj.put("selected_api_key", indexOfSelectedAPIKey);


        //our file destination
        var fileName = "src/main/resources/globalSettings/settings.json";

        //write JSON file
        try (FileWriter file = new FileWriter(fileName)) {

            //write json file from jsonobject
            file.write(obj.toJSONString());
            //write out any data that lingers in a program buffer to the actual file
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
