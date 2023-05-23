package dev.danit_fs4.Servlet;

import dev.danit_fs4.Utils.Auth;
import dev.danit_fs4.Utils.View;
import dev.danit_fs4.services.AccountService;
import dev.danit_fs4.services.LikeService;
import dev.danit_fs4.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class UsersServlet extends HttpServlet {
    private final View view = new View();
    private final UserService userService = new UserService();
    private final LikeService likeService = new LikeService();
    private Integer currInd = 0;
    private Optional<Integer> activeUser;
    private final AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setActiveUser(req, resp);
        if(activeUser.isPresent()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("user", userService.getNextUser(currInd, activeUser.get()));
            view.render(resp.getWriter(), data, "like-page.ftl");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        submitAnswer(req.getParameter("answer"), req.getParameter("id"));
        if (currInd >= userService.size()){
            currInd = 0;
            resp.sendRedirect("/liked");
        }
        else resp.sendRedirect("/users");
    }
    private void submitAnswer(String userAnswer, String i) {
        if(activeUser.isPresent()) {
            int id = Integer.parseInt(i);
            if (userAnswer.equals("yes")) likeService.like(activeUser.get(), id);
            if (userAnswer.equals("no")) likeService.dislike(activeUser.get(), id);
            currInd++;
        }
    }
    private void setActiveUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        activeUser = Auth.getLoggedUser(req, resp, accountService);
    }
}
