package com.assignment.goeur;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BusRoutesForStations {
    public BusRoutesForStations(Map<Integer, Set<Integer>> busRoutesForStations){
        this.busRoutesForStations = busRoutesForStations;
    }
    private Map<Integer, Set<Integer>> busRoutesForStations;


    protected Logger logger = Logger.getLogger(BusRoutesForStations.class.getName());

    public Boolean direct(Integer arrivalStationId, Integer departureStationId) {
        if ( busRoutesForStations == null) {
            logger.log(Level.SEVERE, "Empty bus routes for stations!");
            return false;
        }
        Set<Integer> routesForArrivalStationId = busRoutesForStations.get(arrivalStationId);
        Set<Integer> routesForDepartureStationId = busRoutesForStations.get(departureStationId);
        if ( routesForArrivalStationId == null || routesForDepartureStationId == null) {
            return false;
        }
        Set<Integer> intersection = new HashSet<>(routesForArrivalStationId);
        intersection.retainAll(routesForDepartureStationId);
        return intersection.size() > 0;
    }
}
