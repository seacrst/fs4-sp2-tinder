package dev.danit_fs4.services;

import dev.danit_fs4.DAO.UserDao;
import dev.danit_fs4.Entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao = new UserDao();

    public User getNextUser(int index, int id) {
        try {
            return userDao.getAll(id).get(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Integer size() {
        try {
            return userDao.getAll().size()-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getAll(int id){
        try {
            return userDao.getAll(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> getUser(int id){
        try {
            return userDao.load(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user){
        try {
            userDao.save(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
