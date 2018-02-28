package me.libme.baidu.mapdata.searchindex.impl;

import me.libme.baidu.mapdata.searchindex.point.Point;
import me.libme.kernel._c.json.JJSON;

/**
 * Created by J on 2018/2/28.
 */
public class SimplePoint implements Point {


    private String longitude;


    private String latitude;


    private String name;


    private String addr;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String longitude() {
        return getLongitude();
    }

    @Override
    public String latitude() {
        return getLatitude();
    }

    @Override
    public String name() {
        return getName();
    }

    @Override
    public String addr() {
        return getAddr();
    }


    @Override
    public String toString() {
        return JJSON.get().format(this);
    }
}
