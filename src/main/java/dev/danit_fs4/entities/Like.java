package dev.danit_fs4.entities;

import dev.danit_fs4.dao.Identifiable;

public class Like implements Identifiable {

    private final Integer id;
    private final Integer userId;
    private final Integer likedUserId;

    public Like(int id, int userId, int likedUserId) {
        this.id = id;
        this.userId = userId;
        this.likedUserId = likedUserId;
    }
    public Like(int userId, int likedUserId) {
        this.id = null;
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
