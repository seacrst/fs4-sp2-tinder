package dev.danit_fs4.Servlet;

import dev.danit_fs4.Utils.Auth;
import dev.danit_fs4.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LogoutServlet extends HttpServlet {
    private final AccountService accountService = new AccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Auth.logout(req, resp, accountService);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
