package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Teacher;

public class TeacherRepositoryImpl implements TeacherRepository {
    private Map<Integer, Teacher> teachers = new HashMap<>();
    private int nextId = 1;

    @Override
    public Teacher save(Teacher teacher) {
        teacher.setId(nextId++);
        teachers.put(teacher.getId(), teacher);
        return teacher;
    }

    @Override
    public Teacher findById(int id) {
        return teachers.get(id);
    }

    @Override
    public List<Teacher> findAll() {
        return new ArrayList<>(teachers.values());
    }

    @Override
    public Teacher update(Teacher teacher) {
        if (!teachers.containsKey(teacher.getId())) {
            throw new IllegalArgumentException("Teacher not found or invalid ID");
        }
        teachers.put(teacher.getId(), teacher);
        return teacher;
    }

    @Override
    public void deleteById(int id) {
        teachers.remove(id);
    }

	
}
