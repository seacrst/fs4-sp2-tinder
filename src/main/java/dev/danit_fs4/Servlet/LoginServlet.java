package dev.danit_fs4.Servlet;

import dev.danit_fs4.DAO.UserDatabaseDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (checkAuth(email, password)){
            Cookie cookie = new Cookie("UUID", UUID.randomUUID().toString());
            resp.addCookie(cookie);
            try {
                users.updateCookie(cookie.getValue(), email);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect("/users");
        } else {
            resp.sendRedirect("/login");
        };
    }

    private boolean checkAuth(String email, String password){
        Map<String, String> authData = null;

        try{
            authData = users.getAuthData(email);
            System.out.println(authData);
            if (authData.isEmpty()) return false;
            if (authData.get(email).equals(password)) return true;
            return false;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
