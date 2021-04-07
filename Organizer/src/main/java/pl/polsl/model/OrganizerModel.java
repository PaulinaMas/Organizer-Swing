package pl.polsl.model;

import java.util.*;
import java.util.stream.Collectors;
import pl.polsl.exception.NoParametersException;
import pl.polsl.view.OrganizerView;

/**
 * Class for model - stores list of events
 *
 * @author Paulina Maslowska
 * @version 1.0
 */
public class OrganizerModel {

    private List<Event> events;
    /**
     * owner's name given in parameters in main
     */
    private String owner[]; 
    OrganizerView view = new OrganizerView();

    /**
     * Function creates new OrganizerModel
     *
     * @param args parameters form command line
     * @throws NoParametersException when no owner is given (no parameters in
     * command line)
     */
    public OrganizerModel(String args[]) throws NoParametersException {
        events = new ArrayList<>();
        try {
            setOwner(args);
        } catch (NoParametersException e) {
            //System.err.println(e.getMessage()); //prints "No parameters!"
            // showMessageDialog(null, e.getMessage());
            view.forMessageBoxes(e.getMessage());
            throw new NoParametersException();
        }
    }

    /**
     * function sets owner with parameters from command line
     *
     * @param args arguments from comman line - owner's name
     * @throws NoParametersException when args is empty
     */
    private void setOwner(String args[]) throws NoParametersException {
        if (args.length == 0) {
            throw new NoParametersException();
        } else {
            this.owner = args;
        }
    }

    /**
     * returns owner of organizer
     *
     * @return owner
     */
    public String[] getOwner() {
        return owner;
    }

    /**
     * creates new vector of events
     */
    public OrganizerModel() {
        events = new ArrayList<>();
    }

    /**
     * adds event to vector of events and sorts them by priority
     *
     * @param event
     */
    public void addEvent(Event event) {
        events.add(event);
        this.sortEvents();

    }

    /**
     * returns vector of events
     *
     * @return OrganizerModel om.events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Function searches events with the same name
     *
     * @param name name of searched event
     * @return list of finded events
     */
    public List<Event> findEvent(String name) {
        List<Event> ev = new ArrayList<>();
        events.stream()
                .filter(e -> e.getName().equals(name))
                .forEach(ev::add);

        return ev;
    }

    /**
     * Function sorts events by it's priority (from HIGH to LOW)
     */
    public void sortEvents() {
        this.events = events.stream()
                .sorted(Comparator.comparingInt(Event::getPriorityValue).reversed())
                .collect(Collectors.toList());

    }
}
