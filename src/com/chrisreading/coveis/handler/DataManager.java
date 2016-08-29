package com.chrisreading.coveis.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.chrisreading.coveis.model.Item;

/**
 * Manages the loading/saving of
 * data
 */
public class DataManager {
	
	private List<Item> items;
	private File dir, inventory;
	
	/**
	 * Default constructor
	 */
	public DataManager() {
		items = new ArrayList<Item>(); // create empty list before populated
		this.dir = new File(System.getenv("APPDATA") + "\\CoveInventory\\"); // directory where program files are saved/loaded
		this.inventory = new File(dir + "\\inventory.bin"); // file where items are saved into
	}
	
	/**
	 * Get the inventory list
	 * @return List of items
	 */
	public List<Item> getItems() {
		return items;
	}
	
	/**
	 * Called at startup of program
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public void load() throws IOException, ClassNotFoundException {
		// if doesn't exit, go through first time setup
		if(!dir.exists()) {
			// create directory
			if(dir.mkdir()) {
				System.out.println("Created " + dir.getAbsolutePath());
				
				// then create file
				if(inventory.createNewFile()) {
					System.out.println("Created " + inventory.getAbsolutePath());
				} else {
					System.out.println("Error " + inventory.getAbsolutePath());
				}
			} else {
				System.out.println("Error " + dir.getAbsolutePath());
			}
		} else {
			// if the dir exists, load from inventory file to load arraylist
			FileInputStream fis = new FileInputStream(inventory);
			ObjectInputStream ois = new ObjectInputStream(fis);
			items = (ArrayList<Item>) ois.readObject();
			ois.close();
			
			System.out.println("Loaded " + inventory.getName());
		}
		
		System.out.println("Load complete");
	}
	
	/**
	 * Called before exit of program
	 * @throws IOException 
	 */
	public void save() throws IOException {
		// only perform if the file exists
		if(inventory.exists()) {
			FileOutputStream fos = new FileOutputStream(inventory);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(items);
			oos.close();
			
			System.out.println("Saved successful");
		} else {
			System.out.println("Error saving");
		}
		
		System.out.println("Save complete");
	}

}
