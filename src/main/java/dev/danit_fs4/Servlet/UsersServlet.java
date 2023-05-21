package dev.danit_fs4.Servlet;

import dev.danit_fs4.DAO.LikeDao;
import dev.danit_fs4.DAO.UserDatabaseDao;
import dev.danit_fs4.Utils.Auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class UsersServlet extends HttpServlet {
//    private final UserDao userDao;
    private final UserDatabaseDao users;
    private final LikeDao like;
    private Integer currInd = 0;
    private Integer activeUser=1;
    public UsersServlet(UserDatabaseDao users, LikeDao like) {
        this.users = users;
        this.like = like;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // static file
//        Path filePath = Path.of(ResourcesOps.dirUnsafe("templates/like-page.html"));
//        try (PrintWriter w = resp.getWriter()) {
//            Files
//                    .readAllLines(filePath)
//                    .forEach(w::println);
//        }
        // data from DB List
//        data.put("id", userDao.getByIndex(currentUserIndex).getId());
//        data.put("name", userDao.getByIndex(currentUserIndex).getName());
//        data.put("photo", userDao.getByIndex(currentUserIndex).getPhoto());

        setActiveUser(req);
        // data from DB conn
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", users.getNext(currInd, activeUser).getId());
        data.put("name", users.getNext(currInd, activeUser).getName());
        data.put("photo", users.getNext(currInd, activeUser).getPhoto());

        ResourcesOps.writeInto(resp, data,"like-page.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            submitAnswer(req.getParameter("answer"), req.getParameter("id"));
            System.out.println(req.getParameter("answer"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (currInd >= users.size()){
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
        try {
            Optional<Integer> id = users.getId(Auth.getCookie(req));
            id.ifPresent(integer -> activeUser = integer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
