package dev.danit_fs4.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<A extends Identifiable> {
    void save(A a) throws SQLException;
    Optional<A> load(int id) throws SQLException;
    void delete(int id) throws SQLException;
    default void delete(A a) throws SQLException{
        delete(a.id());
    }
}
