package servlets;

import org.json.simple.JSONObject;
import services.SheetsService;
import services.SheetsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by antonandreev on 04/07/2017.
 */
public class getScheduleServlet extends HttpServlet {

    private SheetsService sheetsService;

    public getScheduleServlet(SheetsService sheetsService) {
        this.sheetsService = sheetsService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/x-json;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fromOffice", sheetsService.getSchedule(SheetsServiceImpl.FROM_OFFICE));
        jsonObject.put("toOffice", sheetsService.getSchedule(SheetsServiceImpl.TO_OFFICE));
        resp.getWriter().write(jsonObject.toJSONString());
        resp.getWriter().flush();
        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
