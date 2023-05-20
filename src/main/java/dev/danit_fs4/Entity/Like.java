package dev.danit_fs4.Entity;

import dev.danit_fs4.DAO.Identifiable;

public class Like implements Identifiable {

    private final Integer id;
    private final Integer userId;
    private final Integer likedUserId;

    public Like(int id, int userId, int likedUserId) {
        this.id = id;
        this.userId = userId;
        this.likedUserId = likedUserId;
    }

    public Integer userid() {
        return userId;
    }

    @Override
    public Integer id() {
        return userId;
    }

    public Integer getLiked() {
        return likedUserId;
    }
}
