package me.libme.baidu.mapdata.searchindex;

import me.libme.xstream.Compositer;
import me.libme.xstream.ConsumerMeta;
import me.libme.xstream.Tupe;

import java.util.Iterator;

/**
 * Created by J on 2018/2/27.
 */
public class PointPersitConsumer extends Compositer {

    private final IPointPersist pointPersist;

    public PointPersitConsumer(ConsumerMeta consumerMeta, IPointPersist pointPersist) {
        super(consumerMeta);
        this.pointPersist=pointPersist;
    }

    @Override
    protected void doConsume(Tupe tupe) throws Exception {
        Iterator iterator= tupe.iterator();
        Point point= (Point) iterator.next();
        pointPersist.persist(point);
    }

}
