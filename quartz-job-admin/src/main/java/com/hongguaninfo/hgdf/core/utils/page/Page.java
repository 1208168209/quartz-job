package com.hongguaninfo.hgdf.core.utils.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @ClassName: Page
 * @Description: 分页实体
 * @author henry
 * @date 2014-2-19 上午10:59:32
 * 
 * @param <T>
 */
public class Page<T> implements Serializable, Iterable<T> {
    protected List<T> result;
    protected int pageSize;
    protected int pageNumber;
    protected int totalCount = 0;

    public Page(PageRequest p, int totalCount) {
        this(p.getPageNumber(), p.getPageSize(), totalCount);
    }

    public Page(PageQuery p, int totalCount) {
        this(p.getPage(), p.getPageSize(), totalCount);
    }

    public Page(int pageNumber, int pageSize, int totalCount) {
        this(pageNumber, pageSize, totalCount, new ArrayList(0));
    }

    public Page(int pageNumber, int pageSize, int totalCount, List<T> result) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("[pageSize] must great than zero");
        }
        this.pageSize = pageSize;
        this.pageNumber = PageUtils.computePageNumber(pageNumber, pageSize, totalCount);
        this.totalCount = totalCount;
        setResult(result);
    }

    public void setResult(List<T> elements) {
        if (elements == null) {
            throw new IllegalArgumentException("'result' must be not null");
        }
        this.result = elements;
    }

    public List<T> getResult() {
        return this.result;
    }

    public boolean isFirstPage() {
        return getThisPageNumber() == 1;
    }

    public boolean isLastPage() {
        return getThisPageNumber() >= getLastPageNumber();
    }

    public boolean isHasNextPage() {
        return getLastPageNumber() > getThisPageNumber();
    }

    public boolean isHasPreviousPage() {
        return getThisPageNumber() > 1;
    }

    public int getLastPageNumber() {
        return PageUtils.computeLastPageNumber(this.totalCount, this.pageSize);
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getThisPageFirstElementNumber() {
        return (getThisPageNumber() - 1) * getPageSize() + 1;
    }

    public int getThisPageLastElementNumber() {
        int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
        return getTotalCount() < fullPage ? getTotalCount() : fullPage;
    }

    public int getNextPageNumber() {
        return getThisPageNumber() + 1;
    }

    public int getPreviousPageNumber() {
        return getThisPageNumber() - 1;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getThisPageNumber() {
        return this.pageNumber;
    }

    public Integer[] getLinkPageNumbers() {
        return linkPageNumbers(7);
    }

    public Integer[] linkPageNumbers(int count) {
        return PageUtils.generateLinkPageNumbers(getThisPageNumber(), getLastPageNumber(), count);
    }

    public int getFirstResult() {
        return PageUtils.getFirstResult(this.pageNumber, this.pageSize);
    }

    public Iterator<T> iterator() {
        return (Iterator<T>) (this.result == null ? Collections.emptyList().iterator() : this.result.iterator());
    }
}