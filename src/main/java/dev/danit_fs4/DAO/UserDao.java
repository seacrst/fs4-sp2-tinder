package dev.danit_fs4.DAO;

import dev.danit_fs4.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements DAO<User> {
    private final Connection connection;
    public UserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) throws SQLException {
        if(user.id() == null) insert(user);
        else update(user);
    }

    @Override
    public Optional<User> load(int id) throws SQLException {
        String sql = "SELECT id, name, photo FROM users WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
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
        String sql = "SELECT id, name, photo FROM users";
        PreparedStatement stmt = connection.prepareStatement(sql);
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
        String sql = "SELECT id, name, photo FROM users WHERE id != ? ORDER BY id";
        PreparedStatement stmt = connection.prepareStatement(sql);
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

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    private void insert(User userProfile) throws SQLException {
        String sql = "INSERT INTO users (name, photo) values (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, userProfile.getName());
        stmt.setString(2, userProfile.getPhoto());
        stmt.execute();
    }
    private void update(User userProfile) throws SQLException {
        String sql = "UPDATE users SET name =?, photo =? WHERE id =?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, userProfile.getName());
        stmt.setString(2, userProfile.getPhoto());
        stmt.setInt(3, userProfile.id());
        stmt.execute();
    }

}
