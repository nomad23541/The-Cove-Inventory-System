package com.chrisreading.coveis.control;

import com.chrisreading.coveis.handler.InventoryManager;
import com.chrisreading.coveis.model.Item;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controller class to handle InventoryView.fxml
 */
public class InventoryController {
	
	/** FXML */
	@FXML
	private TableColumn<Item, String> nameCol;
	@FXML
	private TableColumn<Item, Number> priceCol;
	@FXML
	private TableColumn<Item, Number> amountCol;
	@FXML
	private TableView<Item> table;
	
	public InventoryController() {
		
	}

	@FXML
	public void initialize() {
		table.setItems((ObservableList<Item>) InventoryManager.getInstance().getInventoryList());
		// load table with loaded items in list
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		amountCol.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
	}
	
}
