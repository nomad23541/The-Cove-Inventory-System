package com.chrisreading.coveis.model;

import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *  Model for a food item sold in the cove
 */
public class Item {
	
	private StringProperty name;
	private DoubleProperty price;
	private IntegerProperty amount;
	
	/**
	 * Default constructor
	 */
	public Item() {
		
	}
	
	public Item(String name, double price, int amount) {
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleDoubleProperty(price);
		this.amount = new SimpleIntegerProperty(amount);
	}
	
	/**
	 * GETTERS & SETTERS
	 */
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getName() {
		return name.get();
	}
	
	public void setPrice(double price) {
		this.price.set(price);
	}
	
	public double getPrice() {
		return price.get();
	}
	
	public void setAmount(int amount) {
		this.amount.set(amount);
	}
	
	public int getAmount() {
		return amount.get();
	}

}
