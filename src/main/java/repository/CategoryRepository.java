package repository;

import model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryRepository {
    Boolean existsById(Long id) throws SQLException;

    Category findByName(String name) throws SQLException;

    void saveAll(List<Category> categories) throws SQLException;

    List<Category> findAll();
}
