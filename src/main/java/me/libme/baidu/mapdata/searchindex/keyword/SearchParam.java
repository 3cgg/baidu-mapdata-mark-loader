package me.libme.baidu.mapdata.searchindex.keyword;

/**
 * Created by J on 2018/2/27.
 */
public interface SearchParam {

    String keyword();


    /**
     * identify the search object, the value will be used as key in hash data structures
     * @return
     */
    default String identity(){
        return keyword();
    }

}
