package com.hongguaninfo.hgdf.core.base;

import java.io.Serializable;

/**
 * 
 * @ClassName: BaseEntity
 * @Description: 基础实体
 * @author henry
 * @date 2014-2-19 上午11:25:57
 * 
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -7200095849148417467L;

    protected static final String DATE_FORMAT = "yyyy-MM-dd";

    protected static final String TIME_FORMAT = "HH:mm:ss";

    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

    // 排序
    private String orderStr;

    // 列头下拉框
    private String distinctName;
    private String distinctValue;

    private String operator;
    
    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public String getDistinctName() {
        return distinctName;
    }

    public void setDistinctName(String distinctName) {
        this.distinctName = distinctName;
    }

    public String getDistinctValue() {
        return distinctValue;
    }

    public void setDistinctValue(String distinctValue) {
        this.distinctValue = distinctValue;
    }

}
