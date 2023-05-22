package dev.danit_fs4.Servlet;

import dev.danit_fs4.DAO.LikeDao;
import dev.danit_fs4.Utils.Auth;
import dev.danit_fs4.services.AccountService;
import dev.danit_fs4.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class UsersServlet extends HttpServlet {
//    private final UserDao userDao;
    private final UserService US;
    private final LikeDao like;
    private Integer currInd = 0;
    private Integer activeUser;
    private AccountService AS;
    public UsersServlet(UserService US, LikeDao like, AccountService AS) {
        this.US = US;
        this.like = like;
        this.AS =AS;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setActiveUser(req);
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", US.getNextUser(currInd, activeUser).getId());
        data.put("name", US.getNextUser(currInd, activeUser).getName());
        data.put("photo", US.getNextUser(currInd, activeUser).getPhoto());

        ResourcesOps.writeInto(resp, data,"like-page.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            submitAnswer(req.getParameter("answer"), req.getParameter("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (currInd >= US.size()){
            currInd = 0;
            resp.sendRedirect("/liked");
        }
        else resp.sendRedirect("/users");
    }
    private void submitAnswer(String userAnswer, String i) throws SQLException {
        int id = Integer.parseInt(i);
        if (userAnswer.equals("yes")) like.addLiked(activeUser, id);
        if (userAnswer.equals("no")) like.removeLikedUser(activeUser, id);
        currInd++;
    }
    private void setActiveUser(HttpServletRequest req){
        if(Auth.getCookie(req).isPresent()) {
            activeUser = AS.getId(Auth.getCookie(req).get());
        }
    }
}
