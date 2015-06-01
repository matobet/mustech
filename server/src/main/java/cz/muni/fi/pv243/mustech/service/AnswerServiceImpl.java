package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.AnswerRepository;
import cz.muni.fi.pv243.mustech.model.Answer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by Tomas on 1. 6. 2015.
 */
@Named
@Transactional
public class AnswerServiceImpl implements AnswerService {
    @Inject
    private AnswerRepository answerRepository;

    @Override
    public Answer saveOrUpdate(@Valid Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer findById(Long id) {
        return answerRepository.findBy(id);
    }

    @Override
    public void delete(Long id) {
        answerRepository.remove(answerRepository.findBy(id));
    }
}
