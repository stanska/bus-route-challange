package com.assignment.goeur;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class BusRoutesForStationsTest {

    @Test
    public void givenNullBusRouteForStationsMapThenFalseAndLogError()  {
        BusRoutesForStations busRoutesForStations = new BusRoutesForStations(null);
        busRoutesForStations.logger = mock(Logger.class);
        assertFalse(busRoutesForStations.direct(1,2));
        verify(busRoutesForStations.logger).log(Level.SEVERE, "Empty bus routes for stations!");
    }

    @Test
    public void whenDirectWithNotExistingArrivalThenFalse()  {
        //GIVEN
        HashMap<Integer, Set<Integer>> busRoutesForStationsData = setupBusRoutesForStationsForStation1();

        BusRoutesForStations busRoutesForStations = new BusRoutesForStations(busRoutesForStationsData);
        busRoutesForStations.logger = mock(Logger.class);
        //WHEN
        boolean result = busRoutesForStations.direct(1,2);
        assertFalse(result);
    }

    private HashMap<Integer, Set<Integer>> setupBusRoutesForStationsForStation1() {
        HashMap<Integer, Set<Integer>> busRoutesForStationsData = new HashMap<>();
        HashSet<Integer> busRoutesForStation1 = new HashSet<>();

        busRoutesForStation1.add(2);
        busRoutesForStation1.add(5);
        busRoutesForStation1.add(7);
        busRoutesForStationsData.put(1, busRoutesForStation1);
        return busRoutesForStationsData;
    }


    @Test
    public void whenDirectWithNotExistingDepartureThenFalse()  {
        //GIVEN
        HashMap<Integer, Set<Integer>> busRoutesForStationsData = setupBusRoutesForStationsForStation1();

        BusRoutesForStations busRoutesForStations = new BusRoutesForStations(busRoutesForStationsData);
        busRoutesForStations.logger = mock(Logger.class);
        //WHEN
        boolean result = busRoutesForStations.direct(0,1);
        //THEN
        assertFalse(result);
    }

    @Test
    public void givenArrivalAndDepartureInOneRoutewhenDirectThenTrue()  {
        //GIVEN
        HashMap<Integer, Set<Integer>> busRoutesForStationsData = setupBusRoutesForStationsForStation1();
        HashSet<Integer> busRoutesFor6 = new HashSet<>();
        busRoutesFor6.add(7);
        busRoutesForStationsData.put(6, busRoutesFor6);
        BusRoutesForStations busRoutesForStations = new BusRoutesForStations(busRoutesForStationsData);
        busRoutesForStations.logger = mock(Logger.class);
        //WHEN
        boolean result = busRoutesForStations.direct(1,6);
        //THEN
        assertTrue(result);
    }

    @Test
    public void givenArrivalAndDepartureNotInOneRoutewhenDirectThenTrue()  {
        //GIVEN
        HashMap<Integer, Set<Integer>> busRoutesForStationsData = setupBusRoutesForStationsForStation1();
        HashSet<Integer> busRoutesFor6 = new HashSet<>();
        busRoutesFor6.add(17);
        busRoutesForStationsData.put(6, busRoutesFor6);
        BusRoutesForStations busRoutesForStations = new BusRoutesForStations(busRoutesForStationsData);
        busRoutesForStations.logger = mock(Logger.class);
        //WHEN
        boolean result = busRoutesForStations.direct(1,6);
        //THEN
        assertFalse(result);
    }
}

