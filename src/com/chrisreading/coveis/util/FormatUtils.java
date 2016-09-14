package com.chrisreading.coveis.util;

import java.text.NumberFormat;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Easy access to formatting methods
 */
public class FormatUtils {

	/**
	 * Converts doubles to decimal values
	 * @param value
	 * @return
	 */
	public static String doubleToPrice(double value) {
		NumberFormat format = NumberFormat.getCurrencyInstance();
		return format.format(value);
	}

	public static StringProperty doubleToPrice(DoubleProperty priceProperty) {
		NumberFormat format = NumberFormat.getCurrencyInstance();		
		return new SimpleStringProperty(format.format(priceProperty));
	}
	
}
