package me.libme.baidu.mapdata.searchindex.keyword;

import me.libme.kernel._c.cache.JCacheService;
import me.libme.kernel._c.cache.JMapCacheService;
import me.libme.xstream.EntryTupe;
import me.libme.xstream.Filter;
import me.libme.xstream.Tupe;

import java.util.Iterator;

/**
 * Created by J on 2018/2/27.
 */
public class KeywordFilter implements Filter {

    private JCacheService cacheService=new JMapCacheService();

    @Override
    public boolean test(Tupe tupe) {

        Iterator iterator= tupe.iterator();

        SearchParam searchParam= (SearchParam) ((EntryTupe.Entry)iterator.next()).getValue();
        boolean exists;
        if(!(exists=cacheService.contains(searchParam))){
            cacheService.put(searchParam.identity(),searchParam);
        }
        return !exists;
    }



}
