package com.hongguaninfo.hgdf.core.utils.page;

import java.io.Serializable;

/**
 * 
 * @ClassName: PageQuery
 * @Description: 分页查询
 * @author henry
 * @date 2014-2-19 上午10:59:45
 * 
 */
public class PageQuery implements Serializable {
    private static final long serialVersionUID = -8000900575354501298L;
    public static final int DEFAULT_PAGE_SIZE = 10;
    private int page;
    private int pageSize = 10;

    public PageQuery() {
    }

    public PageQuery(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageQuery(PageQuery query) {
        this.page = query.page;
        this.pageSize = query.pageSize;
    }

    public PageQuery(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString() {
        return "page:" + this.page + ",pageSize:" + this.pageSize;
    }
}