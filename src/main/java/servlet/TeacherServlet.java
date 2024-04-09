package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import repository.TeacherRepository;
import repository.TeacherRepositoryImpl;
import model.Role;
import model.Teacher;
import util.ConnectionFactory;

@WebServlet("/teachers")
public class TeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TeacherRepository teacherRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Initialize the repository using the ConnectionFactory
            teacherRepository = new TeacherRepositoryImpl(ConnectionFactory.getConnection());
        } catch (SQLException e) {
            throw new ServletException("Error initializing teacher repository", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Teacher teacher = teacherRepository.findById(id);
            request.setAttribute("teacher", teacher);
            request.getRequestDispatcher("/WEB-INF/jsp/edit-teacher.jsp").forward(request, response);
        } else {
            List<Teacher> teachers = teacherRepository.findAll();
            request.setAttribute("teachers", teachers);
            request.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String subject = request.getParameter("subject");
            String email = request.getParameter("email");
            Role role = Role.valueOf(request.getParameter("role")); // Assuming Role enum exists
            Teacher teacher = new Teacher(0, firstName, lastName, subject, email, role); // ID is auto-generated
            teacherRepository.save(teacher);
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String subject = request.getParameter("subject");
            String email = request.getParameter("email");
            Role role = Role.valueOf(request.getParameter("role")); // Assuming Role enum exists
            Teacher teacher = new Teacher(id, firstName, lastName, subject, email, role);
            teacherRepository.update(teacher);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            teacherRepository.deleteById(id);
        }
        response.sendRedirect(request.getContextPath() + "/teachers");
    }

//    @Override
//    public void destroy() {
//        super.destroy();
//        // Close the connection when the servlet is destroyed
//        if (teacherRepository instanceof TeacherRepositoryImpl) {
//            ((TeacherRepositoryImpl) teacherRepository).closeConnection();
//        }
//    }
}
