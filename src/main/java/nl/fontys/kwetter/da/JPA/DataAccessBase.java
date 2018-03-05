package nl.fontys.kwetter.da.JPA;

import nl.fontys.kwetter.da.inf.Crud;

import javax.persistence.EntityManager;

public abstract class DataAccessBase<T> implements Crud<T> {

    private Class<T> classObject;

    protected abstract EntityManager getEntityManager();

    public DataAccessBase(Class<T> classObject){
        this.classObject = classObject;
    }

    @Override
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public T read(Object id) {
        return getEntityManager().find(classObject, id);
    }

    @Override
    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
}
