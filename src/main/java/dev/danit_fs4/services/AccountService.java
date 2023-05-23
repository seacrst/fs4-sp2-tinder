package dev.danit_fs4.services;

import dev.danit_fs4.DAO.AccountDao;
import dev.danit_fs4.DAO.LikeDao;
import dev.danit_fs4.DAO.UserDao;
import dev.danit_fs4.Entity.Account;
import dev.danit_fs4.db.DataBase;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountService {
    public final AccountDao AD = new AccountDao(DataBase.getConnection());
    public final LikeDao likeDao = new LikeDao();
    Map<String, Account> authData = new HashMap<>();

    public boolean check(String email, String password) {
        try {
            authData = AD.getAuthData(email);
            if (authData.isEmpty()) return false;
            if (authData.get(email).password().equals(password)) return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCookie(String email, String uuid) {
        Account account = authData.get(email);
        Account updatedAccount = new Account(account.id(), account.email(), account.password(), uuid);
        try {
            AD.save(updatedAccount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCookie(String uuid) {
        try {
            if (AD.getByUUID(uuid).isPresent()) {
                Account account = AD.getByUUID(uuid).get();
                Account updatedAccount = new Account(account.id(), account.email(), account.password(), null);
                AD.save(updatedAccount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getId(String uuid) {
        try {
            Optional<Account> account = AD.getByUUID(uuid);
            if (account.isPresent()) {
                return account.orElseThrow().id();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}