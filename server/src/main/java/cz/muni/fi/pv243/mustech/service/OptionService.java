package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.OptionRepository;
import cz.muni.fi.pv243.mustech.model.Option;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * @author Tomas
 */
@Named
@Transactional
public class OptionService extends AbstractGenericService<Option, OptionRepository> {

    @Inject
    private OptionRepository optionRepository;

    public OptionService() {
        super(Option.class);
    }

    @Override
    protected OptionRepository getRepository() {
        return optionRepository;
    }
}
