package me.libme.baidu.mapdata.searchindex.point;

import me.libme.kernel._c.util.MD5;
import me.libme.module.es5x6.ESModel;

import java.util.Collections;
import java.util.Map;

/**
 * Created by J on 2018/2/27.
 */
public interface Point {

    /**
     * 经度
     * @return
     */
    String longitude();

    /**
     * 纬度
     * @return
     */
    String latitude();

    /**
     * 名称
     * @return
     */
    String name();


    /**
     * 地址
     * @return
     */
    String addr();


    /**
     * 备注数据
     * @return
     */
    default Map<String,Object> data(){return Collections.EMPTY_MAP;}


    default ESModel esModel(){throw new UnsupportedOperationException();}

    default String logicUniqueId(){
        return MD5.md5(name()+"|"+latitude()+"|"+longitude());
    }


}
