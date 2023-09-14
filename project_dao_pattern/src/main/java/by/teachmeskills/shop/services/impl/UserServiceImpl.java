package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.repositories.UserRepository;
import by.teachmeskills.shop.repositories.impl.UserRepositoryImpl;
import by.teachmeskills.shop.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User create(User entity) {
        return userRepository.create(entity);
    }

    @Override
    public List<User> read() {
        return userRepository.read();
    }

    @Override
    public User update(User entity) {
        return userRepository.update(entity);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }
}
