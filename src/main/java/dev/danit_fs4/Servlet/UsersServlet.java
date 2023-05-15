package dev.danit_fs4.Servlet;

import dev.danit_fs4.DAO.UserDao;
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

public class UsersServlet extends HttpServlet {
    private final UserDao users;
    private Integer currentUserIndex = 0;
    public UsersServlet(UserDao users) {
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

        // data from DB List
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("templates")));

        HashMap<String, Object> data = new HashMap<>();
        data.put("id", users.getByIndex(currentUserIndex).getId());
        data.put("name", users.getByIndex(currentUserIndex).getName());
        data.put("photo", users.getByIndex(currentUserIndex).getPhoto());


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
        resp.sendRedirect("/users");
    }

    public void submitAnswer(String userAnswer, int index) {
        currentUserIndex = (currentUserIndex + 1) % users.getAll().size();
    }
}
