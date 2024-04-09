package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Role;
import model.Teacher;

public class TeacherRepositoryImpl implements TeacherRepository {
    private Connection connection;

    public TeacherRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Teacher save(Teacher teacher) {
        String sql = "INSERT INTO teachers (first_name, last_name, subject, email, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, teacher.getSubject());
            statement.setString(4, teacher.getEmail());
            statement.setString(5, teacher.getRole().name());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating teacher failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    teacher.setId(id);
                } else {
                    throw new SQLException("Creating teacher failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public Teacher findById(int id) {
        String sql = "SELECT * FROM teachers WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractTeacherFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teachers";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Teacher teacher = extractTeacherFromResultSet(resultSet);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public Teacher update(Teacher teacher) {
        String sql = "UPDATE teachers SET first_name=?, last_name=?, subject=?, email=?, role=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, teacher.getSubject());
            statement.setString(4, teacher.getEmail());
            statement.setString(5, teacher.getRole().name());
            statement.setInt(6, teacher.getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating teacher failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM teachers WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Teacher extractTeacherFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String subject = resultSet.getString("subject");
        String email = resultSet.getString("email");
        Role role = Role.valueOf(resultSet.getString("role"));
        return new Teacher(id, firstName, lastName, subject, email, role);
    }
}
