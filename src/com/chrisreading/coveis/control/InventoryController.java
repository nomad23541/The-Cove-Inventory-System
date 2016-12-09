package com.chrisreading.coveis.control;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.chrisreading.coveis.CoveApplication;
import com.chrisreading.coveis.control.dialog.ConfirmationDialogController.ButtonsType;
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
import javafx.scene.layout.GridPane;

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
	private Button cartButton;
	@FXML
	private Label nameDetail;
	@FXML
	private Label priceDetail;
	@FXML
	private Label amountDetail;
	@FXML
	private Label dateDetail;
	@FXML
	private Label timeDetail;
	@FXML
	private Label subtotalDetail;
	@FXML
	private GridPane gridPane;
	
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
		
		Platform.runLater(new Runnable() {
			public void run() {
				for(Item item : inventory) {
					if(item.getAmount() <= 5) {
						try {
							ca.showConfirmationDialog("Quantity Warning", "There's " + item.getAmount() + " " + item.getName() + " left!", ButtonsType.OK);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}	
			}
		});
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
			gridPane.setVisible(true);
        } else {
        	gridPane.setVisible(false);
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
		
		// update time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        	@Override
        	public void run() {
        		Platform.runLater(new Runnable() {
        			public void run() {
        				try {
            		        DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
            		        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

            		        Date date = new Date();
            		        
            		        dateDetail.textProperty().set(dateFormat.format(date));
            		        timeDetail.textProperty().set(timeFormat.format(date));		
        				} finally {
        					timer.cancel(); // close to ensure exit of program
        				}
        			}
        		});
        	}
        }, 0, 2000);
	}
	
	@FXML
	private void onCreateCart() {
		try {
			boolean done = ca.showCartDialog(inventory);
			if(done) {
				refreshGrid();
				refreshTable();
			}
		} catch (IOException e) {
			e.printStackTrace();
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
			boolean remove = ca.showConfirmationDialog("Confirmation", "Are you sure you want to remove " + item.getName() + "?", ButtonsType.OK_CANCEL);
			
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
				if(item != null) {
					boolean done = ca.showEditDialog(item);
					if(done) {
						refreshTable();
					}	
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		refreshGrid();
	}
	
	/**
	 * delete selected row if delete key is pressed
	 * @param e
	 */
	@FXML
	private void onTableKeyPress(KeyEvent e) {
		Item item = table.getSelectionModel().getSelectedItem();
		
		try {
			boolean remove = ca.showConfirmationDialog("Confirmation", "Are you sure you want to remove " + item.getName() + "?", ButtonsType.OK_CANCEL);
			
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
