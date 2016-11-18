package com.chrisreading.coveis.handler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.chrisreading.coveis.model.Item;
import com.chrisreading.coveis.util.FileUtils;

/**
 * Manages the loading/saving of
 * data
 */
public class DataManager {
	
	private String OS = System.getProperty("os.name").toLowerCase(); // os property
	
	private List<Item> inventory;
	private File dir, inventoryDir;
	
	/**
	 * Default constructor
	 */
	public DataManager() {
		inventory = InventoryManager.getInstance().getInventoryList();
		
		// detect if on a mac or windows to set installation directories correctly
		if(OS.indexOf("win") >= 0) {
			this.dir = new File(System.getenv("APPDATA") + "/CoveInventory/"); // directory where program files are saved/loaded
			this.inventoryDir = new File(dir + "/Inventory/"); // directory where item files are saved/loaded
		} else if(OS.indexOf("mac") >= 0) {
			this.dir = new File(System.getProperty("user.home") + "/Library/Application Support/CoveInventory/");
			this.inventoryDir = new File(dir + "/Inventory/");
		} else if(OS.indexOf("nux") >= 0) {
			this.dir = new File(System.getProperty("user.home") + "/CoveInventory/");
			this.inventoryDir = new File(dir + "/Inventory/");
		}
	}
	
	/**
	 * Get the inventory list
	 * @return List of items
	 */
	public List<Item> getItems() {
		return inventory;
	}
	
	/**
	 * Called at startup of program
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void load() throws IOException, ClassNotFoundException {
		// if doesn't exit, go through first time setup
		if(!dir.exists()) {
			// create directory
			if(dir.mkdir()) {
				System.out.println("Created " + dir.getAbsolutePath());
				
				// then create inventory dir
				if(inventoryDir.mkdir()) {
					System.out.println("Created " + inventoryDir.getAbsolutePath());
				} else {
					System.out.println("Error " + inventoryDir.getAbsolutePath());
				}
			} else {
				System.out.println("Error " + dir.getAbsolutePath());
			}
		} else {
			// if the dir exists, load from item files to load inventory list
			for(File f : FileUtils.getFiles(inventoryDir)) {
				List<String> lines = Files.readAllLines(Paths.get(f.toURI())); // get all lines from file
				Item item = new Item(lines.get(0), Double.parseDouble(lines.get(1)), Integer.parseInt(lines.get(2)));
				inventory.add(item); // now add to the inventory list
				
				System.out.println("Loaded " + f.getName());
			}
		}
		
		System.out.println("Load complete");
	}
	
	/**
	 * Called before exit of program
	 * @throws IOException 
	 */
	public void save() throws IOException {
		// only perform if the file exists
		if(inventoryDir.exists()) {
			// remove all files in the directory before saving new items
			for(File file : FileUtils.getFiles(inventoryDir)) {
				if(!file.isDirectory()) {
					file.delete();
				}
			}
			
			// create a file for every item in system
			for(Item item : inventory) {
				PrintWriter writer = new PrintWriter(inventoryDir + "/" + item.getName().replaceAll("\\s+", "") + "Item.item", "UTF-8");
				writer.println(item.getName());
				writer.println(item.getPrice());
				writer.println(item.getAmount());
				writer.close(); // stop writing to this file
			}
			
			System.out.println("Saves successful");
		} else {
			System.out.println("Error saving");
		}
		
		System.out.println("Save complete");
	}

}
