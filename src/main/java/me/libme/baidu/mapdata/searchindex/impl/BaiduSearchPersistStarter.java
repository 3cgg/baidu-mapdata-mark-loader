package me.libme.baidu.mapdata.searchindex.impl;

import me.libme.baidu.mapdata.searchindex.keyword.BaiduSearchSourcer;
import me.libme.baidu.mapdata.searchindex.keyword.KeywordConsumer;
import me.libme.baidu.mapdata.searchindex.keyword.KeywordFilter;
import me.libme.baidu.mapdata.searchindex.keyword.KeywordSearch;
import me.libme.baidu.mapdata.searchindex.point.IPointPersist;
import me.libme.baidu.mapdata.searchindex.point.MapDataSourcer;
import me.libme.baidu.mapdata.searchindex.point.PointPersitConsumer;
import me.libme.kernel._c.pubsub.*;
import me.libme.kernel._c.util.ThreadUtil;
import me.libme.xstream.ConsumerMeta;
import me.libme.xstream.SourceMeta;
import me.libme.xstream.Topology;
import me.libme.xstream.WindowTopology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by J on 2018/2/28.
 */
public class BaiduSearchPersistStarter implements Topology{

    private static Logger LOGGER= LoggerFactory.getLogger(BaiduSearchPersistStarter.class);

    private static ExecutorService searchExecutor= Executors.newFixedThreadPool(ThreadUtil.recommendCount(),
            r->new Thread(r,"real thread on executing topology[mapkeyword search]"));

    private static ScheduledExecutorService windowExecutor=Executors.newScheduledThreadPool(1,
            r->new Thread(r,"map-window-topology-scheduler"));

    private static ExecutorService mapdataExecutor= Executors.newFixedThreadPool(ThreadUtil.recommendCount(),
            r->new Thread(r,"real thread on executing topology[mapdata persist]"));


    private Builder builder;


    @Override
    public void shutdown() {

        searchExecutor.shutdown();
        windowExecutor.shutdown();
        mapdataExecutor.shutdown();

    }

    @Override
    public void start() {

        QueuePool queuePool= QueuePools.defaultPool();

        //keyword
        SourceMeta sourceMeta=new SourceMeta("Keyword Source");

        Topic keywordTopic= builder.keywordTopic;// new Topic("Baidu Keyword");

        Subscriber subscriber=new Subscriber(keywordTopic, queuePool);

        BaiduSearchSourcer baiduSearchSourcer=new BaiduSearchSourcer(sourceMeta,subscriber);

        KeywordFilter keywordFilter=new KeywordFilter();

        Topic mapdataTopic=builder.mapdataTopic;//new Topic("map-data");

        Publisher publisher=new Publisher(mapdataTopic,queuePool);

        KeywordSearch keywordSearch=builder.keywordSearch;//new DefaultKeywordSearch();

        KeywordConsumer keywordConsumer=new KeywordConsumer(new ConsumerMeta("Keyword Consumer"),keywordSearch,publisher);

        WindowTopology.builder().setName("Baidu Keyword Search")
                .setSourcer(baiduSearchSourcer)
                .addFiler(keywordFilter)
                .addConsumer(keywordConsumer)
                .windowExecutor(windowExecutor)
                .executor(searchExecutor)
                .setSchedule(builder.schedule)
                .build().start();

        //mapdata


        SourceMeta mapdataSourceMeta=new SourceMeta("Mapdata Source");
        Subscriber mapdataSubscriber=new Subscriber(mapdataTopic, queuePool);

        MapDataSourcer mapDataSourcer=new MapDataSourcer(mapdataSourceMeta,mapdataSubscriber);

        IPointPersist pointPersist=builder.pointPersist;
        PointPersitConsumer pointPersitConsumer=new PointPersitConsumer(new ConsumerMeta("Point Persist"),pointPersist);

        WindowTopology.builder().setName("Baidu Mapdata Persist")
                .setSourcer(mapDataSourcer)
                .addConsumer(pointPersitConsumer)
                .windowExecutor(windowExecutor)
                .executor(mapdataExecutor)
                .setSchedule(builder.schedule)
                .build().start();

    }

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{

        private Topic keywordTopic;

        private Topic mapdataTopic;

        private IPointPersist pointPersist;

        private KeywordSearch keywordSearch;

        private int schedule=500;

        public Builder schedule(int schedule) {
            this.schedule = schedule;
            return this;
        }

        public Builder keywordTopic(Topic keywordTopic) {
            this.keywordTopic = keywordTopic;
            return this;
        }

        public Builder mapdataTopic(Topic mapdataTopic) {
            this.mapdataTopic = mapdataTopic;
            return this;
        }

        public Builder pointPersist(IPointPersist pointPersist) {
            this.pointPersist = pointPersist;
            return this;
        }

        public Builder keywordSearch(KeywordSearch keywordSearch) {
            this.keywordSearch = keywordSearch;
            return this;
        }

        public BaiduSearchPersistStarter build(){
            Objects.requireNonNull(keywordTopic);
            Objects.requireNonNull(mapdataTopic);
            Objects.requireNonNull(pointPersist);

            BaiduSearchPersistStarter baiduSearchPersistStarter=new BaiduSearchPersistStarter();
            baiduSearchPersistStarter.builder=this;
            return baiduSearchPersistStarter;
        }

    }


}
