package javafx.javafx.model.dao;

import javafx.javafx.entities.Department;
import javafx.javafx.entities.Seller;

import java.util.List;

public interface SellerDao {
    void insert(Seller obj);
    void update(Seller obj);
    void deleteById(Integer id);
    Seller findById (Integer id);
    List<Seller> findAll();
    List<Seller> findByDepartment(Department department);

}
