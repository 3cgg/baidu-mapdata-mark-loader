package me.libme.baidu.mapdata.searchindex.impl.place;

import me.libme.baidu.mapdata.searchindex.impl.BaiduConf;
import me.libme.baidu.mapdata.searchindex.impl.ReferencePoint;
import me.libme.baidu.mapdata.searchindex.impl.SimpleSearchParam;
import me.libme.baidu.mapdata.searchindex.impl.place.response.BaiduPlaceResponse;
import me.libme.baidu.mapdata.searchindex.keyword.KeywordSearch;
import me.libme.baidu.mapdata.searchindex.keyword.SearchParam;
import me.libme.baidu.mapdata.searchindex.point.Point;
import me.libme.kernel._c.http.JHttp;
import me.libme.kernel._c.json.JJSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by J on 2018/3/5.
 */
public class SimpleBaiduSearch implements KeywordSearch {

    private final KeywordSearchParser keywordSearchParser;

    private final BaiduConf baiduConf;

    public SimpleBaiduSearch(KeywordSearchParser keywordSearchParser, BaiduConf baiduConf) {
        this.keywordSearchParser = keywordSearchParser;
        this.baiduConf = baiduConf;
    }

    private void collect(SearchConf searchConf, List<Point> points){
        String url=keywordSearchParser.parse(searchConf);
        try {
            String response = (String) JHttp._get().setTimeout(5000).setSocketTimeout(100000)
                    .execute(url);
            BaiduPlaceResponse baiduPlaceResponse =  JJSON.get().parse(response, BaiduPlaceResponse.class);

            baiduPlaceResponse.getResults()
                    .forEach(result->{
                        ReferencePoint baiduPoint=new ReferencePoint();
                        baiduPoint.setName(result.getName());
                        baiduPoint.setLatitude(String.valueOf(result.getLocation().getLat()));
                        baiduPoint.setLongitude(String.valueOf(result.getLocation().getLng()));
                        baiduPoint.setCppEventSource("BAIDU");
                        baiduPoint.setAddr(result.getAddress());
                        baiduPoint.setId(baiduPoint.logicUniqueId());
                        points.add(baiduPoint);
                    });

            int total=baiduPlaceResponse.getTotal();
            int maxNum=total/Integer.valueOf(searchConf.getPageSize());
            int nextNum=Integer.valueOf(searchConf.getPageNum())+1;
            if(nextNum<=maxNum){
                searchConf.setPageNum(""+nextNum);
                collect(searchConf,points);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Point> search(SearchParam searchParam) {
        SimpleSearchParam simpleSearchParam=(SimpleSearchParam)searchParam;
        SearchConf target=new SearchConf().from(baiduConf);
        target.setQuery(simpleSearchParam.keyword());
        target.setRegion(simpleSearchParam.getRegion());
        List<Point> points=new ArrayList<>();
        collect(target,points);
        return points;


    }


}
