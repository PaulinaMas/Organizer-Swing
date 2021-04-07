package pl.polsl.controller;

import pl.polsl.model.*;
import javax.swing.JFrame;
import pl.polsl.exception.*;
import pl.polsl.view.OrganizerView;

/**
 * Class for controller - creates GUI
 *
 * @author Paulina Maslowska
 * @version 1.0
 */
public class OrganizerController {

    OrganizerModel model;

    /**
     * constructor for OrganizerController, it sets arguments from main (for
     * possible choices)
     *
     * @param args arguments from comman line - owner's name
     * @param ifgood boolean - if exeption wasn't thrown GUI is created 
     */
    public OrganizerController(String args[], Boolean[] ifgood) {
        try {
            model = new OrganizerModel(args);
            ifgood[0] = true;

        } catch (NoParametersException e) {
        }
    }

    /**
     * This function creates and shows a new window
     */
    public void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        //new window
        JFrame frame = new JFrame("Organizer");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //new panel
        OrganizerView newContentPane = new OrganizerView(model);
        //visibility
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //show frame
        frame.pack();
        frame.setVisible(true);

    }
}
