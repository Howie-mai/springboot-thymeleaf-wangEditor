package com.zhku.mh.bean;

import java.util.List;

/**
 * ClassName：
 * Time：2019/12/23 9:53
 * Description：
 * Author： mh
 */
public class EChart {
    private String name;
    private String type;
    private List<Integer> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

}
