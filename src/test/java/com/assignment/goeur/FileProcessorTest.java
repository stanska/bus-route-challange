package com.assignment.goeur;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class FileProcessorTest
{
    FileProcessor fileProcessor = new FileProcessor("./src/test/resources/testRoutes.txt");

    @Test
    public void givenTestFileSuccessfulScenario() throws IOException {
        BusRoutesForStations busRoutesForStations = fileProcessor.load();
        assertTrue(busRoutesForStations.direct(3,6));
    }

}
