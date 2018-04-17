package nl.fontys.kwetter.da.jpa;

import nl.fontys.kwetter.annotations.Logged;
import nl.fontys.kwetter.model.Model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public abstract class DataAccessBase<T extends Model> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> classObject;

    public DataAccessBase(Class<T> classObject) {
        this.classObject = classObject;
    }

    @Logged
    public void create(T entity) {
        entityManager.persist(entity);
    }

    @Logged
    public T read(Long id) {
        return entityManager.find(classObject, id);
    }

    @Logged
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Logged
    public void delete(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }
}
