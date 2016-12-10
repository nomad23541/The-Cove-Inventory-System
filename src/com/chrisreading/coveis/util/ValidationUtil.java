package com.chrisreading.coveis.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Easily validate text fields and
 * restrict them to numeral/text/etc.
 */
public class ValidationUtil {
	
	public static void validateDecimal(TextField field) {
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.matches("^(?:\\d*\\.)?\\d+$")) {
					field.setText(newValue.replaceAll("[^0-9.]", ""));
				}
			}
		});
	}
	
	public static void validateNumeral(TextField field) {
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.matches("^[0-9]*$")) {
					field.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

}
