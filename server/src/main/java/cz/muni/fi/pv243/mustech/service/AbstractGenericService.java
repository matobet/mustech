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
    public void saveOrUpdate(T t)
    {
        if(t == null)
        {
            throw new IllegalArgumentException("Entity is null.");
        }
        getRepository().save(t);
    }

    @Override
    public void delete(Long id)
    {
        if(id == null || id <= 0)
        {
            throw new IllegalArgumentException("Id must be posotive.");
        }

        getRepository().remove(getRepository().findBy(id));
    }

    @Override
    public T findById(Long id)
    {
        if(id == null || id <= 0)
        {
            throw new IllegalArgumentException("Id must be posotive.");
        }

        return getRepository().findBy(id);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }
}
