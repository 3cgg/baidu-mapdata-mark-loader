package me.libme.baidu.mapdata.searchindex.impl;

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
}
