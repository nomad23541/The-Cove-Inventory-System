package com.chrisreading.coveis;

import java.io.IOException;

import com.chrisreading.coveis.handler.DataManager;
import com.chrisreading.coveis.model.Item;

/**
 * Main class, program is initiated here
 * Temporary code below to make sure inventory system is working
 */
public class CoveInventorySystem {
	
	public CoveInventorySystem() {
		DataManager dm = new DataManager();
		
		try {
			dm.load();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		if(!dm.getItems().isEmpty()) {
			for(Item item : dm.getItems()) {
				System.out.println(item.getName() + ", " + item.getPrice() + ", " + item.getAmount());
			}	
		}
		
		dm.getItems().add(new Item("Hot Cheetos", 4.00, 23));
		dm.getItems().add(new Item("Diet Pepsi", 1.23, 5));
		
		// currently getting notseriazable error for fx simpleproperty objects, need to fix
		try {
			dm.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new CoveInventorySystem();
	}

}
