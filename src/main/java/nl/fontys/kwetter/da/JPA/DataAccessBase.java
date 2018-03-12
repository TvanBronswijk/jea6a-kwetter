package nl.fontys.kwetter.da.JPA;

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

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public T read(Long id) {
        return entityManager.find(classObject, id);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entityManager.merge(entity));
    }
}
