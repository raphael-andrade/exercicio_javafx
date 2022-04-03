package javafx.javafx.model.dao;

import javafx.javafx.entities.Department;

import java.util.List;

public interface DepartmentDao {
    void insert(Department obj);
    void update(Department obj);
    void deleteById(Integer id);
    Department findById (Integer id);
    List<Department> findAll();
    }
