package dev.danit_fs4.services;

import dev.danit_fs4.dao.LikeDao;
import dev.danit_fs4.entities.User;

import java.sql.SQLException;
import java.util.List;

public class LikeService {
    private final LikeDao likeDao = new LikeDao();

    public List<User> getLikedUsers(Integer id) {
        return likeDao.getLikedUsers(id);
    }

    public void like(int currentUserId, int likedUserId) {
        try {
            likeDao.addLiked(currentUserId, likedUserId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dislike(int currentUserId, int dislikedUserId) {
        try {
            likeDao.removeLikedUser(currentUserId, dislikedUserId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
