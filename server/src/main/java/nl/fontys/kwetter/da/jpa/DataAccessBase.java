package nl.fontys.kwetter.da.jpa;

import nl.fontys.kwetter.annotations.Logged;
import nl.fontys.kwetter.model.Model;

import javax.ejb.NoSuchEntityException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public abstract class DataAccessBase<T extends Model> {

    private static final Logger LOGGER = Logger.getLogger(DataAccessBase.class.getName());

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
    public T read(Long id) throws NoResultException {
        T result =  entityManager.find(classObject, id);
        if(result == null){
            throw new NoResultException("Entity not found.");
        }
        return result;
    }

    @Logged
    public void update(T entity) throws InvalidParameterException {
        try {
            read(entity.getId());
            entityManager.merge(entity);
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            throw new InvalidParameterException();
        }
    }

    @Logged
    public void delete(T entity) throws InvalidParameterException {
        try {
            read(entity.getId());
            entityManager.remove(entityManager.merge(entity));
        } catch (NoResultException e){
            LOGGER.log(Level.WARNING, e.getMessage());
            throw new InvalidParameterException();
        }

    }
}
