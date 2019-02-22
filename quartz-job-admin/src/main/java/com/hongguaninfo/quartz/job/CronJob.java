package com.hongguaninfo.quartz.job;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by chenqinglong on 2019/2/1 0001.
 */
public class CronJob implements Job {
    private Log log = LogFactory.getLog(CronJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("=========================定时任务每1分钟执行一次===============================");
        log.info("jobName=====:"+jobExecutionContext.getJobDetail().getKey().getName());
        log.info("jobGroup=====:"+jobExecutionContext.getJobDetail().getKey().getGroup());
        log.info("taskData=====:"+jobExecutionContext.getJobDetail().getJobDataMap().get("taskData"));
    }
}
