package repository;

import java.util.List;

import model.Teacher;

public interface TeacherRepository {

	Teacher save(Teacher teacher);

    Teacher findById(int id);

    List<Teacher> findAll();

    Teacher update(Teacher teacher);

    void deleteById(int id);
}
