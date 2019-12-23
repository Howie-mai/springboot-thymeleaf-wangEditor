package com.zhku.mh.bean;

import java.util.List;

/**
 * ClassName：
 * Time：2019/12/23 10:01
 * Description：
 * Author： mh
 */
public class EChartResult {
    private List<String> categories;

    private List<String> legend;

    private List<EChart> seriesList;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getLegend() {
        return legend;
    }

    public void setLegend(List<String> legend) {
        this.legend = legend;
    }

    public List<EChart> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<EChart> seriesList) {
        this.seriesList = seriesList;
    }
}
