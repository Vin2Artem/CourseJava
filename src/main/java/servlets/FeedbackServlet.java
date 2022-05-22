package servlets;

import DAO.DAOFactory;
import DAO.FeedbackDAO;
import classes.Captcha;
import log.MyLog;
import models.Feedback;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FeedbackServlet extends HttpServlet {

    private static final String JSP_FEEDBACK = "/WEB-INF/view/feedback.jsp";
    private static final String JSP_ERROR = "/WEB-INF/view/error.jsp";
    private static final String URL_LOGIN = "/login";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(URL_LOGIN);
            return;
        }
        Captcha captcha = new Captcha();
        session.setAttribute("question", captcha.getQuestion());
        session.setAttribute("answer", captcha.getAnswer());
        req.getRequestDispatcher(JSP_FEEDBACK).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(URL_LOGIN);
            return;
        }
        // Captcha check
        req.setCharacterEncoding("UTF-8");
        String right = session.getAttribute("answer").toString();
        String given = req.getParameter("answer");
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
        FeedbackDAO feedbackDAO = sqliteFactory.getFeedbackDAO();
        Feedback feedback = new Feedback(Feedback.DEFAULTID,
                ((User)session.getAttribute("user")).getId(),
                req.getParameter("topic"),
                req.getParameter("desk"));
        int id = feedbackDAO.insertFeed(feedback);
        if (id == feedbackDAO.INSERT_ERROR) {
            req.setAttribute("title", "Ошибка отправки");
            req.setAttribute("message", "Непредвиденная ошибка. Повторите позднее.");
            req.getRequestDispatcher(JSP_ERROR).forward(req, resp);
            return;
        }
        if (id != feedbackDAO.ALREADY_IS) {
            MyLog.Msg("Feedback added: " + feedback.toString());
            req.setAttribute("success", true);
        }
        feedback.setId(id);
        doGet(req, resp);
    }
}
