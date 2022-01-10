package service;

import model.Product;
import repository.ProductRepository;
import repository.ProductRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    public static ProductRepository productRepo = new ProductRepositoryImpl();
    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public void saveAll(List<Product> productList) throws SQLException {
        productRepo.saveAll(productList);
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }
}
