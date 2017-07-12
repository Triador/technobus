package servlets;

import org.json.simple.JSONObject;
import services.H2DBServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by antonandreev on 04/07/2017.
 */
public class getScheduleServlet extends HttpServlet {

    private H2DBServiceImpl dbService;

    public getScheduleServlet(H2DBServiceImpl dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/x-json;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fromOffice", dbService.getJsonArray("fromOffice"));
        jsonObject.put("toOffice", dbService.getJsonArray("toOffice"));

        resp.getWriter().write(jsonObject.toJSONString());
        resp.getWriter().flush();
        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
