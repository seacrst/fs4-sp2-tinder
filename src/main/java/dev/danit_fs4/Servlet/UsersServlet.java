package dev.danit_fs4.Servlet;

import dev.danit_fs4.DAO.UserDatabaseDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class UsersServlet extends HttpServlet {
//    private final UserDao userDao;
    private final UserDatabaseDao users;
    private Integer currInd = 0;
    public UsersServlet(UserDatabaseDao users) {
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

//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
//        cfg.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
//        cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("templates")));

        HashMap<String, Object> data = new HashMap<>();
        // data from DB List
//        data.put("id", userDao.getByIndex(currentUserIndex).getId());
//        data.put("name", userDao.getByIndex(currentUserIndex).getName());
//        data.put("photo", userDao.getByIndex(currentUserIndex).getPhoto());

        // data from DB conn
        data.put("id", users.getNext(currInd).getId());
        data.put("name", users.getNext(currInd).getName());
        data.put("photo", users.getNext(currInd).getPhoto());

//        try (PrintWriter w = resp.getWriter()) {
//            cfg
//                    .getTemplate("like-page.ftl")
//                    .process(data, w);
//        } catch (TemplateException e) {
//            throw new RuntimeException(e);
//        }
        ResourcesOps.writeInto(resp, data,"like-page.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        submitAnswer(req.getParameter("answer"), req.getParameter("id"));

        if (currInd >= users.size()){
            currInd = 0;
            resp.sendRedirect("/liked");
        }
        else resp.sendRedirect("/users");
    }
    private void submitAnswer(String userAnswer, String i) {
        int id = Integer.parseInt(i);
        if (userAnswer.equals("Yes")) users.addLikedUser(id);
        if(userAnswer.equals("No")) users.removeLikedUser(id);
        currInd++;
    }
}
