// ThreeQueue.java
//
// Author: Rahul Simha
// Mar, 2008
//
// Customers leave the first queue and go to 2 or 3rd.
// Some fraction of departing return to first.


import java.util.*;
import java.text.*;



public class ThreeQueue {

    // Fraction that return to start.
    double fracReturn = 0.1;

    // Avg time between arrivals = 1.0, avg time at server=1/0.75.
    double arrivalRate = 1.0;
    double[] serviceRates = {2.0, 1.0, 1.0};

    // Three queues.
    LinkedList<Customer>[] queues;

    PriorityQueue<Event> eventList;

    double clock;

    // Statistics.
    int numArrivals;                        // How many arrived?
    int numDepartures;                      // How many left the system?
    double totalSystemTime, avgSystemTime;  // For time spent in system.
    double averageDepartureRate, endTime;


    void init ()
    {
	queues = new LinkedList [3];
        for (int i=0; i<3; i++) {
            queues[i] = new LinkedList<Customer>();
        }
        eventList = new PriorityQueue<Event> ();
        clock = 0.0;
        numArrivals = numDepartures = 0;
        totalSystemTime = 0.0;
        scheduleArrival ();
    }



    void simulate (int maxCustomers)
    {
        init ();

        while (numDepartures < maxCustomers) {
            Event e = eventList.poll ();
            clock = e.eventTime;
            if (e.type == Event.ARRIVAL) {
                handleArrival (e);
            }
            else {
                handleDeparture (e);
            }
        }
        endTime = clock;
        stats ();
    }


    void handleArrival (Event e)
    {
	numArrivals ++;
	queues[0].add (new Customer (clock));
	if (queues[0].size() == 1) {
	    scheduleDeparture (0);
	}
	scheduleArrival ();
    }



    void handleDeparture (Event e)
    {
	int k = e.whichQueue;
	Customer c = queues[k].removeFirst ();

        if (k == 0) {
            // Send to 1 or 2
            int w = chooseQueue ();
            queues[w].add (c);
            if (queues[w].size() == 1) {
                scheduleDeparture (w);
            }
        }
        else {
            // See if customer is returning to the start.
            if (RandTool.uniform() < fracReturn) {
                queues[0].add (c);
                if (queues[0].size() == 1) {
                    scheduleDeparture (0);
                }
            }
            else {
                // This is a departing customer.
                totalSystemTime += clock - c.arrivalTime;
                numDepartures ++;
            }
        }

	if (queues[k].size() > 0) {
	    scheduleDeparture (k);
	}
    }


    void scheduleArrival ()
    {
	double nextArrivalTime = clock + randomInterarrivalTime();
	eventList.add (new Event (nextArrivalTime, Event.ARRIVAL, -1));
    }


    void scheduleDeparture (int i)
    {
	double nextDepartureTime = clock + randomServiceTime (i);
	eventList.add (new Event (nextDepartureTime, Event.DEPARTURE, i));
    }


    int chooseQueue ()
    {
        //int k = UniformRandom.uniform (1,2);
        if(queues[1].size() < queues[2].size()){
            return 1;
        }else if(queues[1].size() > queues[2].size()){
            return 2;
        }else{
            return UniformRandom.uniform(1,2);
        }
    }


    double randomInterarrivalTime ()
    {
	return exponential (arrivalRate);
    }


    double randomServiceTime (int i)
    {
	return exponential (serviceRates[i]);
    }


    double exponential (double lambda)
    {
        return (1.0 / lambda) * (-Math.log(1.0 - UniformRandom.uniform()));
    }

    void stats ()
    {
	if (numDepartures == 0) {
	    return;
	}
	avgSystemTime = totalSystemTime / numDepartures;
    averageDepartureRate = endTime / numDepartures;
    }

    public String toString ()
    {
        String results = "Simulation results:";
        results += "\n  numDepartures:   " + numDepartures;
        results += "\n  avg System Time: " + avgSystemTime;
        results += "\n avg departure rate: " + averageDepartureRate;
        return results;
    }



    ///////////////////////////////////////////////////////////////////////
    // main

    public static void main (String[] argv)
    {
        ThreeQueue queue = new ThreeQueue ();
        queue.simulate (10000);
        System.out.println (queue);
    }

}


// Class Customer (one instance per customer) stores whatever we
// need for each customer. Since we collect statistics on waiting
// time at the time of departure, we need to record when a
// customer arrives.

class Customer {
    double arrivalTime;
    public Customer (double arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }
}


// Class Event has everything we need for an event: the type of
// event, and when it occurs. To use Java's PriorityQueue, we need
// have this class implement the Comparable interface where
// one event is "less" if it occurs sooner.

class Event implements Comparable {
    public static int ARRIVAL = 1;
    public static int DEPARTURE = 2;
    int type = -1;                     // Arrival or departure.
    double eventTime;                  // When it occurs.
    int whichQueue=-1;                 // A departure from which server?

    public Event (double eventTime, int type, int whichQueue)
    {
	this.eventTime = eventTime;
	this.type = type;
        this.whichQueue = whichQueue;
    }


    public int compareTo (Object obj)
    {
        Event e = (Event) obj;
        if (eventTime < e.eventTime) {
            return -1;
        }
        else if (eventTime > e.eventTime) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public boolean equals (Object obj)
    {
        return (compareTo(obj) == 0);
    }

}
