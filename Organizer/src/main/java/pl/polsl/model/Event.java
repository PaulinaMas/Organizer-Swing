package pl.polsl.model;

import java.util.regex.*;
import pl.polsl.view.OrganizerView;

/**
 * class for one event in organiser
 *
 * @author Paulina Maslowska
 * @version 1.0
 */
public class Event {

    OrganizerView view = new OrganizerView();
    /**
     * date in format dd/mm/yyyy
     */
    String date; 
    String time;
    String name; 
    String note; 
    EnumPriority.Priority priority;

    /**
     * Empty constructor for Event used in tests
     */
    public Event() {

    }

    /**
     * Constructor for event
     *
     * @param name name of the event
     * @param date date of the event
     * @param time time of the event
     * @param note additional informations
     */
    public Event(String name, String date, String time, String note, String priority) {
        this.name = name;
        if (validateDate(date)) {
            this.date = date;
        } else {
            this.date = "NULL"; //when date is not valid it is setted to NULL
            view.forMessageBoxes("Date set to NULL");
        }
        this.note = note;
        this.time = time;
        switch (priority) {
            case "LOW":
                this.priority = EnumPriority.Priority.LOW;
                break;
            case "MEDIUM":
                this.priority = EnumPriority.Priority.MEDIUM;
                break;
            case "HIGH":
                this.priority = EnumPriority.Priority.HIGH;
                break;
        }
    }

    /**
     * Functios uses regex for date validation
     *
     * @param date date of the event for validation
     * @return if date matches to pattern
     */
    public boolean validateDate(String date) {
        Pattern compiledPattern = Pattern.compile("^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$");
        Matcher matcher = compiledPattern.matcher(date);
        return matcher.matches();
    }

    /**
     *
     * @return Event ev.time 
     */
    public String getTime() {
        return this.time;
    }

    /**
     *
     * @return Event ev.date
     */
    public String getDate() {
        return this.date;
    }

    /**
     *
     * @return Event ev.name
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return Event ev.note
     */
    public String getNote() {
        return this.note;
    }

    /**
     *
     * @return priority of event
     */
    public EnumPriority.Priority getPriority() {
        return this.priority;
    }
/**
 * 
 * @return priority of event as it's position in enum
 */
    public int getPriorityValue() {
        return this.priority.ordinal();
    }

}
