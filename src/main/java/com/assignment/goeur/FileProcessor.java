package com.assignment.goeur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProcessor {
    public FileProcessor(String filePath) {
        this.filePath = filePath;
    }

    private String filePath;
    private Pattern pattern = Pattern.compile("(\\d+)\\s(.+)");

    public BusRoutesForStations load() throws IOException {
        System.out.println("Loading data..........");
        Map<Integer, Set<Integer>> busRoutesForStations = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            Integer busRoutesNumber = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    Integer busRoute = Integer.valueOf(matcher.group(1));
                    String stations = matcher.group(2);
                    for (String station : stations.split("\\s")) {
                        Integer stationId = Integer.valueOf(station);
                        if (busRoutesForStations.get(stationId) == null) {
                            busRoutesForStations.put(stationId, new HashSet<>());
                        }

                        busRoutesForStations.get(stationId).add(busRoute);
                    }
                }
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        return new BusRoutesForStations(busRoutesForStations);
    }
}
