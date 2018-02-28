package test.me.libme.baidu.mapdata.searchindex;

import me.libme.baidu.mapdata.searchindex.impl.BaiduSearchPersistStarter;
import me.libme.baidu.mapdata.searchindex.impl.SimpleSearchParam;
import me.libme.baidu.mapdata.searchindex.keyword.KeywordSearch;
import me.libme.baidu.mapdata.searchindex.point.IPointPersist;
import me.libme.baidu.mapdata.searchindex.point.MockESPoint;
import me.libme.kernel._c.pubsub.Publisher;
import me.libme.kernel._c.pubsub.QueuePools;
import me.libme.kernel._c.pubsub.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by J on 2018/2/28.
 */
public class TestSearch2 {


    public static void main(String[] args) throws InterruptedException{




//
//        RestHighLevelClient restHighLevelClient
//                =new RestHighLevelClientBuilder()
////                .addAddress("192.168.0.113",9200)
////                .addAddress("192.168.93.128",9200)
//                .addAddress("218.4.170.234",9200)
////                .auth("elastic","changeme")
////                .addHeader("ES_REQUEST","ONLY_TEST")
//                .build();
//        RestESDocumentOperations restESDocumentOperations=new RestESDocumentOperations(restHighLevelClient)
//                .configure().bulkProcessor(new SimpleBulkProcessorFactory()).ok();
//

        Topic keywordTopic=new Topic("baidu-keyword");
        KeywordSearch keywordSearch=new DefaultKeywordSearch();


        Topic mapdataTopic=new Topic("map-data");

//        String indexName="cpp-mappoint-index";
//        String typeName="point";
//        IPointPersist pointPersist=new ESPointPersist(restESDocumentOperations,indexName,typeName);
        IPointPersist pointPersist=new MockESPoint();//new ESPointPersist(restESDocumentOperations,indexName,typeName);

        BaiduSearchPersistStarter.builder()
                .keywordTopic(keywordTopic)
                .keywordSearch(keywordSearch)
                .mapdataTopic(mapdataTopic)
                .pointPersist(pointPersist)
                .build().start();


        Publisher publisher=new Publisher(keywordTopic, QueuePools.defaultPool());


        List data=new ArrayList();
        data.add(new SimpleSearchParam("祖庙"));
        data.add(new SimpleSearchParam("狮子林"));
        data.add(new SimpleSearchParam("博物馆"));
        data.add(new SimpleSearchParam("欧尚"));
        data.add(new SimpleSearchParam("金鸡湖"));
        data.add(new SimpleSearchParam("科技园"));
        data.add(new SimpleSearchParam("独墅湖"));

        Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(()->{
                    try {

                        publisher.produce().produce(data.get(new Random().nextInt(data.size())));

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                },0,5000, TimeUnit.MILLISECONDS);



        Thread.sleep(15*1000);

        System.out.println("===================================================");

    }


}
