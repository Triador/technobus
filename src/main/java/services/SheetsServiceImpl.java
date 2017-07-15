package services;

import java.io.IOException;

import java.net.URL;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.ServiceException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SheetsServiceImpl implements SheetsService {
    private static volatile SheetsServiceImpl shetsService1Impl;
    public static final String TO_OFFICE =
            "https://spreadsheets.google.com/feeds/list/1yajaDHYL4pWad_cYUAab1C2ZypiYTDg2Vqxe3zmWDiI/1/public/values";
    public static final String FROM_OFFICE =
            "https://spreadsheets.google.com/feeds/list/1yajaDHYL4pWad_cYUAab1C2ZypiYTDg2Vqxe3zmWDiI/2/public/values";

    public static SheetsServiceImpl getInstance() {
        if (shetsService1Impl == null) {
            synchronized (SheetsServiceImpl.class) {
                if (shetsService1Impl == null) {
                    shetsService1Impl = new SheetsServiceImpl();
                }
            }
        }
        return shetsService1Impl;
    }

    @Override
    public JSONArray getSchedule(String url) throws IOException {
        JSONArray jsonArray = new JSONArray();
        try {
            SpreadsheetService service = new SpreadsheetService("Schedule");
            ListFeed listFeed = service.getFeed(new URL(url), ListFeed.class);

            for (ListEntry listElement : listFeed.getEntries()) {
                CustomElementCollection cec = listElement.getCustomElements();
                JSONObject object = new JSONObject();
                object.put("time", cec.getValue("время"));
                object.put("mask", Integer.parseInt(cec.getValue("днинедели"), 2));
                jsonArray.add(object);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }
}