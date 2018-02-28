package me.libme.baidu.mapdata.searchindex.impl;

import me.libme.module.es5x6.ESModel;

/**
 * Created by J on 2018/2/28.
 */
public class ReferencePoint extends SimplePoint {

    private String id;

    private String description;

    private String link; // whitespace tokenizer

    private String category; // whitespace tokenizer

    private String cppEventSource; // data source

    private String changeLog;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCppEventSource() {
        return cppEventSource;
    }

    public void setCppEventSource(String cppEventSource) {
        this.cppEventSource = cppEventSource;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    @Override
    public ESModel esModel() {
        ESModel esModel=new ESModel();
        esModel.operations()
                .id(logicUniqueId())
                .setString("name",name())
                .setString("longitude",longitude())
                .setString("latitude",latitude())
                .setString("addr",addr())
                .setString("description",getDescription())
                .setString("link",getLink())
                .setString("category",getCategory())
                .setString("changeLog",getChangeLog())
                .setString("longitude",longitude());
        return esModel;
    }
}
