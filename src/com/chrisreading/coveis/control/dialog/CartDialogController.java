package com.chrisreading.coveis.control.dialog;

import com.chrisreading.coveis.model.Item;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CartDialogController extends ADialogController {
	
	@FXML
	private TableView<Item> table;
	@FXML
	private TableColumn<Item, String> nameCol;
	@FXML
	private TableColumn<Item, Number> priceCol;
	@FXML
	private TableColumn<Item, Number> amountCol;
	@FXML
	private ListView<Item> list;
	@FXML
	private Label priceLabel;
	
	private ObservableList<Item> inventory; // list of all items

	protected void initialize() {
		table.setItems(inventory);
		
		// load table with loaded items in list
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		amountCol.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
		
		Platform.runLater(new Runnable() {
			public void run() {
				table.requestFocus();
		        table.getSelectionModel().select(0);
		        table.getFocusModel().focus(0);	
			}
		});
	}
	
	public void setInventoryList(ObservableList<Item> inventory) {
		this.inventory = inventory;
	}

	protected void handleOk() {
		
		okClicked = true;
		dialogStage.close();
	}

	protected void handleCancel() {
		dialogStage.close();
	}

}
