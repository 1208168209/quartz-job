package com.hongguaninfo.quartz.controller;

import com.hongguaninfo.hgdf.core.base.BaseController;
import com.hongguaninfo.hgdf.core.base.BasePage;
import com.hongguaninfo.hgdf.core.templete.OperateTemplete;
import com.hongguaninfo.hgdf.core.utils.StringUtil;
import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.quartz.entity.JobAndTrigger;
import com.hongguaninfo.quartz.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenqinglong on 2019/2/1 0001.
 */
@Controller
@RequestMapping("/quartzJob")
public class JobController extends BaseController{
    @Autowired
    private JobService jobService;


    @RequestMapping(value="/job")
    public String job(){
        return "job";
    }

    @RequestMapping(value="/jobDetail/{jobName}/{jobGroup}/{mode}")
    public String jobDetail(@PathVariable String jobName, @PathVariable String jobGroup,
                            @PathVariable String mode,
                            HttpServletRequest request, HttpServletResponse response,
                            Model model){
        JobAndTrigger jobAndTrigger = new JobAndTrigger();
        jobAndTrigger.setJobName(jobName);
        jobAndTrigger.setJobGroup(jobGroup);
        jobAndTrigger = jobService.getJobAndTrigger(jobAndTrigger);
        if (!StringUtil.isEmpty(mode) && "add".equals(mode)){
            //设置运行模式默认为BEAN
            jobAndTrigger.setGlueType("BEAN");
            //新增任务时，设置默认的java源码，当glueType被选为"GLUE(Java)"时使用
            jobAndTrigger.setGlueJava(
                    "package com.hongguaninfo.quartz.job;\n" +
                    "\n" +
                    "import com.hongguaninfo.hgdf.core.utils.logging.Log;\n" +
                    "import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;\n" +
                    "import org.quartz.Job;\n" +
                    "import org.quartz.JobExecutionContext;\n" +
                    "import org.quartz.JobExecutionException;\n" +
                    "\n" +
                    "/**\n" +
                    " * Created by chenqinglong on 2019/2/22 0001.\n" +
                    " */\n" +
                    "public class GlueJavaJob implements Job {\n" +
                    "    private Log log = LogFactory.getLog(GlueJavaJob.class);\n" +
                    "    @Override\n" +
                    "    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {\n" +
                    "        log.info(\"========================GlueJavaJob任务===============================\");\n" +
                    "        log.info(\"jobName=====:\"+jobExecutionContext.getJobDetail().getKey().getName());\n" +
                    "        log.info(\"jobGroup=====:\"+jobExecutionContext.getJobDetail().getKey().getGroup());\n" +
                    "        log.info(\"taskData=====:\"+jobExecutionContext.getJobDetail().getJobDataMap().get(\"glueType\"));\n" +
                    "    }\n" +
                    "}\n");
        }
        model.addAttribute("job", jobAndTrigger);
        model.addAttribute("mode", mode);
        return "job_detail";
    }

    @RequestMapping("/jobList")
    @ResponseBody
    public Map getJobList(final JobAndTrigger vo, final BasePage basePage,
                          HttpServletResponse response, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        try {
            jobService.getJobList(basePage, vo, map);
        } catch (Exception e) {
            LOG.error("任务列表查询错误：", e);
            map.put("success",false);
            map.put("data",e.getStackTrace());
        }
        return map;
    }

    /**
     * 创建cron任务
     * @param jobAndTrigger
     * @return
     */
    @RequestMapping(value = "/addCronJob",method = RequestMethod.POST)
    @ResponseBody
    public Map addCronJob(final JobAndTrigger jobAndTrigger){
        OperateTemplete operateTemplete = new OperateTemplete() {
            @Override
            protected void doSomething() throws BaseException {
                if (StringUtil.isEmpty(jobAndTrigger.getGlueType())){
                    throw new BaseException("请选择运行模式");
                }
                if ("BEAN".equals(jobAndTrigger.getGlueType())){
                    jobService.addCronJob(jobAndTrigger);
                }else if ("GLUE_JAVA".equals(jobAndTrigger.getGlueType())){
                    jobService.addCronJobOfJobSource(jobAndTrigger);
                }
            }
        };
        return operateTemplete.operate();
    }


    /**
     * 创建异步任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/async",method = RequestMethod.POST)
    public String startAsyncJob(@RequestParam("jobName") String jobName, @RequestParam("jobGroup") String jobGroup,
                                @RequestParam("jobGroup") String jobClass){
        jobService.addAsyncJob(jobName,jobGroup,jobClass);
        return "create async task success";
    }

    /**
     * 暂停任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/pause",method = RequestMethod.POST)
    @ResponseBody
    public Map pauseJob(@RequestParam("jobName") final String jobName, @RequestParam("jobGroup") final String jobGroup){
        OperateTemplete operateTemplete = new OperateTemplete() {
            @Override
            protected void doSomething() throws BaseException {
                jobService.pauseJob(jobName,jobGroup);
            }
        };
        return operateTemplete.operate();
    }

    /**
     * 恢复任务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/resume",method = RequestMethod.POST)
    @ResponseBody
    public Map resumeJob(@RequestParam("jobName") final String jobName, @RequestParam("jobGroup") final String jobGroup){
        OperateTemplete operateTemplete = new OperateTemplete() {
            @Override
            protected void doSomething() throws BaseException {
                jobService.resumeJob(jobName,jobGroup);
            }
        };
        return operateTemplete.operate();
    }

    /**
     * 修改任务
     * @param jobAndTrigger
     * @return
     */
    @RequestMapping(value = "/rescheduleJob",method = RequestMethod.POST)
    @ResponseBody
    public Map rescheduleJob(final JobAndTrigger jobAndTrigger){
        OperateTemplete operateTemplete = new OperateTemplete() {
            @Override
            protected void doSomething() throws BaseException {
                jobService.rescheduleJob(jobAndTrigger);
            }
        };
        return operateTemplete.operate();
    }

    /**
     * 删除务
     * @param jobName
     * @param jobGroup
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map deleteJob(@RequestParam("jobName") final String jobName, @RequestParam("jobGroup") final String jobGroup){
        OperateTemplete operateTemplete = new OperateTemplete() {
            @Override
            protected void doSomething() throws BaseException {
                jobService.deleteJob(jobName,jobGroup);
            }
        };
        return operateTemplete.operate();
    }
}