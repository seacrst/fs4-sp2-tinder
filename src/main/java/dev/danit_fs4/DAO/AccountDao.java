package dev.danit_fs4.DAO;

import dev.danit_fs4.Entity.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AccountDao implements DAO<Account> {
    private final Connection connection;
    public AccountDao(Connection connection) {
        this.connection = connection;
    }
    HashMap <String, Account> data = new HashMap<>();
    @Override
    public void save(Account account) throws SQLException {
        if(account.id() == null) insert(account);
        else update(account);
    }

    @Override
    public Optional<Account> load(int id) throws SQLException {
        String sql = "SELECT id, email, password, uuid FROM users WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return Optional.of(new Account(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("uuid")

            ));
        }
        return Optional.empty();
    }
    public Optional<Account> getByUUID(String uuid) throws SQLException {
        String sql = "SELECT id, email, password, uuid FROM users WHERE uuid = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, uuid);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            return Optional.of(new Account(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("uuid")

            ));
        }
        return Optional.empty();
    }
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    private void insert(Account account) throws SQLException {
        String sql = "INSERT INTO users (email, password, uuid) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, account.email());
        stmt.setString(2, account.password());
        stmt.setString(3, account.uuid());
        stmt.execute();
    }
    private void update(Account account) throws SQLException {
        String sql = "UPDATE users SET email =?, password =?, uuid =? WHERE email =?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, account.email());
        stmt.setString(2, account.password());
        stmt.setString(3, account.uuid());
        stmt.setString(4, account.email());
        stmt.execute();
    }

    public Map<String, Account> getAuthData (String email) throws SQLException {
        String sql = "SELECT id, email, password, uuid FROM users WHERE email = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            data.put(
                    rs.getString("email"),
                    new Account(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("uuid")

                    )
            );
        }
        return data;
    }
}
