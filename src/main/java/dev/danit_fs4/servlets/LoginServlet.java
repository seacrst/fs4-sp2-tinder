package dev.danit_fs4.servlets;

import dev.danit_fs4.utils.Auth;
import dev.danit_fs4.utils.View;
import dev.danit_fs4.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private final AccountService accountService = new AccountService();
    private final View view = new View();
    private boolean warningMessage = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        data.put("message", warningMessage);
        view.render(resp.getWriter(), data,"login.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (accountService.check(email, password)){
            accountService.addUUID(email, Auth.addCookie(resp));
            warningMessage = false;
            resp.sendRedirect("/users");
        } else {
            warningMessage = true;
            resp.sendRedirect("/login");
        };
    }

}
