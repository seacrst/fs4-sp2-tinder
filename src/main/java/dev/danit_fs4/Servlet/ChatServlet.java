package dev.danit_fs4.Servlet;

import dev.danit_fs4.Entity.Chat;
import dev.danit_fs4.Utils.Auth;
import dev.danit_fs4.Utils.ViewMessage;
import dev.danit_fs4.services.AccountService;
import dev.danit_fs4.services.ChatService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ChatServlet extends HttpServlet {

    private final ChatService chatService;
    private final AccountService accountService;

    public ChatServlet(ChatService chatService, AccountService accountService) {
        this.chatService = chatService;
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hostId = getHostId(req);
        int guestId = Integer.parseInt(req.getPathInfo().substring(1));
                Chat chat = chatService.getChat(hostId,guestId);

        PrintWriter writer = resp.getWriter();
        ViewMessage vm = new ViewMessage("/templates");
        if (chat.getHistory().size() != 0)
            vm.renderMessages(writer, chat, "chat_exist.ftl");
        else
            vm.renderMessages(writer, chat, "chat_empty.ftl");
    }

    private int getHostId(HttpServletRequest req) {
        Integer id = null;
        if (Auth.getCookie(req).isPresent()) {
            id = accountService.getId(Auth.getCookie(req).get());
        }
        return id;
    }
}
