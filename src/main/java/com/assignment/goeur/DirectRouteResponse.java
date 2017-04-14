package com.assignment.goeur;

public class DirectRouteResponse {

    public DirectRouteResponse(Integer arr_sid, Integer dep_sid, Boolean direct_bus_route ) {
        this.arr_sid = arr_sid;
        this.dep_sid = dep_sid;
        this.direct_bus_route = direct_bus_route;
    }

    private Integer arr_sid;
    private Integer dep_sid;
    private Boolean direct_bus_route;

    public Integer getArr_sid() {
        return arr_sid;
    }

    public Integer getDep_sid() {
        return dep_sid;
    }

    public Boolean getDirect_bus_route() {
        return direct_bus_route;
    }

}
