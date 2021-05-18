package com.safetynet.safetynetalerts.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAlias;

@Component
public class FireStation {

	private String address;
	@JsonAlias(value = "station")
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
