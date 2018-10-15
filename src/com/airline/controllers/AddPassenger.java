package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Gender;
import com.airline.models.Passenger;

/**
 * Servlet implementation class AddPassenger
 */
@WebServlet("/AddPassenger")
public class AddPassenger extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPassenger() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");
	    request.setAttribute("firstName", "");
	    request.setAttribute("lastName", "");
	    request.setAttribute("dob", "");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		request.setAttribute("errors", false);
		Passenger passengerObject = new Passenger();
		
		String firstName = request.getParameter("first-name");		
		System.out.println("First Name:" + firstName);
		
		if(firstName.length() == 0) {
			System.out.println("Empty first name error");
			request.setAttribute("errors", true);
			request.setAttribute("firstName_error", true);
			request.setAttribute("firstName", "");
		}
		else{
			passengerObject.setFirstName(firstName);
			request.setAttribute("firstName", firstName);
		}
		
		String lastName = request.getParameter("last-name");
		
		if(lastName.length() == 0) {
			System.out.println("Empty last name error");
			request.setAttribute("errors", true);
			request.setAttribute("lastName_error", true);
			request.setAttribute("lastName", "");
		}		
		else{
			passengerObject.setLastName(lastName);
			request.setAttribute("lastName", lastName);
		}
		
		String dob_raw = request.getParameter("dob");		
		String dobArray[] = dob_raw.split("\\/");		
		String patterRegx = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
		
		if(dob_raw.matches(patterRegx)) {
			String dobDay = dobArray[0];
			String dobMonth = dobArray[1];
			String dobYear = dobArray[2];
		
			Calendar cal = Calendar.getInstance();
		
			cal.set(Calendar.YEAR, Integer.parseInt(dobYear));
			cal.set(Calendar.MONTH, Integer.parseInt(dobMonth));
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dobYear));
		
			Date dob = cal.getTime();
			
			passengerObject.setDob(dob);
			request.setAttribute("dob", dob_raw);
		}
		
		else {
			System.out.println("Invalid date format");
			request.setAttribute("errors", true);
			request.setAttribute("date_format_error", true);
			request.setAttribute("dob", dob_raw);
			if(dob_raw.length() == 0){
				request.setAttribute("dob", "");
			}
		}
		
		String gender = request.getParameter("gender");
		passengerObject.setGender(Gender.valueOf(gender));
		
		if((Boolean)request.getAttribute("errors")) {
			
			RequestDispatcher view = request.
					getRequestDispatcher("WEB-INF/views/add_passenger.jsp");	
			view.forward(request, response);
		}
		else{
			
			ServletContext sc = this.getServletContext();
			synchronized(this){
				ArrayList<Passenger> passengerList =(ArrayList<Passenger>) sc.
					getAttribute("passengerList");	
			
				passengerList.add(passengerObject);
			
				sc.setAttribute("passengerList", passengerList);
				response.sendRedirect("");
			}
		}
		
	}

}
