package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.BaseModel;

/**
 * Created by Tomas on 25. 6. 2015.
 */
public interface IPermissionService {
    <T extends BaseModel> boolean checkAccess(PrincipalChecker<T> checker, T entity);
}
