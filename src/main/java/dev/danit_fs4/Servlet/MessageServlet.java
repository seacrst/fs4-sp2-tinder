package dev.danit_fs4.Servlet;

import dev.danit_fs4.Entity.Chat;
import dev.danit_fs4.Utils.Auth;
import dev.danit_fs4.Utils.View;
import dev.danit_fs4.services.AccountService;
import dev.danit_fs4.services.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class MessageServlet extends HttpServlet {

    private final MessageService chatService = new MessageService();
    private final AccountService accountService = new AccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> answerMap = req.getParameterMap();
        int idReceiver = Integer.parseInt(answerMap.get("idReceiver")[0]);
        chatService.saveMessage(
                answerMap.get("body")[0],
                Integer.parseInt(answerMap.get("idSender")[0]),
                idReceiver);
        resp.sendRedirect("/messages/" + idReceiver);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hostId = getHostId(req);
        int guestId = Integer.parseInt(req.getPathInfo().substring(1));
        Chat chat = chatService.getChat(hostId, guestId);

        PrintWriter writer = resp.getWriter();
        View vm = new View("/templates");
        if (chat.getHistory().size() != 0)
            vm.render(writer, chatService.getViewData(chat), "chat_exist.ftl");
        else
            vm.render(writer, chatService.getViewData(chat), "chat_empty.ftl");
    }

    private int getHostId(HttpServletRequest req) {
        Integer id = null;
        if (Auth.getCookie(req).isPresent()) {
            id = accountService.getId(Auth.getCookie(req).get());
        }
        return id;
    }
}


//
//
//    private final MessageDataBaseDao messages = new MessageDataBaseDao();
////    private final UserDatabaseDao users;
//    private final UserService users = new UserService();
//    private final AccountService accountService = new AccountService();
//    private Integer hostId;
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Map<String, String[]> answerMap = req.getParameterMap();
//        int idReceiver;
//        try {
//            idReceiver = Integer.parseInt(answerMap.get("idReceiver")[0]);
//            messages.save(new Message(
//                    answerMap.get("body")[0],
//                    Integer.parseInt(answerMap.get("idSender")[0]),
//                    idReceiver
//            ));
//        } catch (
//                SQLException e) {
//            throw new RuntimeException(e);
//        }
//        resp.sendRedirect("/messages/" + idReceiver);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        //Отримуємо ID користувача в hostID
//        setActiveUser(req);
//
//        //Отримуємо ID гостя в guestID
//        int guestId = Integer.parseInt(req.getPathInfo().substring(1));
//
//        //Отримуємо історію листування між користувачем та гостем
//        List<Message> messagesHistory;
//        try {
//            messagesHistory = messages.loadHistory(hostId, guestId);
//        } catch (
//                SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        //Отримуємо імена користувача та гостя в hostName та guestName
//        String hostName;
//        String guestName;
//
//            hostName = users.getUser(hostId).get().getName();
//            guestName = users.getUser(guestId).get().getName();
//
//
//        // формуємо чат-об'єкт
//        Chat chat = new Chat(messagesHistory, hostId, guestId, hostName, guestName);
//
//        //якщо історія листування порожня - використовуємо ftl з чистим чатиком
//        PrintWriter writer = resp.getWriter();
//        ViewMessage vm = new ViewMessage("/templates");
//        if (messagesHistory.size() != 0)
//            vm.renderMessages(writer, chat, "chat_exist.ftl");
//        else
//            vm.renderMessages(writer, chat, "chat_empty.ftl");
//    }
//
//    private void setActiveUser(HttpServletRequest req){
//
//            if(Auth.getCookie(req).isPresent()) {
//                Integer id = accountService.getId(Auth.getCookie(req).get());
//                hostId = id;
//            }
//
//    }
//
//}
