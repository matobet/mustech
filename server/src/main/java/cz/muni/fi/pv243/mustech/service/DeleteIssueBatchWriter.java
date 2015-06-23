package cz.muni.fi.pv243.mustech.service;

/**
 * Created by Tomas on 17. 6. 2015.
 */

import cz.muni.fi.pv243.mustech.model.Issue;
import lombok.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final Logger log = LoggerFactory.getLogger(DeleteIssueBatchWriter.class);

    @Inject
    private IssueService issueService;

    @Override
    public void writeItems(List<Object> list) {
        try {
            for (Object o : list) {
                if (o instanceof Issue) {
                    issueService.delete(((Issue) o).getId());
                }
            }
        } catch (Exception e)
        {
            log.debug("Exception occured during batch job.", e);
        }
    }
}
