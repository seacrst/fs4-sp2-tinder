package dev.danit_fs4.servlets;

import dev.danit_fs4.entities.User;
import dev.danit_fs4.utils.Auth;
import dev.danit_fs4.services.AccountService;
import dev.danit_fs4.utils.View;
import dev.danit_fs4.services.LikeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LikeServlet extends HttpServlet {
    private final LikeService likeService = new LikeService();
    private final AccountService accountService = new AccountService();
    private final View view = new View("/templates");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Optional<Integer> uid = Auth.getLoggedUser(req, res, accountService);
        if(uid.isPresent()) {
            List<User> likedUsers = likeService.getLikedUsers(uid.get());

            Map<String, Object> likedData = new HashMap<>();
            likedData.put("users", likedUsers);

            view.render(res.getWriter(), likedData, "liked-users.ftl");
        }
    }
}
