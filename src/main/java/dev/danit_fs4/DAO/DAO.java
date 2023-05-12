package dev.danit_fs4.DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<A extends Identifiable> {
    void save(A a);
    Optional<A> load(int id);
    List<A> getAll();
    void delete(int id);
    default void delete(A a){
        delete(a.id());
    }
}
