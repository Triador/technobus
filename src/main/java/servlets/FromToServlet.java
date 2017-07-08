package servlets;

import services.H2DBService;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by antonandreev on 04/07/2017.
 */
public class FromToServlet extends HttpServlet {

    private H2DBService dbService;

    public FromToServlet(H2DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userAgent = req.getHeader("user-agent").toUpperCase();
        resp.setContentType("text/html;charset=utf-8");

        if (userAgent.contains("android".toUpperCase())
                || userAgent.contains("iphone".toUpperCase())
                || userAgent.contains("phone".toUpperCase())) {
            resp.getWriter().write(PageGenerator.instance().getPage("mobile.html"));
        }
        else resp.getWriter().write(PageGenerator.instance().getPage("desktop.html"));

        // два разные content-type pа раз не получится отправить, только один
        //resp.setContentType("text/x-json;charset=UTF-8");
        //resp.setHeader("Cache-Control", "no-cache");

        String toJsonArray = dbService.getJsonArray("fromOffice").toJSONString();
        String fromJsonArray = dbService.getJsonArray("toOffice").toJSONString();

        //resp.getWriter().write(toJsonArray);
        //resp.getWriter().write(fromJsonArray);
        resp.getWriter().flush();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
