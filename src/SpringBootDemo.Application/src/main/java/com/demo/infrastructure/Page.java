package com.demo.infrastructure;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T extends Serializable> {

    private int pageNo = 1;// 页码，默认是第一页
    private int pageSize = 10;// 每页显示的记录数，默认是15
    private int totalRecord;// 总记录数
    private int totalPage;// 总页数
    private List<T> results;// 对应的当前页记录
    private Map<String, Object> params = new HashMap<String, Object>();// 其他的参数我们把它分装成一个Map对象

    public Page(){}

    public Page(int pageNo, int pageSize)
    {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }
    /**
     * @param pageNo
     *            the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }
    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    /**
     * @return the totalRecord
     */
    public int getTotalRecord() {
        return totalRecord;
    }
    /**
     * @param totalRecord
     *            the totalRecord to set
     */
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
    /**
     * @return the totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }
    /**
     * @param totalPage
     *            the totalPage to set
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    /**
     * @return the results
     */
    public List<T> getResults() {
        return results;
    }
    /**
     * @param results
     *            the results to set
     */
    public void setResults(List<T> results) {
        this.results = results;
    }
    /**
     * @return the params
     */
    public Map<String, Object> getParams() {
        return params;
    }
    /**
     * @param params
     *            the params to set
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}

