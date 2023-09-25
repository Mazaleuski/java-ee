package by.teachmeskills.shop.repositories.impl;

import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.repositories.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
@Log4j2
public class ProductRepositoryImpl implements ProductRepository {

    private static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE categoryId=?";
    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id=?";
    private final static String DELETE_PRODUCT_BY_ID = "DELETE FROM products WHERE id = ?";
    private final static String GET_ALL_PRODUCTS = "SELECT * FROM products";
    private final static String UPDATE_DESCRIPTION_AND_PRICE_BY_ID = "UPDATE products SET description = ?, price = ? WHERE id = ?";
    private static final String ADD_PRODUCT = "INSERT INTO products (name,description,price,categoryId,imagePath) values (?,?,?,?,?)";
    private static final String GET_PRODUCT_BY_NAME_OR_DESCRIPTION = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ? ORDER BY name ASC;";

    @Override
    public Product create(Product entity) {
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setInt(3, entity.getPrice());
            preparedStatement.setInt(4, entity.getCategoryId());
            preparedStatement.setString(5, entity.getImagePath());
            preparedStatement.execute();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return entity;
    }

    @Override
    public List<Product> read() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                int categoryId = rs.getInt("category_id");
                String imagePath = rs.getString("imagePath");

                products.add(Product.builder()
                        .id(id)
                        .name(name)
                        .description(description)
                        .price(price)
                        .categoryId(categoryId)
                        .imagePath(imagePath)
                        .build());
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return products;
    }

    @Override
    public Product update(Product entity) {
        int productId = entity.getId();
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DESCRIPTION_AND_PRICE_BY_ID);
            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setString(2, String.valueOf(entity.getPrice()));
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.execute();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                product = Product.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .description(rs.getString(3))
                        .price(rs.getInt(4))
                        .categoryId(rs.getInt(5))
                        .imagePath(rs.getString(6))
                        .build();
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return product;
    }

    @Override
    public List<Product> findByCategoryId(int id) {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY_ID);
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                productList.add(Product.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .description(rs.getString(3))
                        .price(rs.getInt(4))
                        .categoryId(rs.getInt(5))
                        .imagePath(rs.getString(6))
                        .build());
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return productList;
    }

    @Override
    public List<Product> findByNameOrDescription(String search) {
        List<Product> products = new LinkedList<>();
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_NAME_OR_DESCRIPTION);
            search = "%" + search.trim() + "%";
            preparedStatement.setString(1, search);
            preparedStatement.setString(2, search);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(Product.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .description(rs.getString(3))
                        .price(rs.getInt(4))
                        .categoryId(rs.getInt(5))
                        .imagePath(rs.getString(6))
                        .build());
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return products;
    }
}
