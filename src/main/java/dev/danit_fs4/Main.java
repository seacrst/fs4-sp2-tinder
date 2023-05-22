package dev.danit_fs4;

import dev.danit_fs4.DAO.AccountDao;
import dev.danit_fs4.DAO.LikeDao;
import dev.danit_fs4.DAO.UserListDao;
import dev.danit_fs4.DAO.MessageDataBaseDao;
import dev.danit_fs4.DAO.UserDao;
import dev.danit_fs4.Entity.Message;
import dev.danit_fs4.Servlet.*;
import dev.danit_fs4.config.Config;
import dev.danit_fs4.config.HerokuConfig;
import dev.danit_fs4.db.DataBase;
import dev.danit_fs4.services.AccountService;
import dev.danit_fs4.services.UserService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.Connection;


public class Main {


    public static void main(String[] args) throws Exception {

        Config config = new HerokuConfig();


        // міграція
//         DataBase.checkAndApplyDeltas(config);

        Connection connection = DataBase.connect(config).orElseThrow();

        Server server = new Server(config.port(8080));

        ServletContextHandler handler = new ServletContextHandler();
        UserListDao dao = new UserListDao();
        UserDao userDao = new UserDao(connection);
        LikeDao likeDao = new LikeDao(userDao, connection);
        AccountDao AD = new AccountDao(connection);
        AccountService AS =new AccountService(AD);
        UserService US =new UserService(userDao);
        MessageDataBaseDao msgDataBaseDao =new MessageDataBaseDao(connection);
//        handler.addServlet(new ServletHolder(new TestServlet()),"/");
        handler.addServlet(new ServletHolder(new StaticContentServlet()),"/static/*");
        handler.addServlet(new ServletHolder(new LoginServlet(AS)),"/login");
        handler.addServlet(new ServletHolder(new UsersServlet(US, likeDao, AS)),"/users");
//        handler.addServlet(new ServletHolder(new MessageServlet()),"/messages/*");
        handler.addServlet(new ServletHolder(new LikeServlet(likeDao, AS)),"/liked");
        handler.addServlet(new ServletHolder(new LoginServlet(AS)),"/login");
//        handler.addServlet(new ServletHolder(new UsersServlet(userDatabaseDao, LD)),"/users");
        handler.addServlet(new ServletHolder(new MessageServlet(msgDataBaseDao, US, AS)),"/messages/*");
//        handler.addServlet(new ServletHolder(new LikeServlet(connection)),"/liked");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
