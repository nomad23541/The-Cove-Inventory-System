package com.chrisreading.coveis.control.dialog;

import com.chrisreading.coveis.model.Item;
import com.chrisreading.coveis.util.FormatUtils;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	@FXML
	private Button btnClear;

	private ObservableList<Item> inventory; // list of all items
	private ObservableMap<Item, Integer> cartList;

	protected void initialize() {
		cartList = FXCollections.observableHashMap();
		
		// load table with loaded items in list
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		amountCol.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
		
		Platform.runLater(new Runnable() {
			public void run() {
				for(Item item : inventory) {
					if(item.getAmount() > 0)
						table.getItems().add(item);
				}
				table.requestFocus();
			}
		});
	}
	
	public void setInventoryList(ObservableList<Item> inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * Add selected item to the list view
	 */
	@FXML
	protected void handleTableClick(MouseEvent e) {
		Item item = table.getSelectionModel().getSelectedItem();
		
		if(item != null) {
			if(!cartList.containsKey(item)) {
				cartList.put(item, 1);
			} else {
				int amount = cartList.get(item);
				if(amount <= item.getAmount() - 1)
					cartList.put(item, amount += 1);
				// else
				// show error screen
			}
		}
		
		// clear list (this refreshes it)
		list.getItems().clear();
		for(Item i : cartList.keySet()) {
			list.getItems().add(i.getName() + " (" + cartList.get(i) + ") " + i.getPrice());
		}
		
		// get subtotal
		double subtotal = 0.0;
		for(Item i : cartList.keySet()) {
			subtotal += i.getPrice() * cartList.get(i);
		}
		
		// finally set subtotal label
		priceLabel.setText(FormatUtils.doubleToPrice(subtotal));
	}
	
	@FXML
	protected void handleClear() {
		list.getItems().clear();
		cartList.clear();
		priceLabel.setText("$0.00");
	}

	protected void handleOk() {
		for(Item item : cartList.keySet()) {
			item.setAmount(item.getAmount() - cartList.get(item));
		}
		okClicked = true;
		dialogStage.close();
	}

	protected void handleCancel() {
		dialogStage.close();
	}

}
