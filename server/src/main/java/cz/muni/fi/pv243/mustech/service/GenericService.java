package cz.muni.fi.pv243.mustech.service;

import java.util.List;

/**
 * Interface for generic service with common methods
 * @author Milan
 */
public interface GenericService<T> {

    void saveOrUpdate(T t);
    void delete(Long id);
    T findById(Long id);
    List<T> findAll();
}
