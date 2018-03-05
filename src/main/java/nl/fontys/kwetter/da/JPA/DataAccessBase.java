package nl.fontys.kwetter.da.JPA;

import nl.fontys.kwetter.da.Crud;
import java.util.Collection;

public abstract class DataAccessBase<T> implements Crud<T> {

    @Override
    public void create(T entity) {

    }

    @Override
    public Collection<T> read(){
        return null;
    }

    @Override
    public T read(Object id) {
        return null;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(T entity) {

    }
}
