package dev.danit_fs4.DAO;

import dev.danit_fs4.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDatabaseDao implements DAO<User> {
    private final Connection connection;
    private final List<User> likes = new ArrayList<>();
    public UserDatabaseDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) throws SQLException {
        if(user.id() == null) insert(user);
        else update(user);
    }

    @Override
    public Optional<User> load(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT id, name, photo FROM users WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return Optional.of(new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("photo")
            ));
        }
        return Optional.empty();
    }

    public List<User> getAll() throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT id, name, photo FROM users ");
        ResultSet rs = stmt.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()){
            User user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("photo")
            );
            list.add(user);
        }
        return list;
    }
    public List<User> getAll(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT id, name, photo FROM users WHERE id != ? ");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()){
            User user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("photo")
            );
            list.add(user);
        }
        return list;
    }
    public Integer size() {
        try {
            return getAll().size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User getNext(int index) {
        try {
            return getAll().get(index);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    private void insert(User userProfile) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (name, photo) values (?, ?)");
        stmt.setString(1, userProfile.getName());
        stmt.setString(2, userProfile.getPhoto());
        stmt.execute();
    }
    private void update(User userProfile) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET name =?, photo =? WHERE id =?");
        stmt.setString(1, userProfile.getName());
        stmt.setString(2, userProfile.getPhoto());
        stmt.setInt(3, userProfile.id());
        stmt.execute();
    }
    public void addLikedUser(int i){
        try{
            Optional<User> likedUser = load(i);
            if (likedUser.isPresent()){
                if(!likes.contains(likedUser.get())) likes.add(likedUser.get());
                else likes.set(likes.indexOf(likedUser.get()), likedUser.get());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeLikedUser(int i){
        try{
            Optional<User> likedUser = load(i);
            likedUser.ifPresent(likes::remove);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getLiked(){
        return likes;
    }

//    List<Integer> list = new ArrayList<>();
//    public Integer get(int i) throws SQLException {
//        PreparedStatement stmt = connection.prepareStatement("SELECT id FROM users ");
//        ResultSet rs = stmt.executeQuery();
//        List<Integer> list = new ArrayList<>();
//        while (rs.next()){
//            list.add(rs.getInt("id"));
//        }
//        return list.get(i);
//    }
//    public Optional<User> getNextUser(int index) {
//        try {
//            return load(get(index));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
