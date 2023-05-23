package dev.danit_fs4.services;

import dev.danit_fs4.dao.AccountDao;
import dev.danit_fs4.entities.Account;
import dev.danit_fs4.db.DataBase;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccountService {
    public final AccountDao AD = new AccountDao(DataBase.getConnection());
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
    public void addUUID(String email, String uuid) {
        Account account = authData.get(email);
        Account updatedAccount = new Account(account.id(), account.email(), account.password(), uuid);
        try {
            AD.save(updatedAccount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUUID(String uuid) {
        try {
            Optional<Account> account = AD.getByUUID(uuid);
            if (account.isPresent()) {
                Account updatedAccount = new Account(account.get().id(), account.get().email(), account.get().password(), null);
                AD.save(updatedAccount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Integer> getId(String uuid) {
        try {
            Optional<Account> account = AD.getByUUID(uuid);
            return account.map(Account::id).stream().findFirst();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}