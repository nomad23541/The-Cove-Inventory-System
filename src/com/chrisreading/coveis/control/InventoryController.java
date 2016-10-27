package com.chrisreading.coveis.control;

import java.io.IOException;

import com.chrisreading.coveis.CoveApplication;
import com.chrisreading.coveis.handler.InventoryManager;
import com.chrisreading.coveis.model.Item;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
	@FXML
	private Button editButton;
	@FXML
	private Label nameDetail;
	@FXML
	private Label priceDetail;
	@FXML
	private Label amountDetail;
	
	private ObservableList<Item> inventory;
	
	public InventoryController() {}
	
	/**
	 * Refresh the list by setting the
	 * observable list to the inventory manager's
	 * list and then set the table items
	 */
	public void refreshTable() {
		inventory = FXCollections.observableArrayList(InventoryManager.getInstance().getInventoryList());
		table.setItems(inventory);
	}
	
	/** 
	 * Refresh that grid pane
	 * that displays the item information
	 */
	public void refreshGrid() {
        // set gridpane details on first selected item
        Item item = table.getSelectionModel().getSelectedItem();
        if(item != null) {
			nameDetail.setText(item.getName());
			priceDetail.setText(Double.toString(item.getPrice()));
			amountDetail.setText(Integer.toString(item.getAmount()));	
        }
	}

	@FXML
	public void initialize() {
		refreshTable();
		
		// load table with loaded items in list
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		amountCol.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
		
		// select first row if there is data
		Platform.runLater(new Runnable() {
			public void run() {
				table.requestFocus();
		        table.getSelectionModel().select(0);
		        table.getFocusModel().focus(0);
		        
		        refreshGrid();
			}
		});
	}
	
	@FXML
	private void handleSell() {
		Item item = table.getSelectionModel().getSelectedItem();
		
		if(item.getAmount() > 0) {
			try {
				boolean sell = ca.showSellDialog(item);
				if(sell) {
					refreshTable();
					refreshGrid();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		} else {
			try {
				ca.showConfirmationDialog("Error", "There aren't any to sell!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void handleAdd() {
		Item item = new Item();
		try {
			boolean add = ca.showAddDialog(item);
			if(add) {
				InventoryManager.getInstance().addItem(item);
				refreshTable();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleRemove() {
		Item item = table.getSelectionModel().getSelectedItem();
		
		try {
			boolean remove = ca.showConfirmationDialog("Confirmation", "Are you sure you want to remove " + item.getName() + "?");
			
			// if ok is clicked, remove the selected item
			if(remove) {
				InventoryManager.getInstance().removeItem(item);
				refreshTable();
				refreshGrid();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Show edit dialog
	 */
	@FXML
	private void handleEdit() {
		Item item = table.getSelectionModel().getSelectedItem();
		try {
			boolean done = ca.showEditDialog(item);
			if(done) {
				refreshTable();
				refreshGrid();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Bring up edit dialog if double clicked
	 * @param e
	 */
	@FXML
	private void onTableClick(MouseEvent e) {
		Item item = table.getSelectionModel().getSelectedItem();	
		
		// if double clicked
		if(e.getClickCount() == 2) {
			try {
				boolean done = ca.showEditDialog(item);
				if(done)
					refreshTable();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		// show details
		nameDetail.setText(item.getName());
		priceDetail.setText(Double.toString(item.getPrice()));
		amountDetail.setText(Integer.toString(item.getAmount()));	
	}
	
	/**
	 * delete selected row if delete key is pressed
	 * @param e
	 */
	@FXML
	private void onTableKeyPress(KeyEvent e) {
		try {
			boolean remove = ca.showConfirmationDialog("Confirmation", "Remove item?");
			
			// if ok is clicked, remove the selected item
			if(remove) {
				InventoryManager.getInstance().removeItem(table.getSelectionModel().getSelectedItem());
				refreshTable();
			}
		} catch (IOException ei) {
			ei.printStackTrace();
		}
	}
	
	/**
	 * Make this class access the rest of the gui
	 */
	public void setApplication(CoveApplication ca) {
		this.ca = ca;
	}
	
}
