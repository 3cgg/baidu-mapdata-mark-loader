package test.me.libme.baidu.mapdata.searchindex;

import me.libme.baidu.mapdata.searchindex.keyword.BaiduSearchSourcer;
import me.libme.baidu.mapdata.searchindex.keyword.KeywordConsumer;
import me.libme.baidu.mapdata.searchindex.keyword.KeywordFilter;
import me.libme.baidu.mapdata.searchindex.keyword.KeywordSearch;
import me.libme.baidu.mapdata.searchindex.point.ESPointPersist;
import me.libme.baidu.mapdata.searchindex.point.IPointPersist;
import me.libme.baidu.mapdata.searchindex.point.MapDataSourcer;
import me.libme.baidu.mapdata.searchindex.point.PointPersitConsumer;
import me.libme.kernel._c.pubsub.*;
import me.libme.kernel._c.util.ThreadUtil;
import me.libme.module.es5x6.RestESDocumentOperations;
import me.libme.module.es5x6.RestHighLevelClientBuilder;
import me.libme.module.es5x6.SimpleBulkProcessorFactory;
import me.libme.xstream.ConsumerMeta;
import me.libme.xstream.SourceMeta;
import me.libme.xstream.WindowTopology;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by J on 2018/2/28.
 */
public class TestSearch {


    private static ExecutorService searchExecutor= Executors.newFixedThreadPool(ThreadUtil.recommendCount(),
            r->new Thread(r,"real thread on executing topology[keyword search]"));

    private static ScheduledExecutorService windowExecutor=Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors(),
            r->new Thread(r,"window-topology-scheduler"));

    private static ExecutorService mapdataExecutor= Executors.newFixedThreadPool(ThreadUtil.recommendCount(),
            r->new Thread(r,"real thread on executing topology[mapdata persist]"));

    public static void main(String[] args) throws InterruptedException{


        RestHighLevelClient restHighLevelClient
                =new RestHighLevelClientBuilder()
//                .addAddress("192.168.0.113",9200)
                .addAddress("192.168.93.128",9200)
                .auth("elastic","changeme")
                .addHeader("ES_REQUEST","ONLY_TEST")
                .build();

        RestESDocumentOperations restESDocumentOperations=new RestESDocumentOperations(restHighLevelClient)
                .configure().bulkProcessor(new SimpleBulkProcessorFactory()).ok();




        QueuePool queuePool=QueuePools.defaultPool();

        //keyword
        SourceMeta sourceMeta=new SourceMeta("Keyword Source");
        Subscriber subscriber=new Subscriber(new Topic("Baidu Keyword"), queuePool);

        BaiduSearchSourcer baiduSearchSourcer=new BaiduSearchSourcer(sourceMeta,subscriber);

        KeywordFilter keywordFilter=new KeywordFilter();

        Topic mapdataTopic=new Topic("map-data");

        Publisher publisher=new Publisher(mapdataTopic,queuePool);

        KeywordSearch keywordSearch=new DefaultKeywordSearch();

        KeywordConsumer keywordConsumer=new KeywordConsumer(new ConsumerMeta("Keyword Consumer"),keywordSearch,publisher);

        WindowTopology.builder().setName("Baidu Keyword Search")
                .setSourcer(baiduSearchSourcer)
                .addFiler(keywordFilter)
                .addConsumer(keywordConsumer)
                .windowExecutor(windowExecutor)
                .executor(searchExecutor)
                .build().start();

        //mapdata


        SourceMeta mapdataSourceMeta=new SourceMeta("Mapdata Source");
        Subscriber mapdataSubscriber=new Subscriber(mapdataTopic, queuePool);

        MapDataSourcer mapDataSourcer=new MapDataSourcer(mapdataSourceMeta,mapdataSubscriber);

        String indexName="";
        String typeName="";
        IPointPersist pointPersist=new ESPointPersist(restESDocumentOperations,indexName,typeName);
        PointPersitConsumer pointPersitConsumer=new PointPersitConsumer(new ConsumerMeta("Point Persist"),pointPersist);

        WindowTopology.builder().setName("Baidu Mapdata Persist")
                .setSourcer(mapDataSourcer)
                .addConsumer(pointPersitConsumer)
                .windowExecutor(windowExecutor)
                .executor(mapdataExecutor)
                .build().start();

        Thread.sleep(15*1000);

        System.out.println("===================================================");

    }


}
