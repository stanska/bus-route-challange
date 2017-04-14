package com.assignment.goeur;

public class DirectRouteRequest {

    public DirectRouteRequest(Integer arr_sid, Integer dep_sid) {
        this.arr_sid = arr_sid;
        this.dep_sid = dep_sid;
    }

    private Integer arr_sid;
    private Integer dep_sid;

    public Integer getArr_sid() {
        return arr_sid;
    }

    public Integer getDep_sid() {
        return dep_sid;
    }

}
