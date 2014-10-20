package com.example.sqlitedemo;

public class BMIObject implements java.io.Serializable{
	
	private long id;
	private double height;
	private double weight;
	private double bmi;
	private int Date;

	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getDate() {
		return Date;
	}
	public void setDate(int date) {
		Date = date;
	}

}
