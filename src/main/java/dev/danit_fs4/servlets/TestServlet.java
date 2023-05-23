package dev.danit_fs4.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class TestServlet extends HttpServlet {
    String message = "Hello world";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Writer writer = resp.getWriter()) {
            writer.write(message);
        }
    }
}
