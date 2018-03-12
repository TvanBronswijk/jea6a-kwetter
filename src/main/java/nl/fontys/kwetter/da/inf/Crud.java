package nl.fontys.kwetter.da.inf;

import nl.fontys.kwetter.model.Model;

public interface Crud<T extends Model> {
    void create(T entity);

    T read(Long id);

    void update(T entity);

    void delete(T entity);
}