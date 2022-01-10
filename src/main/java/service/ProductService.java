package service;

import model.Category;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    Product save(Product product);

    void saveAll(List<Product> productList) throws SQLException;

    List<Product> findAll();

    Product findById(Long id);
}
