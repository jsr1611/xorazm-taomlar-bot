package repository;

import model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static config.DbConfig.connection;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public void saveAll(List<Product> productList) throws SQLException {
        for (Product product : productList) {
            Boolean exists = existsById(product.getId());
            if (!exists) {
                String INSERT_CATEGORY = "INSERT INTO products(category_id, name, price, image_url) values(?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY);
                statement.setLong(1, product.getCategoryId());
                statement.setString(2, product.getName());
                statement.setDouble(3, product.getPrice());
                statement.setString(4, product.getImageUrl());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Boolean existsById(Long id) throws SQLException {
        String SELECT_BY_ID = "SELECT * FROM products WHERE id = '" + id + "'";
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }


}
