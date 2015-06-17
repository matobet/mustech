package cz.muni.fi.pv243.mustech.service;


import org.apache.deltaspike.data.api.EntityRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Milan
 */
@Transactional
public abstract class AbstractGenericService<T, R extends EntityRepository<T, Long>> implements GenericService<T> {

    private Class<T> tClass;

    public AbstractGenericService(Class<T> tClass) {
    }

    protected abstract R getRepository();

    @Override
    public void saveOrUpdate(T t) {
        getRepository().save(t);
    }

    @Override
    public void delete(Long id) {
        getRepository().remove(getRepository().findBy(id));
    }

    @Override
    public T findById(Long id) {
        return getRepository().findBy(id);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }
}
