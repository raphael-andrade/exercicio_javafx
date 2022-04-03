package javafx.javafx.model.dao;

import javafx.javafx.db.DB;
import javafx.javafx.model.dao.impl.DepartmentDaoJDBC;
import javafx.javafx.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDao createSellerDao(){

        return new SellerDaoJDBC(DB.getConnection());
    }
    public static DepartmentDao createDepartmentDao(){

        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
