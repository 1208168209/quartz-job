package com.hongguaninfo.hgdf.core.utils.page;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @ClassName: PageRequest
 * @Description: 分页封装request
 * @author henry
 * @date 2014-2-19 上午11:00:18
 * 
 * @param <T>
 */
public class PageRequest<T> implements Serializable {
    public static final int DEFAULT_PAGE_SIZE = 10;

    @Deprecated
    private T filters;
    private int pageNumber;
    private int pageSize = 10;
    private String sortColumns;

    public PageRequest() {
    }

    @Deprecated
    public PageRequest(T filters) {
        setFilters(filters);
    }

    public PageRequest(int pageNumber, int pageSize) {
        this(pageNumber, pageSize, (T) null);
    }

    @Deprecated
    public PageRequest(int pageNumber, int pageSize, T filters) {
        this(pageNumber, pageSize, filters, null);
    }

    public PageRequest(int pageNumber, int pageSize, String sortColumns) {
        this(pageNumber, pageSize, null, sortColumns);
    }

    @Deprecated
    public PageRequest(int pageNumber, int pageSize, T filters, String sortColumns) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        setFilters(filters);
        setSortColumns(sortColumns);
    }

    @Deprecated
    public T getFilters() {
        return this.filters;
    }

    @Deprecated
    public void setFilters(T filters) {
        this.filters = filters;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortColumns() {
        return this.sortColumns;
    }

    public void setSortColumns(String sortColumns) {
        checkSortColumnsSqlInjection(sortColumns);
        if ((sortColumns != null) && (sortColumns.length() > 200)) {
            throw new IllegalArgumentException("sortColumns.length() <= 200 must be true");
        }
        this.sortColumns = sortColumns;
    }

    public List<SortInfo> getSortInfos() {
        return Collections.unmodifiableList(SortInfo.parseSortColumns(this.sortColumns));
    }

    private void checkSortColumnsSqlInjection(String sortColumns) {
        if (sortColumns == null){
            return;
        }
        if ((sortColumns.indexOf("'") >= 0) || (sortColumns.indexOf("\\") >= 0)){
            throw new IllegalArgumentException("sortColumns:" + sortColumns + " has SQL Injection risk");
        }
    }
}