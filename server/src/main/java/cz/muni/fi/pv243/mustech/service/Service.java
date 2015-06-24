package cz.muni.fi.pv243.mustech.service;

import javax.enterprise.inject.Stereotype;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Named
@Transactional
public @interface Service {
}
