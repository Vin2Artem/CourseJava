package servlets;

import DAO.DAOFactory;
import DAO.UserDAO;
import classes.Captcha;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private static final String JSP_LOGIN = "/WEB-INF/view/login.jsp";
    private static final String JSP_ERROR = "/WEB-INF/view/error.jsp";
    private static final String URL_MAIN = "/main";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            resp.sendRedirect(URL_MAIN);
            return;
        }
        Captcha captcha = new Captcha();
        session.setAttribute("question", captcha.getQuestion());
        session.setAttribute("answer", captcha.getAnswer());
        getServletContext().getRequestDispatcher(JSP_LOGIN).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            if (session.getAttribute("user") != null) {
                resp.sendRedirect(URL_MAIN);
                return;
            }
            // Captcha check
            req.setCharacterEncoding("UTF-8");
            String right = session.getAttribute("answer").toString();
            String given = req.getParameter("answer").toString();
            if (!right.equals(given)) {
                req.setAttribute("title", "Ошибка входа");
                req.setAttribute("message", "Каптча введена неверно!");
                req.getRequestDispatcher(JSP_ERROR).forward(req, resp);
                return;
            }
            // If OK
            session.removeAttribute("answer");
            session.removeAttribute("question");
            DAOFactory sqliteFactory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
            UserDAO userDAO = sqliteFactory.getUserDAO();
            User target = userDAO.findUser(req.getParameter("email"), req.getParameter("pwd"));
            if (target == null) {
                req.setAttribute("title", "Ошибка входа");
                req.setAttribute("message", "Неверный логин и/или пароль!");
                req.getRequestDispatcher(JSP_ERROR).forward(req, resp);
                return;
            }
            // Session init
            session.setMaxInactiveInterval(60 * 60 * 24);
            session.setAttribute("user", target);
            resp.sendRedirect(URL_MAIN);
        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher(JSP_ERROR).forward(req, resp);
        }
    }
}
