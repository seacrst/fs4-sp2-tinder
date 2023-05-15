package dev.danit_fs4.DAO;

import dev.danit_fs4.Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements DAO<User> {
    private final List<User> users = new ArrayList<>();

    public UserDao() {
        users.add(new User(1, "John", "https://images.unsplash.com/photo-1503443207922-dff7d543fd0e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bWVufGVufDB8fDB8fA%3D%3D&w=1000&q=80"));
        users.add(new User(2, "Mary", "https://images.pexels.com/photos/2913125/pexels-photo-2913125.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"));
        users.add(new User(3, "Tom", "https://i.pinimg.com/736x/90/f7/a4/90f7a49893bc987858e13e10ffc72a23.jpg"));
    }

    @Override
    public void save(User user) {
        if(!users.contains(user)) users.add(user);
        else users.set(users.indexOf(user), user);
    }

    @Override
    public Optional<User> load(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }
    public User getByIndex(int index) {
        return users.get(index);
    }
    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void delete(int id) {
        Optional<User> user = load(id);
        user.ifPresent(users::remove);
    }
}
