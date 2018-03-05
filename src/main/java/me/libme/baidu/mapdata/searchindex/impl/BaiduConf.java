package me.libme.baidu.mapdata.searchindex.impl;

/**
 * Created by J on 2018/3/5.
 */
public class BaiduConf {


    /**
     * 	访问应用（AK）
     */
    private String ak;


    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
