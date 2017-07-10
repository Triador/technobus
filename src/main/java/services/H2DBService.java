package services;

import org.json.simple.JSONArray;

/**
 * Created by antonandreev on 10/07/2017.
 */
public interface H2DBService {
    JSONArray getJsonArray(String bdName);
    void update(String bdName, int from, int to);
}
