package dev.danit_fs4.Servlet;

import dev.danit_fs4.DAO.UserDatabaseDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private final UserDatabaseDao users;
    public LoginServlet(UserDatabaseDao users) {
        this.users = users;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResourcesOps.writeInto(resp, null,"login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (checkAuth(req.getParameter("email"), req.getParameter("password") )){
            resp.sendRedirect("/users");
        } else {
            resp.sendRedirect("/login");
        };
    }

    private boolean checkAuth(String email, String password){
        return true;
    }
}
