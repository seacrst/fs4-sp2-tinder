package dev.danit_fs4.services;

import dev.danit_fs4.DAO.MessageDataBaseDao;
import dev.danit_fs4.DAO.UserDao;
import dev.danit_fs4.Entity.Chat;
import dev.danit_fs4.Entity.Message;
import dev.danit_fs4.Entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MessageService {
    private final MessageDataBaseDao messages = new MessageDataBaseDao();
    private final UserDao users = new UserDao();

    //Зберігає месседж в БД
    public void saveMessage(String body, int hostId, int guestId) {
        try {
            messages.save(new Message(
                    body,
                    hostId,
                    guestId
            ));
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Повертає історію листування між користувачем та гостем
    public List<Message> getMessagesHistory(int hostId, int guestId) {
        List<Message> messagesHistory;
        try {
            messagesHistory = messages.loadHistory(hostId, guestId);
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return messagesHistory;
    }

    //Повертає ім'я користувача
    public String getHostName(int hostId) {
        String result = "Users name not found";
        try {
            Optional<User> user = users.load(hostId);
            if (user.isPresent()) result = user.get().getName();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
        return result;
    }

    //Повертає ім'я гостя
    public String getGuestName(int guestId) {
        String result = "Guests name not found";
        try {
            Optional<User> guest = users.load(guestId);
            if (guest.isPresent()) result = guest.get().getName();
        } catch (SQLException s) {
            throw new RuntimeException(s);
        }
        return result;
    }

    // Повертає чат-об'єкт
    public Chat getChat(int hostId, int guestId) {
        return new Chat(getMessagesHistory(hostId, guestId), hostId, guestId, getHostName(hostId), getGuestName(guestId));
    }
}


