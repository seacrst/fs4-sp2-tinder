package dev.danit_fs4.services;

import dev.danit_fs4.DAO.LikeDao;

public class LikeService {
    private final LikeDao likeDao = new LikeDao();


    public LikeDao getData() {
        return likeDao;
    }
}
