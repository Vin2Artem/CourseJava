package servlets;

import DAO.CourseDAO;
import DAO.LessonDAO;
import DAO.SQLiteDAOFactory;
import models.Course;
import models.Lesson;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class SearchServlet extends HttpServlet {

    private static final String JSP_SEARCH = "/WEB-INF/view/search.jsp";
    private static final String URL_LOGIN = "/login";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        search(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        search(req, resp);
    }

    protected void search(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(URL_LOGIN);
            return;
        }
        req.setCharacterEncoding("UTF-8");
        String search = req.getParameter("search");
        if (search == null || search.equals("")) {
            resp.sendRedirect(URL_LOGIN);
            return;
        }
        req.setAttribute("search", search);
        SQLiteDAOFactory sqLiteDAOFactory = new SQLiteDAOFactory();
        CourseDAO courseDAO = sqLiteDAOFactory.getCourseDAO();
        ArrayList<Course> courses = new ArrayList<>();
        String courseDesc = req.getParameter("courseDesc");
        String lessonDesc = req.getParameter("lessonDesc");
        if (courseDesc == null && lessonDesc == null) {
            req.setAttribute("courseDesc", true);
            courseDesc = "on";
        }
        if (courseDesc != null) {
            req.setAttribute("courseDesc", true);
            courses.addAll(courseDAO.findCourses(search));
        } else {
            req.setAttribute("courseDesc", false);
        }
        LessonDAO lessonDAO = sqLiteDAOFactory.getLessonDAO();
        HashSet<Lesson> lessons = new HashSet<>();
        if (lessonDesc != null) {
            req.setAttribute("lessonDesc", true);
            for (Course course : courseDAO.getAllCourses()) {
                ArrayList<Lesson> temp = lessonDAO.getLessonsOfCourse(user, course.getId());
                for (Lesson lesson : temp) {
                    if (lesson.getDesc().toLowerCase().contains(search.toLowerCase())) {
                        lessons.add(lesson);
                    }
                }
            }
        } else {
            req.setAttribute("lessonDesc", false);
        }
        req.setAttribute("courses", courses);
        req.setAttribute("lessons", lessons);
        if (courses.size() == 0 && lessons.size() == 0) {
            req.setAttribute("notFound", true);
        }

        req.getRequestDispatcher(JSP_SEARCH).forward(req, resp);
    }
}
