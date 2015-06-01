package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.OptionRepository;
import cz.muni.fi.pv243.mustech.model.Option;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by Tomas on 1. 6. 2015.
 */
@Named
@Transactional
public class OptionServiceImpl implements OptionService {
    @Inject
    private OptionRepository optionRepository;

    @Override
    public Option saveOrUpdate(@Valid Option option) {
        return optionRepository.save(option);
    }

    @Override
    public Option findById(Long id) {
        return optionRepository.findBy(id);
    }

    @Override
    public void delete(Long id) {
        optionRepository.remove(optionRepository.findBy(id));
    }
}
