package nl.fontys.kwetter.da;

import java.util.Collection;

public interface Crud<T> {
    void create(T entity);
    Collection<T> read();
    T read(Object id);
    void update(T entity);
    void delete(T entity);
}
