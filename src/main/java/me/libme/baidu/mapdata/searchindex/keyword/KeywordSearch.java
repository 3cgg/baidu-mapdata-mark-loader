package me.libme.baidu.mapdata.searchindex.keyword;

import me.libme.baidu.mapdata.searchindex.point.Point;

import java.util.List;

/**
 * Created by J on 2018/2/27.
 */
public interface KeywordSearch {


    List<Point> search(SearchParam searchParam);

}
