package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.BaseModel;

/**
 * Interfaces for service classes that are able to determine whether
 * a logged in user principal can access given entity.
 *
 * @param <T> Type of business entity that is checked for permission
 */
public interface PrincipalChecker<T extends BaseModel> {

    /** Returns whether principal with given name can access given entity */
    boolean canAccess(String principalName, T entity);
}
