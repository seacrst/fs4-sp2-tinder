package dev.danit_fs4.Servlet;

import dev.danit_fs4.DAO.LikeDao;
import dev.danit_fs4.DAO.UserDatabaseDao;
import dev.danit_fs4.Entity.User;
import dev.danit_fs4.Utils.Auth;
import dev.danit_fs4.Utils.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LikeServlet extends HttpServlet {
    private final LikeDao likeDao;
    private final UserDatabaseDao usersDao;

    public LikeServlet(UserDatabaseDao usersDao, LikeDao likeDao) {
        this.likeDao = likeDao;
        this.usersDao = usersDao;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        if(Auth.getCookie(req).isPresent()) {
            Optional<Integer> id = null;
            try {
                id = usersDao.getId(Auth.getCookie(req).get());
                Integer uid = id.orElseThrow();
                List<User> likedUsers = likeDao.getLikedUsers(uid);
                View view = new View("/templates");

                view.renderUsers(writer, likedUsers, "liked-users.ftl");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logout = req.getParameter("logout");
        System.out.println(logout);

    }
}
