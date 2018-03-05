package me.libme.baidu.mapdata.searchindex.impl;

import me.libme.kernel._c._i.JParser;

/**
 * Created by J on 2018/3/5.
 */
public interface BaiduUrlParser<T extends BaiduConf> extends JParser{


    String parse(T baiduConf);


}
