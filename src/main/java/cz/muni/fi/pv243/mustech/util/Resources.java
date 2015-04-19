package cz.muni.fi.pv243.mustech.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {

    @Produces
    @PersistenceContext(unitName = "mustech")
    private EntityManager entityManager;
}
