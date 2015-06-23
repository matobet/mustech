package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.Issue;
import lombok.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Iterator;

/**
 * Created by Tomas on 17. 6. 2015.
 */

/**
 * Issue reader for batch service.
 */
@Named
public class IssueBatchReader extends AbstractItemReader
{
    private final Logger log = LoggerFactory.getLogger(IssueBatchReader.class);

    @Inject
    private IssueService issueService;

    private static Iterator<Issue> issueIterator;

    private static final Object lock = new Object();

    @Override
    public Issue readItem() {
        try {
            synchronized (lock){
            if (issueIterator == null) {
               issueIterator = issueService.findAll().iterator();
            }

            return issueIterator.hasNext() ? issueIterator.next() : null;}
        }catch(Exception e)
        {
            log.debug("Error occured during item reading.",e);
            return null;
        }
    }
}
