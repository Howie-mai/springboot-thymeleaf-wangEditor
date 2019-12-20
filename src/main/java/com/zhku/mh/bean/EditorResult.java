package com.zhku.mh.bean;

import java.util.List;

/**
 * ClassName：
 * Time：2019/12/20 14:15
 * Description：
 * Author： mh
 */
public class EditorResult {
    private Integer errno;
    private List<String> data;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
