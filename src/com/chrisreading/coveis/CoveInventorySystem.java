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
		
		im.addItem(new Item("Hot Cheetos", 1.23, 50));
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
	public InventoryManager getIM() {
		return im;
	}
	
	/**
	 * Get the DataManager
	 * @return
	 */
	public DataManager getDM() {
		return dm;
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		new CoveInventorySystem(); // initialize backend
		// launch program
		Application.launch(CoveApplication.class, args);
	}

}
