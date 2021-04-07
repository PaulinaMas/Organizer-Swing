package pl.polsl;

import pl.polsl.controller.OrganizerController;

/**
 *
 * @author Paulina Maslowska
 * @version 1.0 
 * CLass contains main function
 */
public class Main {
/**
 * User can give any number of parameters, they are treated as one argument - user name
 * @param args arguments from comman line - owner's name
 */
    public static void main(String args[]) {
        //in args is user name (organizer's owner), it is used to welcome user 
        //date format: dd/mm/yyyy or d/m/yyyy or d/mm/yyyy or dd/m/yyyy
        Boolean[] ifgood = new Boolean[]{false}; //to check NoParameters exception: when it's thrown it is false
        OrganizerController controller = new OrganizerController(args, ifgood);
        if (ifgood[0] == true) {
            javax.swing.SwingUtilities.invokeLater(controller::createAndShowGUI);
        }
    }

}
