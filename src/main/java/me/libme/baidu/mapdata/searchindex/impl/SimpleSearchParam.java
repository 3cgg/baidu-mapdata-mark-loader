package me.libme.baidu.mapdata.searchindex.impl;

import me.libme.baidu.mapdata.searchindex.keyword.SearchParam;

/**
 * Created by J on 2018/2/28.
 */
public class SimpleSearchParam implements SearchParam {

    private String keyword;

    private String region;

    public SimpleSearchParam() {
    }

    public SimpleSearchParam(String keyword) {
        this.keyword = keyword;
    }

    public SimpleSearchParam(String keyword, String region) {
        this.keyword = keyword;
        this.region = region;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String keyword() {
        return getKeyword();
    }


}
