package com.chrisreading.coveis;

import java.io.IOException;

import com.chrisreading.coveis.handler.DataManager;
import com.chrisreading.coveis.handler.InventoryManager;
import com.chrisreading.coveis.model.Item;

import javafx.application.Application;

/**
 * Main class, program is initiated here
 */
public class CoveInventorySystem {
	
	/** Instance of this class */
	private static CoveInventorySystem instance;
	
	private InventoryManager im; // inventory manager used
	private DataManager dm; // data manager used
	
	public CoveInventorySystem() throws ClassNotFoundException, IOException {
		instance = this;
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
	
	/**
	 * Get an instance of this class to access the managers
	 * @return
	 */
	public static CoveInventorySystem getInstance() {
		return instance;
	}
	
	/**
	 * Get the InventoryManager
	 * @return
	 */
	public InventoryManager getInventoryManager() {
		return im;
	}
	
	/**
	 * Get the DataManager
	 * @return
	 */
	public DataManager getDataManager() {
		return dm;
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		new CoveInventorySystem(); // initialize backend
		// launch program
		Application.launch(CoveApplication.class, args);
	}

}
