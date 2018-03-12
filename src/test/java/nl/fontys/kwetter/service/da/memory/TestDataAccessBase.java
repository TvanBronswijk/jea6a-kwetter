package nl.fontys.kwetter.service.da.memory;

import nl.fontys.kwetter.model.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class TestDataAccessBase<T extends Model> {

    protected List<T> data = new ArrayList<>(Collections.nCopies(128, null));

    public void create(T entity) {
        data.set(entity.getId().intValue(), entity);
    }

    public T read(Long id) {
        return data.get(id.intValue());
    }

    public void update(T entity) {
        data.set(entity.getId().intValue(), entity);
    }

    public void delete(T entity) {
        data.set(entity.getId().intValue(), null);
    }
}
