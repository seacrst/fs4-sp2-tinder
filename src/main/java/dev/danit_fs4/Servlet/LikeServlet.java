package dev.danit_fs4.Servlet;

import dev.danit_fs4.Entity.User;
import dev.danit_fs4.Utils.Auth;
import dev.danit_fs4.services.AccountService;
import dev.danit_fs4.Utils.View;
import dev.danit_fs4.services.LikeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LikeServlet extends HttpServlet {
    private final LikeService likeService = new LikeService();
    private final AccountService accountService = new AccountService();
    private final View view = new View("/templates");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(Auth.getCookie(req).isPresent()) {
            Integer uid = accountService.getId(Auth.getCookie(req).get());
            List<User> likedUsers = likeService.getLikedUsers(uid);

            Map<String, Object> likedData = new HashMap<>();
            likedData.put("users", likedUsers);

            view.render(res.getWriter(), likedData, "liked-users.ftl");
        }
    }
}
