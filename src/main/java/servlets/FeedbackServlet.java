package servlets;

import DAO.DAOFactory;
import DAO.FeedbackDAO;
import DAO.UserDAO;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FeedbackServlet extends HttpServlet {

    private static final String JSP_FEEDBACK = "/WEB-INF/view/feedback.jsp";
    private static final String JSP_ERROR = "/WEB-INF/view/error.jsp";
    private static final String URL_LOGIN = "/login";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(URL_LOGIN);
            return;
        }
        if (!user.getEditor()) {
            Captcha captcha = new Captcha();
            session.setAttribute("question", captcha.getQuestion());
            session.setAttribute("answer", captcha.getAnswer());
        } else {
            DAOFactory sqliteFactory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
            UserDAO userDAO = sqliteFactory.getUserDAO();
            FeedbackDAO feedbackDAO = sqliteFactory.getFeedbackDAO();
            ArrayList<Feedback> feeds = feedbackDAO.getAllFeeds();
            ArrayList<Map<String, Object>> feedbacks = new ArrayList<>();
            for (Feedback f : feeds) {
                Map<String, Object> feedback = new HashMap<>();
                feedback.put("id", f.getId());
                feedback.put("user", userDAO.findUser(f.getUser()));
                feedback.put("topic", f.getTopic());
                feedback.put("desc", f.getDesc());
                feedbacks.add(feedback);

            }
            req.setAttribute("feedbacks", feedbacks);
        }
        req.getRequestDispatcher(JSP_FEEDBACK).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(URL_LOGIN);
            return;
        }
        req.setCharacterEncoding("UTF-8");
        if (!user.getEditor()) {
            // Captcha check
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
                    ((User) session.getAttribute("user")).getId(),
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
        }
        doGet(req, resp);
    }
}
