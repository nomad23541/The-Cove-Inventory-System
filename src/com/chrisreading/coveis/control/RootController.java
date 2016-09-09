package com.chrisreading.coveis.control;

import java.io.IOException;

import com.chrisreading.coveis.CoveApplication;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class RootController {
	
	/** Instance of application class */
	private CoveApplication ca;
	
	/** FXML */
	@FXML
	private MenuItem about;
	
	public RootController() {}

	@FXML
	public void initialize() {
	}
	
	@FXML
	private void onAboutClick() {
		try {
			ca.showAboutDialog();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Make this class access the rest of the gui
	 */
	public void setApplication(CoveApplication ca) {
		this.ca = ca;
	}
	
}
