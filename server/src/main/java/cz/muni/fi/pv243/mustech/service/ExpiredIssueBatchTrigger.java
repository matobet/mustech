package cz.muni.fi.pv243.mustech.service;

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
    @PostConstruct
    @Schedule(dayOfMonth = "Sun",persistent = false)
    public void runBatch()
    {
        JobOperator jo = BatchRuntime.getJobOperator();
        jo.start("expiredIssueBatchJob", new Properties());
    }
}
