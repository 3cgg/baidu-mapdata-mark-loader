package me.libme.baidu.mapdata.searchindex;

import me.libme.kernel._c.pubsub.Produce;
import me.libme.kernel._c.pubsub.Publisher;
import me.libme.xstream.Compositer;
import me.libme.xstream.ConsumerMeta;
import me.libme.xstream.Tupe;

import java.util.Iterator;
import java.util.List;

/**
 * Created by J on 2018/2/27.
 */
public class KeywordConsumer extends Compositer {

    private final KeywordSearch keywordSearch;

    private final Publisher publisher;

    private final Produce produce;
    public KeywordConsumer(ConsumerMeta consumerMeta,KeywordSearch keywordSearch,Publisher publisher) {
        super(consumerMeta);
        this.keywordSearch=keywordSearch;
        this.publisher=publisher;
        this.produce=publisher.produce();
    }

    @Override
    protected void doConsume(Tupe tupe) throws Exception {

        Iterator iterator= tupe.iterator();
        SearchParam searchParam= (SearchParam) iterator.next();
        List<Point> points=keywordSearch.search(searchParam);
        points.forEach(point -> produce.produce(point));

    }

}
