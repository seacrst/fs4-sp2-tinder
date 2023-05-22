package dev.danit_fs4.DAO;

import dev.danit_fs4.Entity.Like;
import dev.danit_fs4.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LikeDao implements DAO<Like>{
    private final Connection connection;
    private final UserDatabaseDao userDao;
    public LikeDao(UserDatabaseDao userDao, Connection connection) {
        this.connection = connection;
        this.userDao = userDao;
    }
    @Override
    public void save(Like like) throws SQLException {
        String sql = "INSERT INTO liked (user_id, liked_user_id) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, like.userid());
        stmt.setInt(2, like.getLiked());
        stmt.execute();

    }

    @Override
    public Optional<Like> load(int id) throws SQLException {
        String sql = "SELECT id, user_id, liked_user_id FROM liked WHERE user_id = ? AND liked_user_id =?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return Optional.of(new Like(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("liked_user_id")
            ));
        }
        return Optional.empty();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM liked WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
    public void removeLikedUser(int UI, int LUI) throws SQLException {
        String sql = "DELETE FROM liked WHERE user_id = ? AND liked_user_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, UI);
        stmt.setInt(2, LUI);
        stmt.executeUpdate();
    }
    public Optional<Integer> getId(int UI, int LUI) throws SQLException {
        String sql = "SELECT id FROM liked WHERE user_id = ? AND liked_user_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, UI);
        stmt.setInt(2, LUI);
        stmt.execute();
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return Optional.of(rs.getInt("id"));
        }
        return Optional.empty();
    }
    public void addLiked(int UI, int LUI) throws SQLException {
        String sql = """
                INSERT INTO liked (user_id, liked_user_id)
                SELECT ? AS user_id, ? AS liked_user_id
                WHERE NOT EXISTS (
                    SELECT 1
                    FROM liked
                    WHERE user_id = ? AND liked_user_id = ?
                );
                """;
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, UI);
        stmt.setInt(2, LUI);
        stmt.setInt(3, UI);
        stmt.setInt(4, LUI);
        stmt.execute();
    }

    public List<Like> getAll() {
        List<Like> likes = new ArrayList<>();
        try {
            ResultSet rsl = connection
                    .prepareStatement("SELECT id, user_id, liked_user_id FROM liked")
                    .executeQuery();

            while (rsl.next()) {
                likes.add(new Like(rsl.getInt("id"), rsl.getInt("user_id"), rsl.getInt("liked_user_id")));
            }

            return likes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getLikedUsers(Integer uid) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = connection
                    .prepareStatement("""
                            select liked_user_id
                            from liked
                            where ? = user_id
                            """);
            ps.setInt(1, uid);
            ResultSet rsl = ps.executeQuery();
            while (rsl.next()) {
                int id = rsl.getInt("liked_user_id");
                Optional<User> user = userDao.load(id);
                user.ifPresent(users::add);
            }
            return users;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
