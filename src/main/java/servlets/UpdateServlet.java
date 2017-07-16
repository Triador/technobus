package servlets;

import services.SheetsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by antonandreev on 16/07/2017.
 */
public class UpdateServlet extends HttpServlet {

    private SheetsService sheetsService;

    public UpdateServlet(SheetsService sheetsService) {
        this.sheetsService = sheetsService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        sheetsService.updateJson();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
