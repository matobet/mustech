package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.BaseModel;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 * Simple EJB helper to access the Security Context from CDI service beans
 */
@Stateless
@Default
public class PermissionService implements IPermissionService{

    @Resource
    private EJBContext context;

    public <T extends BaseModel> boolean checkAccess(PrincipalChecker<T> checker, T entity) {
        String principal = context.getCallerPrincipal().getName();
        return context.isCallerInRole("ADMIN") || checker.canAccess(principal, entity);
    }
}
