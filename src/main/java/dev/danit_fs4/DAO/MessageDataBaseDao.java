package dev.danit_fs4.DAO;

import dev.danit_fs4.Entity.Message;
import dev.danit_fs4.db.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MessageDataBaseDao implements DAO<Message> {

    private final Connection connection = DataBase.getConnection();

    @Override
    public void save(Message msg) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO messages (created_at, sender_id, receiver_id, body) values (?,?,?,?)");
        stmt.setString(1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MM yyyy  HH:mm")));
        stmt.setInt(2, msg.getIdSender());
        stmt.setInt(3, msg.getIdReceiver());
        stmt.setString(4, msg.getBody());
        stmt.execute();
    }

    @Override
    public Optional<Message> load(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "SELECT  created_at, body, sender_id, receiver_id FROM messages WHERE id = ? ORDER BY id"
        );
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return Optional.of(new Message(
                    rs.getInt("id"),
                    rs.getString("created_at"),
                    rs.getString("body"),
                    rs.getInt("sender_id"),
                    rs.getInt("receiver_id")
            ));
        }
        return Optional.empty();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM messages WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public List<Message> loadHistory(int idSender, int idReceiver) throws SQLException {
        List<Message> messageHistory = new LinkedList<>();
        PreparedStatement stmt = connection.prepareStatement(
                "SELECT id, created_at, body, sender_id, receiver_id FROM messages WHERE sender_id = ? AND receiver_id = ? OR sender_id = ? AND receiver_id = ? ORDER BY id"
        );
        stmt.setInt(1, idSender);
        stmt.setInt(2, idReceiver);
        stmt.setInt(3, idReceiver);
        stmt.setInt(4, idSender);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Message msg = new Message(
                    rs.getInt("id"),
                    rs.getString("created_at"),
                    rs.getString("body"),
                    rs.getInt("sender_id"),
                    rs.getInt("receiver_id")
            );
            messageHistory.add(msg);
        }
        return messageHistory;
    }
}
