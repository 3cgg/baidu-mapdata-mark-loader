package me.libme.baidu.mapdata.searchindex.impl;

import me.libme.baidu.mapdata.searchindex.keyword.SearchParam;

/**
 * Created by J on 2018/2/28.
 */
public class SimpleSearchParam implements SearchParam {

    private String keyword;

    public SimpleSearchParam(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String keyword() {
        return getKeyword();
    }


}
