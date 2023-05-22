package dev.danit_fs4.Servlet;

import dev.danit_fs4.Utils.Auth;
import dev.danit_fs4.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private final AccountService AS;
    private boolean warningMessage = false;
    public LoginServlet( AccountService as) {
        this.AS = as;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        data.put("message", warningMessage);
        ResourcesOps.writeInto(resp, data,"login.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (AS.check(email, password)){
            AS.addCookie(email, Auth.addCookie(resp));
            warningMessage = false;
            resp.sendRedirect("/users");
        } else {
            warningMessage = true;
            resp.sendRedirect("/login");
        };
    }

}
