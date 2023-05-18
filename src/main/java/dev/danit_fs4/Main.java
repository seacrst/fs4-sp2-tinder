package dev.danit_fs4;

import dev.danit_fs4.DAO.UserDao;
import dev.danit_fs4.DAO.UserDatabaseDao;
import dev.danit_fs4.Servlet.*;
import dev.danit_fs4.config.Config;
import dev.danit_fs4.config.HerokuConfig;
import dev.danit_fs4.db.DataBase;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.Connection;


public class Main {


    public static void main(String[] args) throws Exception {

        Config config = new HerokuConfig();


        // міграція
        // DataBase.checkAndApplyDeltas(config);

        Connection connection = DataBase.connect(config).orElseThrow();

        Server server = new Server(config.port(8080));

        ServletContextHandler handler = new ServletContextHandler();
        UserDao dao = new UserDao();
        UserDatabaseDao userDatabaseDao = new UserDatabaseDao(connection);
        handler.addServlet(new ServletHolder(new TestServlet()),"/");
        handler.addServlet(new ServletHolder(new StaticContentServlet()),"/static/*");
        handler.addServlet(new ServletHolder(new LoginServlet(userDatabaseDao)),"/login");
        handler.addServlet(new ServletHolder(new UsersServlet(userDatabaseDao)),"/users");
        handler.addServlet(new ServletHolder(new MessageServlet()),"/message");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
