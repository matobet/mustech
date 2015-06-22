package cz.muni.fi.pv243.mustech.service;

import cz.muni.fi.pv243.mustech.model.Issue;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tomas on 17. 6. 2015.
 */

/**
 * Deletes all expired issues after 1 month.
 */
@Named
public class ExpiredIssueBatchProcessor implements ItemProcessor {
    @Override
    public Issue processItem(Object o) throws Exception {

        if(o instanceof Issue)
        {
            Issue issue = (Issue)o;

            Calendar afterExpiration = Calendar.getInstance();
            afterExpiration.add(Calendar.MONTH, -1);

            if(issue.getExpiresAt().before(afterExpiration.getTime()))
            {
                return issue;
            }
        }

        return null;
    }
}
