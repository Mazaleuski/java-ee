package by.teachmeskills.shop.repositories.impl;

import by.teachmeskills.shop.entities.Category;
import by.teachmeskills.shop.repositories.CategoryRepository;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CategoryRepositoryImpl implements CategoryRepository {

    private static final String GET_ALL_CATEGORIES = "SELECT * FROM categories";
    private static final String GET_CATEGORY_NAME_BY_ID = "SELECT name FROM categories WHERE id=?";
    private static final String ADD_CATEGORY = "INSERT INTO categories (name,imagePath,rating) values (?,?,?)";
    private final static String DELETE_CATEGORY_BY_ID = "DELETE FROM categories WHERE id = ?";
    private final static String UPDATE_IMAGE_PATH_AND_RATING_BY_ID = "UPDATE categories SET imagePath = ?, rating = ? WHERE id = ?";

    @Override
    public Category create(Category entity) {
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CATEGORY);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getImagePath());
            preparedStatement.setInt(3, entity.getRating());
            preparedStatement.execute();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return entity;
    }

    @Override
    public List<Category> read() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = pool.getConnection();) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_CATEGORIES);
            while (rs.next()) {
                categories.add(Category.builder().id(rs.getInt(1)).name(rs.getString(2))
                        .imagePath(rs.getString(3)).rating(rs.getInt(4)).build());
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return categories;
    }

    @Override
    public Category update(Category entity) {
        int productId = entity.getId();
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_IMAGE_PATH_AND_RATING_BY_ID);
            preparedStatement.setString(1, entity.getImagePath());
            preparedStatement.setInt(2, entity.getRating());
            preparedStatement.setInt(3, productId);
            preparedStatement.execute();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return entity;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_BY_ID);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.execute();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public String findNameById(int id) {
        String categoryName = null;
        try (Connection connection = pool.getConnection();) {
            PreparedStatement categoriesStatement = connection.prepareStatement(GET_CATEGORY_NAME_BY_ID);
            categoriesStatement.setString(1, String.valueOf(id));
            ResultSet rs = categoriesStatement.executeQuery();
            if (rs.next()) {
                categoryName = rs.getString(1);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return categoryName;
    }
}
