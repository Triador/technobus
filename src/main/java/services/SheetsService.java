package services;

import org.json.simple.JSONArray;
import java.io.IOException;

/**
 * Created by antonandreev on 13/07/2017.
 */
public interface SheetsService {
    JSONArray getSchedule(String listName) throws IOException;
}
