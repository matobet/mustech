package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.AnswerRepository;
import cz.muni.fi.pv243.mustech.model.Answer;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * @author Tomas
 */
@Named
@Transactional
public class AnswerService extends AbstractGenericService<Answer, AnswerRepository> {

    @Inject
    private AnswerRepository answerRepository;

    public AnswerService() {
        super(Answer.class);
    }

    @Override
    protected AnswerRepository getRepository() {
        return answerRepository;
    }
}
