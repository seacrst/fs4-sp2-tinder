package dev.danit_fs4.Servlet;

import dev.danit_fs4.DAO.LikeDao;
import dev.danit_fs4.Entity.User;
import dev.danit_fs4.Utils.Auth;
import dev.danit_fs4.services.AccountService;
import dev.danit_fs4.Utils.View;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LikeServlet extends HttpServlet {
    private final LikeDao likeDao;
    private final AccountService AS;

    public LikeServlet(LikeDao likeDao, AccountService AS) {
        this.likeDao = likeDao;
        this.AS =AS;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();
        if(Auth.getCookie(req).isPresent()) {

            Integer uid = AS.getId(Auth.getCookie(req).get());
            List<User> likedUsers = likeDao.getLikedUsers(uid);
            View view = new View("/templates");
            view.renderUsers(writer, likedUsers, "liked-users.ftl");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logout = req.getParameter("logout");
        System.out.println(logout);

    }
}
