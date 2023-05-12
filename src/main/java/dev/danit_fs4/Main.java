package dev.danit_fs4;

import dev.danit_fs4.DAO.UserDao;
import dev.danit_fs4.Servlet.StaticContentServlet;
import dev.danit_fs4.Servlet.TestServlet;
import dev.danit_fs4.Servlet.UsersServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class Main {


    public static void main(String[] args) throws Exception {
       Server server = new Server(8080);

        ServletContextHandler handler = new ServletContextHandler();
        UserDao dao = new UserDao();
        handler.addServlet(new ServletHolder(new TestServlet()),"/");
        handler.addServlet(new ServletHolder(new StaticContentServlet()),"/static/*");
        handler.addServlet(new ServletHolder(new UsersServlet(dao)),"/users");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
