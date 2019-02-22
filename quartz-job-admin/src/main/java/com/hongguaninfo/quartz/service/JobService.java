package com.hongguaninfo.quartz.service;

import com.hongguaninfo.hgdf.core.base.BasePage;
import com.hongguaninfo.hgdf.core.glue.GlueFactory;
import com.hongguaninfo.hgdf.core.utils.StringUtil;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import com.hongguaninfo.quartz.dao.QrtzJobDetailsDao;
import com.hongguaninfo.quartz.entity.JobAndTrigger;
import com.hongguaninfo.quartz.entity.QrtzJobDetails;
import com.hongguaninfo.quartz.job.AsyncJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Created by chenqinglong on 2019/2/1 0001.
 */
@Service
public class JobService {

    private Log log = LogFactory.getLog(JobService.class);
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private QrtzJobDetailsDao qrtzJobDetailsDao;

    @Autowired @Qualifier("Scheduler")
    private Scheduler scheduler;

    /**
     * 添加一个定时任务
     * @param jobAndTrigger
     */
    public void addCronJob(JobAndTrigger jobAndTrigger) throws BaseException{
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobAndTrigger.getJobName(), jobAndTrigger.getJobGroup());
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                System.out.println("job:" + jobAndTrigger.getJobName() + " 已存在");
            } else {
                //构建job信息
                jobDetail = JobBuilder.newJob((Class<Job>)Class.forName(jobAndTrigger.getJobClassName()))
                        .withIdentity(jobAndTrigger.getJobName(), jobAndTrigger.getJobGroup())
                        .withDescription(jobAndTrigger.getDescription())
                        .build();
                //用JopDataMap来传递数据
                jobDetail.getJobDataMap().put("taskData", "hzb-cron-001");


                //表达式调度构建器,首先校验表达式格式
                if (!CronExpression.isValidExpression(jobAndTrigger.getCronExpression())) {
                    throw new BaseException("定时任务规则表达式不正确");
                }
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobAndTrigger.getCronExpression());

                //按新的cronExpression表达式构建一个新的trigger
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobAndTrigger.getJobName() + "_trigger", jobAndTrigger.getJobGroup() + "_trigger")
                        .withSchedule(scheduleBuilder)
                        .withDescription(jobAndTrigger.getDescription())
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (Exception e) {
            log.error("任务创建失败：", e);
            throw new BaseException(e.getMessage());
        }
    }

    /**
     * 添加一个定时任务
     * @param jobAndTrigger
     */
    public void addCronJobOfJobSource(JobAndTrigger jobAndTrigger) throws BaseException{
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobAndTrigger.getJobName(), jobAndTrigger.getJobGroup());
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                System.out.println("job:" + jobAndTrigger.getJobName() + " 已存在");
            } else {
                //构建job信息
                jobDetail = JobBuilder.newJob(GlueFactory.getInstance().loadNewInstance(jobAndTrigger.getGlueJava()).getClass())
                        .withIdentity(jobAndTrigger.getJobName(), jobAndTrigger.getJobGroup())
                        .withDescription(jobAndTrigger.getDescription())
                        .build();
                //用JopDataMap来传递数据
                jobDetail.getJobDataMap().put("taskData", "hzb-cron-001");
                jobDetail.getJobDataMap().put("glueType",jobAndTrigger.getGlueType());
                jobDetail.getJobDataMap().put("glueJava",jobAndTrigger.getGlueJava());

                //表达式调度构建器,首先校验表达式格式
                if (!CronExpression.isValidExpression(jobAndTrigger.getCronExpression())) {
                    throw new BaseException("定时任务规则表达式不正确");
                }
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobAndTrigger.getCronExpression());

                //按新的cronExpression表达式构建一个新的trigger
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobAndTrigger.getJobName() + "_trigger", jobAndTrigger.getJobGroup() + "_trigger")
                        .withSchedule(scheduleBuilder)
                        .withDescription(jobAndTrigger.getDescription())
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (Exception e) {
            log.error("任务创建失败：", e);
            throw new BaseException(e.getMessage());
        }
    }

    /**
     * 添加异步任务
     * @param jobName
     * @param jobGroup
     */
    public void addAsyncJob(String jobName, String jobGroup, String jobClass) {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) {
                System.out.println("job:" + jobName + " 已存在");
            }
            else {
                //构建job信息,在用JobBuilder创建JobDetail的时候，有一个storeDurably()方法，可以在没有触发器指向任务的时候，将任务保存在队列中了。然后就能手动触发了
                jobDetail = JobBuilder.newJob(AsyncJob.class).withIdentity(jobName, jobGroup).storeDurably().build();
                jobDetail.getJobDataMap().put("asyncData","this is a async task");
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName + "_trigger", jobGroup + "_trigger") //定义name/group
                        .startNow()//一旦加入scheduler，立即生效
                        .withSchedule(simpleSchedule())//使用SimpleTrigger
                        .build();
                scheduler.scheduleJob(jobDetail, trigger);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 暂停任务
     * @param jobName
     * @param jobGroup
     */
    public void pauseJob(String jobName, String jobGroup) throws BaseException {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName + "_trigger", jobGroup + "_trigger");

            scheduler.pauseTrigger(triggerKey);
        } catch (SchedulerException e) {
            log.error("暂停失败：", e);
            throw new BaseException("暂停失败：" + e.getMessage());
        }
    }

    /**
     * 恢复任务
     * @param jobName
     * @param jobGroup
     */
    public void resumeJob(String jobName, String jobGroup) throws BaseException {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName + "_trigger", jobGroup + "_trigger");
            scheduler.resumeTrigger(triggerKey);
        } catch (SchedulerException e) {
            log.error("恢复失败：", e);
            throw new BaseException("恢复失败：" + e.getMessage());
        }
    }

    /**
     * 修改任务表达式并重新调度
     * @param jobAndTrigger
     */
    public void rescheduleJob(JobAndTrigger jobAndTrigger ) throws BaseException {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobAndTrigger.getJobName() + "_trigger", jobAndTrigger.getJobGroup() + "_trigger");
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobAndTrigger.getCronExpression());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            String oldTime = trigger.getCronExpression();
            if (oldTime.isEmpty() || !oldTime.equals(jobAndTrigger.getCronExpression())){
                //表达式调度构建器,首先校验表达式格式
                if (!CronExpression.isValidExpression(jobAndTrigger.getCronExpression())) {
                    throw new BaseException("定时任务规则表达式不正确");
                }
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            log.error("修改失败：", e);
            throw new BaseException("修改失败：" + e.getMessage());
        }
    }

    /**
     * 删除job
     * @param jobName
     * @param jobGroup
     */
    public void deleteJob(String jobName, String jobGroup) throws BaseException {
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName,jobGroup);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.error("删除失败：", e);
            throw new BaseException("删除失败：" + e.getMessage());
        }

    }

    public List<JobAndTrigger> getJobList(BasePage<JobAndTrigger> basePage, JobAndTrigger vo,
                                          Map<String, Object> map) throws Exception {
        basePage.setFilters(vo);
        Page<JobAndTrigger> page = qrtzJobDetailsDao.pageQuery("getJobAndTriggerList",basePage);
        List<JobAndTrigger> list = page.getResult();
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
    }

    public List<JobAndTrigger> getJobAndTriggerList(JobAndTrigger jobAndTrigger){
        List<JobAndTrigger> jobAndTriggerList = qrtzJobDetailsDao.getJobAndTriggerList(jobAndTrigger);
        return jobAndTriggerList;
    }

    public JobAndTrigger getJobAndTrigger(JobAndTrigger jobAndTrigger){
        List<JobAndTrigger> jobAndTriggerList = qrtzJobDetailsDao.getJobAndTriggerList(jobAndTrigger);
        if (jobAndTriggerList.size() > 0){
            jobAndTrigger =  jobAndTriggerList.get(0);
            //以下为查询加载模式
            JobKey jobKey = JobKey.jobKey(jobAndTrigger.getJobName(), jobAndTrigger.getJobGroup());
            try {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                if (jobDetail != null && jobDetail.getJobDataMap().get("glueType") != null){
                    jobAndTrigger.setGlueType(String.valueOf(jobDetail.getJobDataMap().get("glueType")));
                }
                if (jobDetail != null && jobDetail.getJobDataMap().get("glueJava") != null){
                    jobAndTrigger.setGlueJava(String.valueOf(jobDetail.getJobDataMap().get("glueJava")));
                }
            } catch (SchedulerException e) {
                log.error("jobDetail查询出错：", e);
                jobAndTrigger = new JobAndTrigger();
            }
        }else {
            jobAndTrigger = new JobAndTrigger();
        }
        return jobAndTrigger;
    }
}
