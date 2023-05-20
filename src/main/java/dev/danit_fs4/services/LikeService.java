package dev.danit_fs4.services;

import dev.danit_fs4.DAO.UserDatabaseDao;
import dev.danit_fs4.Entity.Like;
import dev.danit_fs4.Entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeService {
    private final Connection connection;
    private final UserDatabaseDao usersDAO;

    public LikeService(Connection con) {
        connection = con;
        usersDAO = new UserDatabaseDao(con);
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

    public List<User> getLikedUsers(List<Like> likes) {
        List<User> users = new ArrayList<>();

        likes.forEach(like -> {
            try {
                User user = usersDAO.load(like.getLiked()).orElseThrow();

                users.add(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        return users;
    }
}
