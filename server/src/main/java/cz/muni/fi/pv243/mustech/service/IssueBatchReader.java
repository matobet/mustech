package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.Issue;

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
    @Inject
    private IssueService issueService;

    private Iterator<Issue> issueIterator;

    @Override
    public Issue readItem() throws Exception{
            if(issueIterator == null)
            {
                issueIterator = issueService.findAll().iterator();
            }

            return issueIterator.hasNext() ? issueIterator.next() : null;
    }
}
