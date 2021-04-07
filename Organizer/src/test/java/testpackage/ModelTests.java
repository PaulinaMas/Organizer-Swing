/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpackage;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.exception.NoParametersException;
import pl.polsl.model.*;

/**
 * Class for Tests for Model
 * @author Paulina Maslowska
 * @version 2.0
 */
public class ModelTests {

    OrganizerModel model = new OrganizerModel();

    /**
     * Test passes when exception NoParameters (empty table) is thrown.
     */
    @Test
    public void setOwnerTest() {
        String tab[] = new String[0];
        try {
            model = new OrganizerModel(tab);
            fail("An exception should be thrown when tab is empty");
        } catch (NoParametersException e) {
        }
    }

    /**
     * Test passes when added value and saved value are identical
     */
    @Test
    public void addEventTest() {
        String tab[] = {"ab"};
        try {
            model = new OrganizerModel(tab);

        } catch (NoParametersException e) {
        }
        Event event = new Event("testevent", "20/02/2020", "18.00", "samplenote", "LOW");
        model.addEvent(event);
        List<Event> ev = model.getEvents();
        assertEquals("testevent", ev.get(0).getName(), "Added value should be identical");
        assertEquals("20/02/2020", ev.get(0).getDate(), "Added value should be identical");
        assertEquals("18.00", ev.get(0).getTime(), "Added value should be identical");
        assertEquals("samplenote", ev.get(0).getNote(), "Added value should be identical");
    }

    /**
     * Test passes when dates when dates are valid
     *
     * @param test some correct dates
     */
    @ParameterizedTest
    @ValueSource(strings = {"20/02/2000", "2/02/2000", "2/2/2000", "20/2/2000"})
    public void validateDateTest(String test) {
        Event ev = new Event();
        assertTrue(ev.validateDate(test), "Not correct date");
    }

    /**
     * Test passes when dates when dates are not valid
     *
     * @param test some correct dates
     */
    @ParameterizedTest
    @ValueSource(strings = {"20-02-2000", "20.02.2000", "20/002/2000", "20.2/2000"})
    public void validateDateTestForBadDates(String test) {
        Event ev = new Event();
        assertFalse(ev.validateDate(test), "Expected not valid string");
    }

    /**
     * Test passes when added event's date matches to finded event
     *
     * @param sampledate some correct dates
     */
    @ParameterizedTest
    @ValueSource(strings = {"20/02/2020", "20/01/2020"})
    public void findEventTest(String sampledate) {
        String tab[] = {"ab"};
        try {
            model = new OrganizerModel(tab);

        } catch (NoParametersException e) {
        }
        Event event = new Event("testevent", sampledate, "18.00", "samplenote", "LOW");
        model.addEvent(event);
        Event event1 = new Event("testevent", sampledate, "16.00", "samplenote1", "LOW");
        model.addEvent(event1);
        model.findEvent("testevent").stream().map(e -> {
            assertEquals("testevent", e.getName(), "Added value should be identical");
            return e;
        }).forEachOrdered(e -> {
            assertEquals(sampledate, e.getDate(), "Added value should be identical");
        });

    }

    /**
     * Test passes when added event's date matches to sorted event
     */
    @Test
    public void sortEventsTest() {
        Event event = new Event("high", "1/1/2020", "18.00", "samplenote", "HIGH");
        model.addEvent(event);
        Event event1 = new Event("low", "1/1/2020", "18.00", "samplenote", "LOW");
        model.addEvent(event1);
        Event event2 = new Event("medium", "1/1/2020", "18.00", "samplenote", "MEDIUM");
        model.addEvent(event2);
        model.sortEvents();
        List<Event> ev = model.getEvents();
        assertEquals("high", ev.get(0).getName(), "Added value should be high");
        assertEquals("medium", ev.get(1).getName(), "Added value should be medium");
        assertEquals("low", ev.get(2).getName(), "Added value should be low");

    }

}
