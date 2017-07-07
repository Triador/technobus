package servlets;

import services.H2DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartServlet extends HttpServlet {

    private H2DBService dbService;

    public StartServlet(H2DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/x-json;charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");

        //тут выбираем user-agent, но пока перенаправлю в to
        resp.sendRedirect("/to");
    }
}
