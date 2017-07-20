package services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Created by antonandreev on 13/07/2017.
 */
public interface SheetsService {
    JSONObject getSchedule() throws IOException;
    JSONArray getSchedule(String url);
    void updateJson();
    JSONArray getCoordinates();
}
