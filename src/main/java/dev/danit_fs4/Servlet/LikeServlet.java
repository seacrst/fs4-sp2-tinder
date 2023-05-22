package dev.danit_fs4.Servlet;

import dev.danit_fs4.DAO.LikeDao;
import dev.danit_fs4.Entity.User;
import dev.danit_fs4.Utils.AuthService;
import dev.danit_fs4.services.AccountService;
import dev.danit_fs4.view.View;

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
        if(AuthService.getCookie(req).isPresent()) {

            Integer uid = AS.getId(AuthService.getCookie(req).get());
            List<User> likedUsers = likeDao.getLikedUsers(uid);
            View view = new View("/templates");
            view.renderUsers(writer, likedUsers, "liked-users.ftl");

        }

    }
}
