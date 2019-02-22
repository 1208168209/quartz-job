package com.hongguaninfo.hgdf.core.utils.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: SortInfo
 * @Description: 分页排序信息
 * @author henry
 * @date 2014-2-19 上午11:01:23
 * 
 */
public class SortInfo implements Serializable {
    private static final long serialVersionUID = 6959974032209696722L;
    private String columnName;
    private String sortOrder;

    public SortInfo() {
    }

    public SortInfo(String columnName, String sortOrder) {
        this.columnName = columnName;
        this.sortOrder = sortOrder;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public static List<SortInfo> parseSortColumns(String sortColumns) {
        if (sortColumns == null) {
            return new ArrayList(0);
        }

        List results = new ArrayList();
        String[] sortSegments = sortColumns.trim().split(",");
        for (int i = 0; i < sortSegments.length; i++) {
            String sortSegment = sortSegments[i];
            String[] array = sortSegment.split("\\s+");

            SortInfo sortInfo = new SortInfo();
            sortInfo.setColumnName(array[0]);
            sortInfo.setSortOrder(array.length == 2 ? array[1] : null);
            results.add(sortInfo);
        }
        return results;
    }

    public String toString() {
        return this.columnName
                + (this.sortOrder == null ? "" : new StringBuilder().append(" ").append(this.sortOrder).toString());
    }
}
