package at.ac.fhcampuswien.globalSettings;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadJSON {

    private boolean colorPatternDark;

    @SuppressWarnings("unchecked")
    public void readSettings() {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        var fileName = "src/main/resources/globalSettings/settings.json";
        try (
                FileReader reader = new FileReader(fileName)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;

            //Iterate over employee array
            employeeList.forEach(emp -> parseEmployeeObject((JSONObject) emp));

        } catch (ParseException |
                IOException e) {
            e.printStackTrace();
        }

    }

    public boolean getColorPattern() {
       return colorPatternDark;
    }

    private void parseEmployeeObject(JSONObject employee) {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("settings");

        //Get employee first name
        boolean colorPattern = (boolean) employeeObject.get("colorPatternDark");
        colorPatternDark = colorPattern;

    }
}
