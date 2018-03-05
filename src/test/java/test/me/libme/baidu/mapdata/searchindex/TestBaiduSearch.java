package test.me.libme.baidu.mapdata.searchindex;

import me.libme.baidu.mapdata.searchindex.impl.BaiduConf;
import me.libme.baidu.mapdata.searchindex.impl.SimpleSearchParam;
import me.libme.baidu.mapdata.searchindex.impl.place.KeywordSearchParser;
import me.libme.baidu.mapdata.searchindex.impl.place.SimpleBaiduSearch;

/**
 * Created by J on 2018/3/5.
 */
public class TestBaiduSearch {


    public static void main(String[] args) {

        BaiduConf baiduConf=new BaiduConf();
        baiduConf.setPlaceUrl("http://api.map.baidu.com/place/v2/search?");
        baiduConf.setAk("kGVeZwfybkB9ffG9hbKx4hwzwZBsN4YH");

        KeywordSearchParser keywordSearchParser=new KeywordSearchParser();

        SimpleBaiduSearch simpleBaiduSearch=new SimpleBaiduSearch(keywordSearchParser,baiduConf);

        SimpleSearchParam simpleSearchParam=new SimpleSearchParam("金鸡湖");
        simpleSearchParam.setRegion("苏州");

        simpleBaiduSearch.search(simpleSearchParam)
                .stream().forEach(point -> System.out.println(point));

    }



}
