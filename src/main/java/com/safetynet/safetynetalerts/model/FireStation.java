package com.safetynet.safetynetalerts.model;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonAlias;


public class FireStation {

	private String address;
	@JsonAlias(value = "station")
	@Min(value = 1)
	private int stationNumber;

	public FireStation() {
		super();
	}

	public FireStation(String address, int stationNumber) {
		super();
		this.address = address;
		this.stationNumber = stationNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	@Override
	public String toString() {
		return "FireStation [address=" + address + ", stationNumber=" + stationNumber + "]";
	}
}
