package dev.danit_fs4.Servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class StaticContentServlet extends HttpServlet {
    private final String staticLocation = "/static-content";
//    private final String staticLocation = ResourcesOps.dirUnsafe("static-content");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo.startsWith("/")) pathInfo = pathInfo.substring(1);
//
//        Path file = Path.of(staticLocation, pathInfo.trim());
//        System.out.println(file+" file");
//        if (!file.toFile().exists()) {
//            resp.setStatus(404);
//        } else {
//            try (ServletOutputStream os = resp.getOutputStream()) {
//                Files.copy(file, os);
//            }
//        }


        try (InputStream is = getClass().getResourceAsStream(staticLocation + "/" + pathInfo)) {
            if (is == null) {
                resp.setStatus(404);
            } else {
                try (OutputStream os = resp.getOutputStream()) {
                    int bytesRead;
                    while ((bytesRead = is.read()) != -1) {
                        os.write(bytesRead);
                    }
                }
            }
        }
    }
}
