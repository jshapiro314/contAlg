
import java.util.*;
import java.io.*;

public class Classifier {

    // Names of files that contain samples from "category A"
    static String[] fileNamesA = {"A0.data", "A1.data", "A2.data", "A3.data", "A4.data"};

    // Names of files that contain samples from "category B"
    static String[] fileNamesB = {"B0.data", "B1.data", "B2.data", "B3.data", "B4.data"};
    
    // The data in each of the files are eventually placed in these data structures.
    // dataSetA[2] has all the data in "A2.data"
    // Because dataSetA[2] is a Vector of LineSegmentd's, then
    // dataSetA[2].get(5) is the 5th line segment in that file.
    static Vector<LineSegmentd>[] dataSetA;
    static Vector<LineSegmentd>[] dataSetB;

    // The is the unclassified (uncategorized) sample.
    static Vector<LineSegmentd> sample;
    

    public static void main (String[] argv)
    {
        readData ();
        printData ();
    }

    static void printData ()
    {
        // INSERT YOUR CODE IN THIS METHOD.

        System.out.println ("Data Set A:");
        for (int i=0; i<dataSetA.length; i++) {
            System.out.println ("  Data point i=" + i + ":");
            for (LineSegmentd L: dataSetA[i]) {
                // Use L.x1, L.y1, L.x2, L.y2
                System.out.println ("        " + L);
            }
        }

        System.out.println ("----------------------------------------");
        
        System.out.println ("Data Set B:");
        for (int i=0; i<dataSetB.length; i++) {
            System.out.println ("  Data point i=" + i + ":");
            for (LineSegmentd L: dataSetB[i]) {
                // Use L.x1, L.y1, L.x2, L.y2
                System.out.println ("        " + L);
            }
        }

        System.out.println ("----------------------------------------");

        System.out.println ("  Sample point: ");
        for (LineSegmentd L: sample) {
            // Use L.x1, L.y1, L.x2, L.y2
            System.out.println ("        " + L);
        }

        System.out.println ("----------------------------------------");

        
        double x1a = 0;
        double x2a = 0;
        double y1a = 0;
        double y2a = 0;
        System.out.println ("Data Set A:");
        for (int i=0; i<dataSetA.length; i++) {
            x1a = 0;
            x2a = 0;
            y1a = 0;
            y2a = 0;
            for (LineSegmentd L: dataSetA[i]) {
                // Use L.x1, L.y1, L.x2, L.y2
                x1a += L.x1;
                x2a += L.x2;
                y1a += L.y1;
                y2a += L.y2;
            }
            x1a = x1a / dataSetA[i].size();
            x2a = x2a / dataSetA[i].size();
            y1a = y1a / dataSetA[i].size();
            y2a = y2a / dataSetA[i].size();

            System.out.println(x1a);
            System.out.println(x2a);
            System.out.println(y1a);
            System.out.println(y2a);

        }

        double x1b = 0;
        double x2b = 0;
        double y1b = 0;
        double y2b = 0;
        System.out.println ("Data Set B:");
        for (int i=0; i<dataSetB.length; i++) {
            x1b = 0;
            x2b = 0;
            y1b = 0;
            y2b = 0;
            for (LineSegmentd L: dataSetB[i]) {
                // Use L.x1, L.y1, L.x2, L.y2
                x1b += L.x1;
                x2b += L.x2;
                y1b += L.y1;
                y2b += L.y2;
            }
            x1b = x1b / dataSetB[i].size();
            x2b = x2b / dataSetB[i].size();
            y1b = y1b / dataSetB[i].size();
            y2b = y2b / dataSetB[i].size();

            System.out.println(x1b);
            System.out.println(x2b);
            System.out.println(y1b);
            System.out.println(y2b);

        }

        double x1c = 0;
        double x2c = 0;
        double y1c = 0;
        double y2c = 0;
        System.out.println ("Sample Set:");
        for (LineSegmentd L: sample) {
            // Use L.x1, L.y1, L.x2, L.y2
            x1c += L.x1;
            x2c += L.x2;
            y1c += L.y1;
            y2c += L.y2;
        }
        x1c = x1c / sample.size();
        x2c = x2c / sample.size();
        y1c = y1c / sample.size();
        y2c = y2c / sample.size();

        System.out.println(x1c);
        System.out.println(x2c);
        System.out.println(y1c);
        System.out.println(y2c);

    }
    


    static void readData ()
    {
        dataSetA = new Vector [fileNamesA.length];
        for (int i=0; i<fileNamesA.length; i++) {
            dataSetA[i] = loadFile (fileNamesA[i]);
        }
        
        dataSetB = new Vector [fileNamesB.length];
        for (int i=0; i<fileNamesB.length; i++) {
            dataSetB[i] = loadFile (fileNamesB[i]);
        }

        sample = loadFile ("sample.data");
    }
    

    public static Vector<LineSegmentd> loadFile (String fileName)
    {
        try {
            Scanner scanner = new Scanner (new File(fileName));
            Vector<LineSegmentd> segments = new Vector<LineSegmentd>();
            while (scanner.hasNextDouble()) {
                double x1 = scanner.nextDouble();
                double y1 = scanner.nextDouble();
                double x2 = scanner.nextDouble();
                double y2 = scanner.nextDouble();
                LineSegmentd L = new LineSegmentd (x1,y1, x2,y2);
                segments.add (L);
            }
            return segments;
        }
        catch (IOException e){
            System.out.println (e);
            return null;
        }
    }


}

