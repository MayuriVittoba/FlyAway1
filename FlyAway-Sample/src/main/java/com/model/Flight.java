package com.model;

public class Flight {

	private int flightId, persons=4;
	private String airlineName, flightName, srcPlace = "dummy", destPlace, travelDate="dummy";
	private double ticketPrice;

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public int getPersonNumber() {
		return persons;
	}

	public void setPersonNumber(int persons) {
		this.persons = persons;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getSourcePlace() {
		return srcPlace;
	}

	public void setSourcePlace(String srcPlace) {
		this.srcPlace = srcPlace;
	}

	public String getDestPlace() {
		return destPlace;
	}

	public void setDestPlace(String destPlace) {
		this.destPlace = destPlace;
	}

	public String getDateOfTravel() {
		return travelDate;
	}

	public void setDateOfTravel(String travelDate) {
		this.travelDate = travelDate;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}