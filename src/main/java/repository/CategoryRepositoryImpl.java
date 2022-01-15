package repository;

import model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.DbConfig.connection;

public class CategoryRepositoryImpl implements CategoryRepository{



    @Override
    public Boolean existsById(Long id) throws SQLException {
        String SELECT_BY_ID = "SELECT * FROM categories WHERE id = '" + id + "'";
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    @Override
    public Category findByName(String name) throws SQLException {
        String SELECT_BY_NAME = "SELECT * FROM categories WHERE name = '" + name +"'";
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return new Category(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("prefix")
            );
        }
        return null;
    }

    @Override
    public void saveAll(List<Category> categories) throws SQLException {
        for (Category category : categories) {
            Boolean exists = existsById(category.getId());
            if (!exists) {
                String INSERT_CATEGORY = "INSERT INTO categories(prefix, name) values(?, ?)";
                PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY);
                statement.setString(1, category.getPrefix());
                statement.setString(2, category.getName());

                statement.executeUpdate();
            }
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM categories";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                categories.add(new Category(
                         resultSet.getLong("id"),
                        resultSet.getString("prefix"),
                        resultSet.getString("name")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }
}
