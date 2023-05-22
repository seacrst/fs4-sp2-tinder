package dev.danit_fs4.services;

import dev.danit_fs4.DAO.UserDao;

import java.sql.Connection;

public class LikeService {
    private final Connection connection;
    private final UserDao usersDAO;

    public LikeService(Connection con) {
        connection = con;
        usersDAO = new UserDao(con);
    }
}
