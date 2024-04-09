package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import model.Role;
import model.Teacher;
import repository.TeacherRepository;
import repository.TeacherRepositoryImpl;
import util.ConnectionFactory;

@WebServlet("/add-teacher")
public class AddTeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private TeacherRepository teacherRepository;

    public void init() throws ServletException {
        try {
            // Get the connection from ConnectionFactory
            connection = ConnectionFactory.getConnection();
            // Initialize the repository using the connection
            teacherRepository = new TeacherRepositoryImpl(connection);
        } catch (SQLException e) {
            throw new ServletException("Error initializing teacher repository", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the add-teacher.jsp page for displaying the form
        request.getRequestDispatcher("/WEB-INF/jsp/add-teacher.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle the form submission to add a new teacher
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String subject = request.getParameter("subject");
        String email = request.getParameter("email");
        Role role = Role.valueOf(request.getParameter("role")); // Assuming Role enum exists

        // Create a new Teacher object and save it using the repository
        Teacher teacher = new Teacher(0, firstName, lastName, subject, email, role);
        teacherRepository.save(teacher);

        // Redirect to the teachers list page after adding the teacher
        response.sendRedirect(request.getContextPath() + "/teachers");
    }

    public void destroy() {
        super.destroy();
        // Connection cleanup should ideally be handled in the finally block after usage
        ConnectionFactory.closeConnection(connection);
    }
}
