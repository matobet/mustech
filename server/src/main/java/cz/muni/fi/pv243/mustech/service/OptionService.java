package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.OptionRepository;
import cz.muni.fi.pv243.mustech.model.Option;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Option service implementing and initializing generic service
 * @author Tomas
 */
@Named
@Transactional
public class OptionService extends AbstractGenericService<Option, OptionRepository> {
}
