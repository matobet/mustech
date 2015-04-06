package cz.muni.fi.pv243.mustech;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {
    @PersistenceContext
    @Produces
    private EntityManager entityManager;
}
