package cz.muni.fi.pv243.mustech.service;

/**
 * Created by Tomas on 17. 6. 2015.
 */

import cz.muni.fi.pv243.mustech.model.Issue;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Delete expired issues.
 */
@Named
public class DeleteIssueBatchWriter extends AbstractItemWriter
{
    @Inject
    private IssueService issueService;

    @Override
    public void writeItems(List<Object> list) throws Exception {
        for(Object o : list)
        {
            if(o instanceof Issue)
            {
                issueService.delete(((Issue)o).getId());
            }
        }
    }
}
