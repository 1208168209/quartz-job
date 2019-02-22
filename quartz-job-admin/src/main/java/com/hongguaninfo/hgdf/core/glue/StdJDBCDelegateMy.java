package com.hongguaninfo.hgdf.core.glue;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.quartz.job.CronJob;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.jdbcjobstore.StdJDBCDelegate;
import org.quartz.spi.ClassLoadHelper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * 自定义的jdbc类，重写selectJobDetail，改变加载job class的方式，
 * 继承了org.quartz.impl.jdbcjobstore.StdJDBCDelegate
 * Created by chenqinglong on 2019/2/22 0022.
 */
public class StdJDBCDelegateMy extends StdJDBCDelegate {
    private Log log = LogFactory.getLog(StdJDBCDelegateMy.class);
    @Override
    public JobDetail selectJobDetail(Connection conn, JobKey jobKey, ClassLoadHelper loadHelper) throws ClassNotFoundException, IOException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        JobDetailImpl map1;
        try {
            ps = conn.prepareStatement(this.rtp("SELECT * FROM {0}JOB_DETAILS WHERE SCHED_NAME = {1} AND JOB_NAME = ? AND JOB_GROUP = ?"));
            ps.setString(1, jobKey.getName());
            ps.setString(2, jobKey.getGroup());
            rs = ps.executeQuery();
            JobDetailImpl job = null;
            if(rs.next()) {
                job = new JobDetailImpl();
                job.setName(rs.getString("JOB_NAME"));
                job.setGroup(rs.getString("JOB_GROUP"));
                job.setDescription(rs.getString("DESCRIPTION"));
                job.setDurability(this.getBoolean(rs, "IS_DURABLE"));
                job.setRequestsRecovery(this.getBoolean(rs, "REQUESTS_RECOVERY"));
                Map map = null;
                if(this.canUseProperties()) {
                    map = this.getMapFromProperties(rs);
                } else {
                    map = (Map)this.getObjectFromBlob(rs, "JOB_DATA");
                }

                if(null != map) {
                    job.setJobDataMap(new JobDataMap(map));
                }

                if (null != job && null != job.getJobDataMap() && null != job.getJobDataMap().get("glueType") && "GLUE_JAVA".equals(String.valueOf(job.getJobDataMap().get("glueType")))){
                    //根据job参数判断jobClass是项目内置的类还是页面动态编辑的类：
                    // 如果是页面编辑的类，那么修改类的加载方式
                    try {
                        job.setJobClass(GlueFactory.getInstance().loadNewInstance(String.valueOf(job.getJobDataMap().get("glueJava"))).getClass());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    job.setJobClass(loadHelper.loadClass(rs.getString("JOB_CLASS_NAME"), Job.class));
                }
            }

            map1 = job;
        } finally {
            closeResultSet(rs);
            closeStatement(ps);
        }

        return map1;
    }

    private Map<?, ?> getMapFromProperties(ResultSet rs) throws ClassNotFoundException, IOException, SQLException {
        InputStream is = (InputStream)this.getJobDataFromBlob(rs, "JOB_DATA");
        if(is == null) {
            return null;
        } else {
            Properties properties = new Properties();
            if(is != null) {
                try {
                    properties.load(is);
                } finally {
                    is.close();
                }
            }

            Map map = this.convertFromProperty(properties);
            return map;
        }
    }
}
