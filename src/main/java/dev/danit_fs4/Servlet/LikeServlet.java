package dev.danit_fs4.Servlet;

import dev.danit_fs4.DAO.UserDatabaseDao;
import dev.danit_fs4.Entity.Like;
import dev.danit_fs4.Entity.User;
import dev.danit_fs4.services.LikeService;
import dev.danit_fs4.view.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LikeServlet extends HttpServlet {
    private final LikeService service;

    public LikeServlet(Connection con) {
        service = new LikeService(con);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        View view = new View("/templates");

        List<Like> likes = service.getAll();
        List<User> likedUsers = service.getLikedUsers(likes);

        view.renderUsers(writer, likedUsers, "liked-users.ftl");
    }
}
