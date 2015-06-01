package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.Option;

import javax.enterprise.context.RequestScoped;

/**
 * Created by Tomas on 1. 6. 2015.
 */
public interface OptionService {
    Option saveOrUpdate(Option option);
    Option findById(Long id);
    void delete(Long id);
}
