package me.libme.baidu.mapdata.searchindex.point;

import me.libme.module.es5x6.ESDocumentOperations;
import me.libme.module.es5x6.IESModel;

import java.util.Map;

/**
 * Created by J on 2018/2/27.
 */
public class ESPointPersist implements IPointPersist {


    private final ESDocumentOperations documentOperations;

    private final String indexName;

    private final String typeName;

    public ESPointPersist(ESDocumentOperations documentOperations, String indexName, String typeName) {
        this.documentOperations = documentOperations;
        this.indexName = indexName;
        this.typeName = typeName;
    }

    @Override
    public void persist(Point point) throws Exception {


        IESModel esModel=point.esModel();
        String esId=esModel.esId();
        Map map=documentOperations.one(indexName,typeName,esId);

        if(map.isEmpty()){
            documentOperations.insert(indexName,typeName,esModel);
        }else{
            documentOperations.update(indexName,typeName,esId,esModel);
        }

    }



}
