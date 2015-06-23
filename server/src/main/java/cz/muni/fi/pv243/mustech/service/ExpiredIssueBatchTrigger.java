package cz.muni.fi.pv243.mustech.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.util.Properties;

/**
 * Created by Tomas on 17. 6. 2015.
 */
@Singleton
public class ExpiredIssueBatchTrigger
{
    private final Logger log = LoggerFactory.getLogger(ExpiredIssueBatchTrigger.class);

    @PostConstruct
    @Schedule(dayOfWeek = "Sun",persistent = false)
    public void runBatch()
    {
        try {
            JobOperator jo = BatchRuntime.getJobOperator();
            jo.start("expiredIssueBatchJob", new Properties());
        }catch (Exception e)
        {
            log.debug("Some error occured during batch job.",e);
        }
    }
}
