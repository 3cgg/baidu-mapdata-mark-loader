package me.libme.baidu.mapdata.searchindex.impl;

/**
 * Created by J on 2018/3/5.
 */
public class BaiduConf {


    /**
     * 	访问应用（AK）
     */
    private String ak;

    /**
     * http://api.map.baidu.com/place/v2/search?
     */
    private String placeUrl;

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getPlaceUrl() {
        return placeUrl;
    }

    public void setPlaceUrl(String placeUrl) {
        this.placeUrl = placeUrl;
    }

    public <T extends BaiduConf> T from(BaiduConf baiduConf){
        this.setAk(baiduConf.getAk());
        this.setPlaceUrl(baiduConf.getPlaceUrl());
        return (T)this;
    }

}
