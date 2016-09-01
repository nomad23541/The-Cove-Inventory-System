package com.chrisreading.coveis.handler;

import java.util.ArrayList;
import java.util.List;

import com.chrisreading.coveis.model.Item;

/**
 * Manages the inventory, amount, price of an item
 */
public class InventoryManager {
	
	/** Instance of this class */
	public static InventoryManager im;
	
	private List<Item> inventory;
	private List<Item> toRemove;
	
	public InventoryManager() {
		this.inventory = new ArrayList<Item>(); // create empty list before populated
		this.toRemove = new ArrayList<Item>(); // items to be removed (file deleted at save)
		im = this;
	}
	
	/**
	 * Get instance of this class
	 * @return
	 */
	public static InventoryManager getInstance() {
		return im;
	}
	
	public List<Item> getToRemoveList() {
		return toRemove;
	}
	
	public List<Item> getInventoryList() {
		return inventory;
	}
	
	public void addItem(Item item) {
		if(!inventory.isEmpty()) {
			for(Item i : inventory) {
				if(!i.getName().equals(item.getName())) {
					inventory.add(item);
					System.out.println("Added new item: " + item.getName());
				}
			}	
		} else {
			inventory.add(item);
			System.out.println("Added new item: " + item.getName());
		}
	}
	
	public void removeItem(Item item) {
		if(inventory.contains(item)) {
			inventory.remove(item);
			toRemove.add(item);
			System.out.println("Removed item: " + item.getName());
		}
	}
	
	/**
	 * Grab an item from the list by it's name
	 * @param name Name of item
	 * @return The item found
	 */
	public Item getItem(String name) {
		for(Item i : inventory) {
			if(i.getName().replaceAll("\\s+", "") == name.replaceAll("\\s+", "")) {
				return i;
			}
		}
		
		return null;
	}

}
