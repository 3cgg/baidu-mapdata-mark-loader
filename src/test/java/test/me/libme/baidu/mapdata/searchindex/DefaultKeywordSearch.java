package test.me.libme.baidu.mapdata.searchindex;

import me.libme.baidu.mapdata.searchindex.impl.ReferencePoint;
import me.libme.baidu.mapdata.searchindex.keyword.KeywordSearch;
import me.libme.baidu.mapdata.searchindex.keyword.SearchParam;
import me.libme.baidu.mapdata.searchindex.point.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J on 2018/2/28.
 */
public class DefaultKeywordSearch implements KeywordSearch {


    @Override
    public List<Point> search(SearchParam searchParam) {
        List list=new ArrayList();
        for(int i=0;i<10;i++){
            ReferencePoint simplePoint=new ReferencePoint();
            simplePoint.setName(searchParam.keyword()+" Name-"+i);
            simplePoint.setLongitude("2."+i);
            simplePoint.setLatitude("1."+i);
            simplePoint.setAddr(searchParam.keyword()+" addr-"+i);
            simplePoint.setId(simplePoint.logicUniqueId());
            simplePoint.setCppEventSource("Baidu");
            list.add(simplePoint);
        }
        return list;
    }


}
