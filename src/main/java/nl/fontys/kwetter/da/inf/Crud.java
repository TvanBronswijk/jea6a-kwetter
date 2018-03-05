package nl.fontys.kwetter.da.inf;

public interface Crud<T> {
    void create(T entity);
    T read(Object id);
    void update(T entity);
    void delete(T entity);
}
