package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorServlet extends HttpServlet {

    private static final String JSP_ERROR = "/WEB-INF/view/error.jsp";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        error(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        error(req, resp);
    }

    private void error(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String type = req.getParameter("type");
        if (type != null && type.equals("js")) {
            req.setAttribute("title", "JavaScript");
            req.setAttribute("message", "У вас в браузере отключён JavaScript. Для корректной работы портала необходимо его включить.");
        }
        String redir = req.getParameter("redir");
        if (redir != null) {
            req.setAttribute("redir", redir);
        }
        req.getRequestDispatcher(JSP_ERROR).forward(req, resp);
    }
}
