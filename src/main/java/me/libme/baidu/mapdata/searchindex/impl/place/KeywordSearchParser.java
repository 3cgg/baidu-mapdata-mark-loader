package me.libme.baidu.mapdata.searchindex.impl.place;

import me.libme.baidu.mapdata.searchindex.impl.BaiduUrlParser;
import me.libme.kernel._c.util.JStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by J on 2018/3/5.
 */
public class KeywordSearchParser implements BaiduUrlParser<SearchConf> {


    @Override
    public String parse(SearchConf baiduConf) {

        Objects.requireNonNull(baiduConf.getSearchUrl());
        Objects.requireNonNull(baiduConf.getAk());
        List<String> items=new ArrayList<>();

        if(JStringUtils.isNotNullOrEmpty(baiduConf.getRegion())){
            items.add("region="+baiduConf.getRegion());
        }

        if(JStringUtils.isNotNullOrEmpty(baiduConf.getQuery())){
            items.add("query="+baiduConf.getQuery());
        }

        if(JStringUtils.isNotNullOrEmpty(baiduConf.getPageSize())){
            items.add("page_size="+baiduConf.getPageSize());
        }

        if(JStringUtils.isNotNullOrEmpty(baiduConf.getPageNum())){
            items.add("page_num="+baiduConf.getPageNum());
        }

        if(JStringUtils.isNotNullOrEmpty(baiduConf.getOutput())){
            items.add("output="+baiduConf.getOutput());
        }

        if(JStringUtils.isNotNullOrEmpty(baiduConf.getAk())){
            items.add("ak="+baiduConf.getAk());
        }

        String searchUrl=baiduConf.getSearchUrl().join("&",items);

        return searchUrl;
    }



}
