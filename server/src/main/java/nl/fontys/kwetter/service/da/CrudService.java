package nl.fontys.kwetter.service.da;

import nl.fontys.kwetter.da.inf.Crud;
import nl.fontys.kwetter.model.Model;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

@Transactional
@Stateless
public abstract class CrudService<T extends Model> {

    protected abstract Crud<T> getDao();

    public T get(Long id) {
        return getDao().read(id);
    }

    public void create(T entity) {
        getDao().create(entity);
    }

    public void update(T entity) {
        getDao().update(entity);
    }

    public void delete(T entity) {
        getDao().delete(entity);
    }


}
