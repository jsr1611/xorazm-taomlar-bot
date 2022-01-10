package repository;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    Product save(Product product);

    void saveAll(List<Product> productList) throws SQLException;

    List<Product> findAll();

    Product findById(Long id);

    Boolean existsById(Long id) throws SQLException;
}
