package by.teachmeskills.shop.repositories.impl;

import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Log4j2
public class UserRepositoryImpl implements UserRepository {

    private static final String GET_USER = "SELECT * FROM users WHERE email=? and password=?";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private final static String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    private static final String ADD_USER = "INSERT INTO users (name,surname,birthday,email,password,balance,address,phoneNumber) values (?,?,?,?,?,0,?,?)";
    private final static String GET_ALL_USERS = "SELECT * FROM users";
    private final static String UPDATE_ADDRESS_AND_PHONE_NUMBER = "UPDATE users SET address = ?, phoneNumber = ? WHERE id = ?";

    @Override
    public User create(User entity) {
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, String.valueOf(entity.getBirthday()));
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getPassword());
            preparedStatement.setString(6, entity.getAddress());
            preparedStatement.setString(7, entity.getPhoneNumber());
            preparedStatement.execute();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return entity;
    }

    @Override
    public List<User> read() {
        List<User> users = new ArrayList<>();

        try (Connection connection = pool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String password = rs.getString("password");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                int balance = rs.getInt("balance");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                users.add(User.builder()
                        .id(id)
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .birthday(birthday)
                        .balance(balance)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .build());
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return users;
    }

    @Override
    public User update(User entity) {
        int userId = entity.getId();
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS_AND_PHONE_NUMBER);
            preparedStatement.setString(1, entity.getAddress());
            preparedStatement.setString(2, entity.getPhoneNumber());
            preparedStatement.setInt(3, userId);
            preparedStatement.execute();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return entity;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.execute();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = User.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .surname(rs.getString(3))
                        .birthday(rs.getObject(4, LocalDate.class))
                        .email(rs.getString(5))
                        .balance(rs.getInt(7))
                        .address(rs.getString(8))
                        .phoneNumber(rs.getString(9))
                        .build();
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return user;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = null;
        try (Connection connection = pool.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = User.builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .surname(rs.getString(3))
                        .birthday(rs.getObject(4, LocalDate.class))
                        .email(rs.getString(5))
                        .balance(rs.getInt(7))
                        .address(rs.getString(8))
                        .phoneNumber(rs.getString(9))
                        .build();
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return user;
    }

}

