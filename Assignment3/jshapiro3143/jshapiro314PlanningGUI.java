// PlanningGUI.java
//
// Author: Rahul Simha
// Jan, 2008
//
// This is the starting point for the planning algorithm demo:
//    java PlanningGUI


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;


public class jshapiro314PlanningGUI extends JFrame {

    // Write text feedback at the top of the frame.
    JLabel status = new JLabel (" ");

    // Other GUI stuff.
    JTabbedPane tabbedPane;
    JTextField algField = new JTextField (20);
    JButton nextB = new JButton ("Next-step");

    // Three problems currently supported.
    jshapiro314ArmProblem armProblem;

    // Which planner, which problem etc. These are interfaces.
    jshapiro314Planner planner;
    jshapiro314PlanningProblem problem;
    LinkedList<State> plan;


    //////////////////////////////////////////////////////////////////////
    // main

    public static void main (String[] argv)
    {
        new jshapiro314PlanningGUI ();
    }


    //////////////////////////////////////////////////////////////////////
    // GUI construction

    public jshapiro314PlanningGUI ()
    {
        this.setSize (800, 500);
        this.setResizable (true);
        this.setBackground (Color.gray);

        armProblem = new jshapiro314ArmProblem (status);

        Container cPane  = this.getContentPane();
        cPane.add (status, BorderLayout.NORTH);

        cPane.add (armProblem.getFullPanel(), BorderLayout.CENTER);
        cPane.add (makeBottomPanel(), BorderLayout.SOUTH);

        this.setVisible (true);
        problem = armProblem;
    }



    JPanel makeBottomPanel ()
    {
        JPanel panel = new JPanel ();

        panel.add (new JLabel ("Algorithm: "));
        panel.add (algField);
        JButton loadB = new JButton ("Load");
        panel.add (loadB);
        loadB.addActionListener (
        new ActionListener ()
        {
            public void actionPerformed (ActionEvent a)
            {
                loadAlgorithm ();
            }
        }
        );

        panel.add (new JLabel ("    "));
        JButton planB = new JButton ("Plan");
        panel.add (planB);
        planB.addActionListener (
        new ActionListener ()
        {
            public void actionPerformed (ActionEvent a)
            {
                plan ();
            }
        }
        );

        panel.add (new JLabel ("    "));
        panel.add (nextB);
        nextB.addActionListener (
        new ActionListener ()
        {
            public void actionPerformed (ActionEvent a)
            {
                next ();
            }
        }
        );
        nextB.setEnabled (false);

        panel.add (new JLabel ("    "));
        JButton quitB = new JButton ("Quit");
        quitB.addActionListener (
        new ActionListener () {
            public void actionPerformed (ActionEvent a)
            {
                System.exit(0);
            }
        }
        );
        panel.add (quitB);

        return panel;
    }



    //////////////////////////////////////////////////////////////////////
    // GUI event handling


    void plan ()
    {
        if (planner != null) {
            System.out.println ("Starting plan generation ...");
            status.setText ("Starting plan generation ...");
            plan = planner.makePlan (problem, problem.getStartState(), false, armProblem.targetX, armProblem.targetY);
            if (plan == null) {
                status.setText ("No solution found");
                return;
            }
            problem.setPlan (plan);
            status.setText ("Planning complete");
            nextB.setEnabled (true);
        }
        else {
            status.setText ("Error: No planner loaded");
        }
    }


    void loadAlgorithm ()
    {
        String className = algField.getText();
        try {
            planner = (jshapiro314Planner) (Class.forName(className)).newInstance();
            status.setText (className + " loaded");
            nextB.setEnabled (false);

            //Precompute the 5 locations when the planner is loaded
            planner.prePlan(problem, problem.getStartState());
        }
        catch (Exception e) {
            System.out.println (e);
            status.setText ("Failed to load " + className);
        }
    }

    void next ()
    {
        armProblem.next ();
    }

}
