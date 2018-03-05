package me.libme.baidu.mapdata.searchindex.impl.place;

import me.libme.baidu.mapdata.searchindex.impl.BaiduConf;

/**
 * Created by J on 2018/3/5.
 */
public class SearchConf extends BaiduConf {


    private String searchUrl;

    private String pageSize;

    private String pageNum;

    private String output;

    private String query;

    private String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
