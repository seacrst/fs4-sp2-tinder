package dev.danit_fs4.servlets;

import dev.danit_fs4.entities.Chat;
import dev.danit_fs4.utils.Auth;
import dev.danit_fs4.utils.View;
import dev.danit_fs4.services.AccountService;
import dev.danit_fs4.services.LikeService;
import dev.danit_fs4.services.MessageService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Optional;

public class MessageServlet extends HttpServlet {

    private final MessageService chatService = new MessageService();
    private final AccountService accountService = new AccountService();
    private final LikeService likeService = new LikeService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> answerMap = req.getParameterMap();
        int guestId = Integer.parseInt(answerMap.get("idReceiver")[0]);
        int hostId = Integer.parseInt(answerMap.get("idSender")[0]);

        if (likeService.isLiked(hostId, guestId)) {
            chatService.saveMessage(
                    answerMap.get("body")[0],
                    hostId,
                    guestId);
            resp.sendRedirect("/messages/" + guestId);
        } else {
            resp.sendRedirect("/users");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Integer> hostId = Auth.getLoggedUser(req, resp, accountService);
        int guestId = Integer.parseInt(req.getPathInfo().substring(1));
            if (hostId.isPresent() ) {
                if(likeService.isLiked(hostId.get(), guestId)) {
                    Chat chat = chatService.getChat(hostId.get(), guestId);
                    ServletOutputStream outputStream = resp.getOutputStream();
                    View vm = new View("/templates");
                    if (chat.getHistory().size() != 0)
                        vm.render(outputStream, chatService.getViewData(chat), "chat_exist.ftl");
                    else
                        vm.render(outputStream, chatService.getViewData(chat), "chat_empty.ftl");
                } else{
                    resp.sendRedirect("/users");
                }
            }
    }

    private Optional<Integer> getHostId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return Auth.getLoggedUser(req, resp, accountService);
    }
}