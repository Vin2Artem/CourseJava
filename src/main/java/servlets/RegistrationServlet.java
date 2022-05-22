package servlets;

import DAO.DAOFactory;
import DAO.UserDAO;
import classes.Captcha;
import log.MyLog;
import models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private final static String JSP_REGISTRATION = "/WEB-INF/view/registration.jsp";
    private final static String JSP_REGISTRATION_SUCCESS = "/WEB-INF/view/registration_success.jsp";
    private static final String JSP_ERROR = "/WEB-INF/view/error.jsp";
    private static final String URL_MAIN = "/main";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            resp.sendRedirect(URL_MAIN);
            return;
        }
        Captcha captcha = new Captcha();
        session.setAttribute("question", captcha.getQuestion());
        session.setAttribute("answer", captcha.getAnswer());
        req.setAttribute("today", java.time.LocalDate.now());
        getServletContext().getRequestDispatcher(JSP_REGISTRATION).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            if (session.getAttribute("user") != null) {
                resp.sendRedirect(URL_MAIN);
                return;
            }
            // Captcha check
            req.setCharacterEncoding("UTF-8");
            String right, given;
            try {
                right = session.getAttribute("answer").toString();
                given = req.getParameter("answer");
            } catch (Exception e) {
                req.setAttribute("title", "Ошибка входа");
                req.setAttribute("message", "У вас заблокированы файлы cookie. Для корректной работы портала необходимо их разблокировать.");
                req.getRequestDispatcher(JSP_ERROR).forward(req, resp);
                return;
            }
            if (!right.equals(given)) {
                req.setAttribute("title", "Ошибка входа");
                req.setAttribute("message", "Каптча введена неверно!");
                req.getRequestDispatcher(JSP_ERROR).forward(req, resp);
                return;
            }
            // If OK
            session.removeAttribute("answer");
            session.removeAttribute("question");
            req.setCharacterEncoding("UTF-8");
            DAOFactory sqliteFactory = DAOFactory.getDAOFactory(DAOFactory.SQLITE);
            UserDAO userDAO = sqliteFactory.getUserDAO();
            User user = new User(User.DEFAULTID,
                    req.getParameter("surname"),
                    req.getParameter("name"),
                    req.getParameter("patronymic"),
                    req.getParameter("sex"),
                    req.getParameter("birth"),
                    req.getParameter("email"),
                    BCrypt.hashpw(req.getParameter("pwd"), BCrypt.gensalt(12)),
                    req.getParameter("tel"),
                    req.getParameter("city"),
                    false);
            int id = userDAO.insertUser(user);
            if (id == userDAO.INSERT_ERROR) {
                req.setAttribute("title", "Ошибка регистрации");
                req.setAttribute("message", "Непредвиденная ошибка. Повторите позднее.");
                req.getRequestDispatcher(JSP_ERROR).forward(req, resp);
                return;
            }
            if (id == userDAO.UNIQUE_EMAIL) {
                req.setAttribute("title", "Ошибка регистрации");
                req.setAttribute("message", "Аккаунт с таким email уже зарегистрирован.");
                req.getRequestDispatcher(JSP_ERROR).forward(req, resp);
                return;
            }
            user.setId(id);
            MyLog.Msg("User added: " + user.toString());
            req.getRequestDispatcher(JSP_REGISTRATION_SUCCESS).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher(JSP_ERROR).forward(req, resp);
        }
    }
}
