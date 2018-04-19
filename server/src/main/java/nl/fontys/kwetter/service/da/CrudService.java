package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.Crud;
import nl.fontys.kwetter.model.Model;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@Transactional
@Stateless
public abstract class CrudService<T extends Model> {

    private static final Logger LOGGER = Logger.getLogger(CrudService.class.getName());

    protected abstract Crud<T> getDao();

    public T get(Long id) {
        return getDao().read(id);
    }

    public T create(T entity) {
        getDao().create(entity);
        return entity;
    }

    public T update(T entity) {
        getDao().update(entity);
        return entity;
    }

    public T delete(T entity) {
        getDao().delete(entity);
        return entity;
    }


}
