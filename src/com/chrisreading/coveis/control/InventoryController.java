package com.chrisreading.coveis.control;

import java.io.IOException;

import com.chrisreading.coveis.CoveApplication;
import com.chrisreading.coveis.handler.InventoryManager;
import com.chrisreading.coveis.model.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controller class to handle InventoryView.fxml
 */
public class InventoryController {
	
	/** Instance of application class */
	private CoveApplication ca;
	
	/** FXML */
	@FXML
	private TableColumn<Item, String> nameCol;
	@FXML
	private TableColumn<Item, Number> priceCol;
	@FXML
	private TableColumn<Item, Number> amountCol;
	@FXML
	private TableView<Item> table;
	@FXML
	private Button removeButton;
	@FXML
	private Button addButton;
	
	private ObservableList<Item> inventory = FXCollections.observableArrayList(InventoryManager.getInstance().getInventoryList());
	
	public InventoryController() {}

	@FXML
	public void initialize() {
		table.setItems(inventory);
		
		// load table with loaded items in list
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		amountCol.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
	}
	
	@FXML
	private void handleAdd() {
		// show add dialog
		// temp
		InventoryManager.getInstance().addItem(new Item("Hot Cheetos", 1.23, 50));
	}
	
	@FXML
	private void handleRemove() {
		try {
			boolean remove = ca.showConfirmationDialog();
			
			// if ok is clicked, remove the selected item
			if(remove) {
				InventoryManager.getInstance().removeItem(table.getSelectionModel().getSelectedItem());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// remove from list
	}
	
	/**
	 * Make this class access the rest of the gui
	 */
	public void setApplication(CoveApplication ca) {
		this.ca = ca;
	}
	
	
	
}
