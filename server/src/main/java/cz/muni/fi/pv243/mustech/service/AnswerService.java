package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.dal.AnswerRepository;
import cz.muni.fi.pv243.mustech.model.Answer;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 * Answer service implementing and initializing generic service
 * @author Tomas
 */
@Named
@Transactional
public class AnswerService extends AbstractGenericService<Answer, AnswerRepository> {
}
