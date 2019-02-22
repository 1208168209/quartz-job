package com.hongguaninfo.hgdf.core.templete;

import com.hongguaninfo.hgdf.core.utils.exception.BaseException;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * web 层封装抽象类
 * 
 * @author henry
 * 
 */
public abstract class OperateTemplete {

    public static final Log LOG = LogFactory.getLog(OperateTemplete.class);

    protected String str;
    protected Map<String, Object> map = new HashMap<String, Object>();
    protected List<Object> list = new ArrayList<Object>();
    protected List<BindingResult> validResultList = new ArrayList<BindingResult>();

    /**
     * 设置map值
     * 
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        map.put(key, value);
    }

    /**
     * 配合@ResponseBody输出json
     * 
     * @return
     */
    public Map<String, Object> operate() {
        try {
            doSomething();
            map.put("success", true);
        } catch (Exception e) {
            LOG.error("operate fail!", e);
            map.put("success", false);
            map.put("data", getExcptionMessage(e));
        }
        return map;
    }

    /**
     * 配合@ResponseBody输出json 只输出结果list
     * 
     * @return
     */
    public List<Object> operateList() {
        try {
            doSomething();
        } catch (Exception e) {
            LOG.error("operateList fail!", e);
        }
        return list;
    }
    
    /**
     * 配合@ResponseBody输出json 只输出结果map
     * 
     * @return
     */
    public Map<String, Object> operateMap() {
        try {
            doSomething();
        } catch (Exception e) {
            LOG.error("operateList fail!", e);
        }
        return map;
    }
    
    /**
     * view
     * 
     * @return
     */
    public String operateModel() {
        try {
            doSomething();
        } catch (Exception e) {
            LOG.error("operateModel fail!", e);
        }
        return str;
    }

    /**
     * 输出视图
     * 
     * @param viewName
     * @return
     */
    public ModelAndView operateView(String viewName) {
        try {
            doSomething();
        } catch (Exception e) {
            LOG.error("operateView fail!", e);
            str = returnFail(getExcptionMessage(e));
        }
        return new ModelAndView(viewName, map);
    }

    /**
     * 输出excel
     * 
     * @param viewName
     * @return
     */
    public ModelAndView operateExcel(String viewName) {
        return operateView(viewName);
    }

    /**
     * 输出pdf
     * 
     * @param viewName
     * @return
     */
    public ModelAndView operatePdf(String viewName) {
        return operateView(viewName);
    }

    /**
     * 返回成功
     * 
     * @return
     */
    private String returnSuccess() {
        return "ok";
    }

    /**
     * 返回错误信息
     * 
     * @param msg
     * @return
     */
    private String returnFail(String msg) {
        return msg;
    }

    /**
     * action层 统一调用方法
     * 
     * @throws BaseException
     */
    protected abstract void doSomething() throws BaseException;
    
    /**
     * 异常信息（当范围message长度大于100时只显示指定内容）
     * @param e
     * @return
     */
    private String getExcptionMessage(Exception e){
        if(String.valueOf(e.getMessage()).length() > 100){
            return "服务器处理异常！";
        }else{            
            return e.getMessage();
        }
    }
    
    
}
