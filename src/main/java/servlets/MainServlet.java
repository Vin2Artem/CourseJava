package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    private static final String jsp_student = "/WEB-INF/view/main.jsp";
    private static final String url_login = "/login";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        main(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        main(req, resp);
    }

    protected void main(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(url_login);
            return;
        }
        /* check last lesson exist */
        Cookie[] cookies = req.getCookies();
        String cookieName = "last_lesson";
        Cookie last_lesson = null;
        if(cookies != null) {
            for(Cookie c : cookies) {
                if(cookieName.equals(c.getName())) {
                    last_lesson = c;
                    break;
                }
            }
        }
        if (last_lesson != null) {
            req.setAttribute("last_lesson", last_lesson.getValue());
        }
        // Add 2nd group (admin)
        req.getRequestDispatcher(jsp_student).forward(req, resp);
    }
}
