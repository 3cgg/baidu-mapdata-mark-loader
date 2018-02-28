package me.libme.baidu.mapdata.searchindex.point;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by J on 2018/2/28.
 */
public class MockESPoint implements IPointPersist {

    private static Logger LOGGER= LoggerFactory.getLogger(MockESPoint.class);


    @Override
    public void persist(Point point) throws Exception {

        LOGGER.info("ready to persist point:"+point);

    }


}
