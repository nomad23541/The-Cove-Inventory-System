package com.chrisreading.coveis.control.dialog;

import com.chrisreading.coveis.CoveApplication;
import com.chrisreading.coveis.model.Item;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

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
	private ListView<String> list;
	@FXML
	private Label priceLabel;

	private ObservableList<Item> inventory; // list of all items
	private ObservableMap<String, Integer> cartList;

	protected void initialize() {
		cartList = FXCollections.observableHashMap();
		
		// load table with loaded items in list
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		amountCol.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
		
		Platform.runLater(new Runnable() {
			public void run() {
				table.setItems(inventory);
				table.requestFocus();
		        table.getSelectionModel().select(0);
		        table.getFocusModel().focus(0);
			}
		});
	}
	
	public void setInventoryList(ObservableList<Item> inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * Add selected item to the list view
	 * when double clicked.
	 */
	@FXML
	protected void handleTableClick(MouseEvent e) {
		Item item = table.getSelectionModel().getSelectedItem();
		
		if(e.getClickCount() == 2) {
			if(item != null) {
				if(!cartList.containsKey(item.getName())) {
					cartList.put(item.getName(), 1);
					System.out.println("Added " + item.getName() + item.getAmount() + " to hashmap");
				} else {
					int amount = cartList.get(item.getName());
					cartList.put(item.getName(), amount += 1);
					System.out.println("Added " + item.getName() + item.getAmount() + " to hashmap");
				}
			}
		}
	}

	protected void handleOk() {
		okClicked = true;
		dialogStage.close();
	}

	protected void handleCancel() {
		dialogStage.close();
	}

}
