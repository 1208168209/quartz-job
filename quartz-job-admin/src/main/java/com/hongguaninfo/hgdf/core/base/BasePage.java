package com.hongguaninfo.hgdf.core.base;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.page.PageRequest;

/**
 * 
 * @ClassName: BasePage
 * @Description: 基础分页类
 * @author henry
 * @date 2014-2-19 上午11:26:12
 * 
 * @param <E>
 */
public class BasePage<E> extends PageRequest<E> implements java.io.Serializable {
    protected static final Log LOG = LogFactory.getLog(BasePage.class);
    private static final long serialVersionUID = -360860474471966681L;
    public static final int DEFAULT_PAGE_SIZE = 20;

    static {
        LOG.debug("BaseQuery.DEFAULT_PAGE_SIZE=" + DEFAULT_PAGE_SIZE);
    }

    private int posStart = 0;
    private int count = DEFAULT_PAGE_SIZE;
    private int linkResMenuId = 0;

    // for hgdf
    private int page = 1;
    private int rows = DEFAULT_PAGE_SIZE;

    public BasePage() {
        setPageSize(DEFAULT_PAGE_SIZE);
    }

    /**
     * 整理page
     * 
     * @param entity
     */
    public void tidyPage(E entity) {
        this.setPageNumber(this.getPosStart() / this.getCount() + 1);
        this.setPageSize(this.getCount());
        this.setFilters(entity);
    }

    public int getPosStart() {
        return posStart;
    }

    /**
     * 当前开始位置
     * 
     * @param posStart
     */
    public void setPosStart(int posStart) {
        this.posStart = posStart;
    }

    public int getCount() {
        return count;
    }

    /**
     * 每页数量
     * 
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    public int getLinkResMenuId() {
        return linkResMenuId;
    }

    public void setLinkResMenuId(int linkResMenuId) {
        this.linkResMenuId = linkResMenuId;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
        this.setCount(rows);
        this.setPosStart((this.page - 1) * rows);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        this.setPosStart((page - 1) * this.getRows());
    }

}
