package com.chrisreading.coveis;

import java.io.IOException;

import com.chrisreading.coveis.handler.DataManager;
import com.chrisreading.coveis.handler.InventoryManager;

import javafx.application.Application;

/**
 * Main class, program is initiated here
 */
public class CoveInventorySystem {
	
	private InventoryManager im;
	private DataManager dm;
	
	public CoveInventorySystem() throws ClassNotFoundException, IOException {
		im = new InventoryManager();
		dm = new DataManager();
		dm.load(); // first time setup / load previous files
		
		// probably not the best way to do this
		// but save the newly added data on exit
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    @Override
		    public void run() {
		        try {
					dm.save();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		});
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		new CoveInventorySystem(); // initialize backend
		// launch program
		Application.launch(CoveApplication.class, args);
	}

}
