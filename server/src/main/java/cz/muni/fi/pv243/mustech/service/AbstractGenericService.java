package cz.muni.fi.pv243.mustech.service;


import org.apache.deltaspike.data.api.EntityRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Abstract generic service with common methods for several entity services
 * @author Milan
 */
@Transactional
public abstract class AbstractGenericService<T, R extends EntityRepository<T, Long>> implements GenericService<T> {

    @Inject
    protected R repository;

    @Override
    public void saveOrUpdate(T t)
    {
        if(t == null)
        {
            throw new IllegalArgumentException("Entity is null.");
        }
        repository.save(t);
    }

    @Override
    public void delete(Long id)
    {
        if(id == null || id <= 0)
        {
            throw new IllegalArgumentException("Id must be positive.");
        }

        repository.remove(repository.findBy(id));
    }

    @Override
    public T findById(Long id)
    {
        if(id == null || id <= 0)
        {
            throw new IllegalArgumentException("Id must be positive.");
        }
        return repository.findBy(id);
    }

    @Override
    public List<T> findAll() { return repository.findAll(); }
}
