package com.airline.controllers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.airline.models.Passenger;

/**
 * Application Lifecycle Listener implementation class AirlineListener
 *
 */
@WebListener
public class AirlineListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AirlineListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	ServletContext sc = event.getServletContext();
    	
    	ArrayList<Passenger> passengerList = (ArrayList<Passenger>) sc.
    			getAttribute("passengerList");
    	
    	if(passengerList == null){
    		System.out.println("No passenger list created yet, Let's create the list here");
    		
    		passengerList = new ArrayList<Passenger>();
    		
    		sc.setAttribute("passengerList", passengerList);
    	}
    }
	
}
