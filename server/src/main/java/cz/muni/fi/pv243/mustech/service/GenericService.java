package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.BaseModel;

import java.util.List;

/**
 * Interface for generic service with common methods
 * @author Milan
 */
public interface GenericService<T extends BaseModel> extends PrincipalChecker<T> {

    void saveOrUpdate(T t);
    void delete(Long id);
    T findById(Long id);
    List<T> findAll();
}
