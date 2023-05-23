package dev.danit_fs4.dao;

import java.sql.SQLException;
import java.util.Optional;

public interface DAO<A extends Identifiable> {
    void save(A a) throws SQLException;
    Optional<A> load(int id) throws SQLException;
    void delete(int id) throws SQLException;
    default void delete(A a) throws SQLException{
        delete(a.id());
    }
}
