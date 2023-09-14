package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.User;

public interface UserService extends BaseService<User> {
    User findByEmailAndPassword(String email, String password);

    User findById(int id);
}
