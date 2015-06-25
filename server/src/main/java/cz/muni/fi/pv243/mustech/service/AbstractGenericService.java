package cz.muni.fi.pv243.mustech.service;


import cz.muni.fi.pv243.mustech.model.BaseModel;
import org.apache.deltaspike.data.api.EntityRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract generic service with common methods for several entity services
 * @author Milan
 */
public abstract class AbstractGenericService<T extends BaseModel, R extends EntityRepository<T, Long>> implements GenericService<T> {

    @Inject
    protected R repository;

    @Inject
    private IPermissionService permissionService;

    protected boolean canAccess(T entity) {
        // we need to delegate the principal and role check to this Stateless EJB since
        // CDI beans can't access the security context
        return permissionService.checkAccess(this, entity);
    }

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
        T entity = repository.findBy(id);
        if (!canAccess(entity)) {
            return null;
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll().stream()
                .filter(this::canAccess)
                .collect(Collectors.toList());
    }
}
