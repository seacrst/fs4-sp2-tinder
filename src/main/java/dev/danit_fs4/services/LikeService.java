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
}
