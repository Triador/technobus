package services;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.ServiceException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;

public class SheetsServiceImpl implements SheetsService {
    private static volatile SheetsServiceImpl sheetsService1Impl;
    public static final String TO_OFFICE =
            "https://spreadsheets.google.com/feeds/list/1yajaDHYL4pWad_cYUAab1C2ZypiYTDg2Vqxe3zmWDiI/1/public/values";
    public static final String FROM_OFFICE =
            "https://spreadsheets.google.com/feeds/list/1yajaDHYL4pWad_cYUAab1C2ZypiYTDg2Vqxe3zmWDiI/2/public/values";
    public static final String COORDINATES =
            "https://spreadsheets.google.com/feeds/list/1yajaDHYL4pWad_cYUAab1C2ZypiYTDg2Vqxe3zmWDiI/3/public/values";

    public static SheetsServiceImpl getInstance() {
        if (sheetsService1Impl == null) {
            synchronized (SheetsServiceImpl.class) {
                if (sheetsService1Impl == null) {
                    sheetsService1Impl = new SheetsServiceImpl();
                }
            }
        }
        return sheetsService1Impl;
    }

    @Override
    public JSONObject getSchedule() throws IOException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            Object object = parser.parse(new FileReader("web/schedule.json"));
            jsonObject = (JSONObject) object;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public JSONArray getSchedule(String url) {
        JSONArray jsonArray = new JSONArray();
        try {
            SpreadsheetService service = new SpreadsheetService("Schedule");
            ListFeed listFeed = service.getFeed(new URL(url), ListFeed.class);

            for (ListEntry listElement : listFeed.getEntries()) {
                CustomElementCollection cec = listElement.getCustomElements();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("time", cec.getValue("время"));
                jsonObject.put("mask", Integer.parseInt(cec.getValue("днинедели"), 2));
                jsonArray.add(jsonObject);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public void updateJson() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("web/schedule.json"))) {
            JSONObject jsonObject = new JSONObject();
            JSONArray fromJsonArray = getSchedule(FROM_OFFICE);
            JSONArray toJsonArray = getSchedule(TO_OFFICE);
            JSONArray coordinates = getCoordinates();
            jsonObject.put("Technopolis", coordinates);
            jsonObject.put("fromOffice", fromJsonArray);
            jsonObject.put("toOffice", toJsonArray);
            writer.write(jsonObject.toJSONString());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getCoordinates() {
        JSONArray jsonArray = new JSONArray();
        try {
            SpreadsheetService service = new SpreadsheetService("Schedule");
            ListFeed listFeed = service.getFeed(new URL(COORDINATES), ListFeed.class);

            for (ListEntry listElement : listFeed.getEntries()) {
                CustomElementCollection cec = listElement.getCustomElements();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(cec.getValue("Name"), cec.getValue("Technopolis"));
                jsonArray.add(jsonObject);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}