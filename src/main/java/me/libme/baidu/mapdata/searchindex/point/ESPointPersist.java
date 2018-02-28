package me.libme.baidu.mapdata.searchindex.point;

import me.libme.module.es5x6.ESDocumentOperations;
import me.libme.module.es5x6.ESModel;

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


        ESModel esModel=point.esModel();
        esModel.operations()
                .indexName(indexName)
                .type(typeName);

        String esId=esModel.esId();
        Map map=documentOperations.one(indexName,typeName,esId);

        if(map==null||map.isEmpty()){
            documentOperations.insert(indexName,typeName,esModel);
        }else{
            documentOperations.update(indexName,typeName,esId,esModel);
        }

    }



}
