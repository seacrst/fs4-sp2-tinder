package dev.danit_fs4.Servlet;

import dev.danit_fs4.DAO.UserDatabaseDao;
import dev.danit_fs4.Entity.User;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;

public class UsersServlet_v2 extends HttpServlet {
//    private final UserDao userDao;
    private final UserDatabaseDao users;
    private Integer currentUserIndex = 0;
    public UsersServlet_v2(UserDatabaseDao users) {
        this.users = users;
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

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("templates")));

        HashMap<String, Object> data = new HashMap<>();
        // data from DB List
//        data.put("id", userDao.getByIndex(currentUserIndex).getId());
//        data.put("name", userDao.getByIndex(currentUserIndex).getName());
//        data.put("photo", userDao.getByIndex(currentUserIndex).getPhoto());

        // data from DB conn
//        data.put("id", users.getNext(currentUserIndex).getId());
//        data.put("name", users.getNext(currentUserIndex).getName());
//        data.put("photo", users.getNext(currentUserIndex).getPhoto());
        // data from DB conn
//        Optional<User> nextUser = users.getNextUser(currentUserIndex);
//        if (nextUser.isPresent()) {
//            data.put("id", nextUser.get().getId());
//            data.put("name", nextUser.get().getName());
//            data.put("photo", nextUser.get().getPhoto());
//        }


        try (PrintWriter w = resp.getWriter()) {
            cfg
                    .getTemplate("like-page.ftl")
                    .process(data, w);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        submitAnswer(req.getParameter("answer"), currentUserIndex);

            if (currentUserIndex >= users.size()){
                currentUserIndex = 0;
                resp.sendRedirect("/liked");
            }
            else {
                resp.sendRedirect("/users");
            }
    }
    private void submitAnswer(String userAnswer, int i) {
        if (userAnswer.equals("Yes")) users.addLikedUser(i);
        if(userAnswer.equals("No")) users.removeLikedUser(i);
        currentUserIndex++;
    }

}
