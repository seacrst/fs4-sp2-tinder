package dev.danit_fs4;

import dev.danit_fs4.servlets.*;
import dev.danit_fs4.config.Config;
import dev.danit_fs4.config.HerokuConfig;
import dev.danit_fs4.db.DataBase;
import dev.danit_fs4.filters.AuthFilter;
import dev.danit_fs4.filters.loggedFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;


public class Main {


    public static void main(String[] args) throws Exception {

        Config config = new HerokuConfig();

        // міграція
         DataBase.checkAndApplyDeltas(config);

        Connection connection = DataBase.connect(config).orElseThrow();
        DataBase.setConnection(connection);

        Server server = new Server(config.port(8080));

        ServletContextHandler handler = new ServletContextHandler();

        EnumSet<DispatcherType> dt = EnumSet.of(DispatcherType.REQUEST);
        handler.addServlet(new ServletHolder(new RootServlet()),"");
        handler.addServlet(new ServletHolder(new StaticContentServlet()),"/static/*");

        handler.addFilter(new FilterHolder(loggedFilter.class), "/login", dt);
        handler.addFilter(new FilterHolder(AuthFilter.class), "/users", dt);
        handler.addFilter(new FilterHolder(AuthFilter.class), "/liked", dt);
        handler.addFilter(new FilterHolder(AuthFilter.class), "/messages/*", dt);
        handler.addFilter(new FilterHolder(AuthFilter.class), "/logout", dt);

        handler.addServlet(new ServletHolder(new LoginServlet()),"/login");
        handler.addServlet(new ServletHolder(new UsersServlet()),"/users");
        handler.addServlet(new ServletHolder(new LikeServlet()),"/liked");
        handler.addServlet(new ServletHolder(new MessageServlet()),"/messages/*");
        handler.addServlet(new ServletHolder(new LogoutServlet()),"/logout");
        handler.addServlet(new ServletHolder(new ServletRedirectTo("/users")), "/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
