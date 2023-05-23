package dev.danit_fs4.services;

import dev.danit_fs4.DAO.LikeDao;
import dev.danit_fs4.Entity.User;

import java.sql.SQLException;
import java.util.List;

public class LikeService {
    private final LikeDao likeDao = new LikeDao();

    public List<User> getLikedUsers(Integer id) {
        return likeDao.getLikedUsers(id);
    }

    public void like(int currentUserId, int likedUserId) throws SQLException {
        likeDao.addLiked(currentUserId, likedUserId);
    }

    public void dislike(int currentUserId, int dislikedUserId) throws SQLException {
        likeDao.removeLikedUser(currentUserId, dislikedUserId);
    }

    public boolean isLiked(Integer hostId, Integer guestId) {
        boolean isLikedFlag;
        try {
            isLikedFlag = likeDao.getId(hostId, guestId).isPresent();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
        return isLikedFlag;

    }
}
