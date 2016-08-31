package com.chrisreading.coveis.util;

import java.util.List;

import com.chrisreading.coveis.model.Item;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Utilities for various nodes
 */
public class NodeUtils {
	
	public static void refreshTable(TableView<Item> table, ObservableList<Item> list, List<TableColumn<Item, String>> columnsString, List<TableColumn<Item, Number>> columnsNumber) {
		table.getItems().clear();
		table.setItems(list);
		
		// columns with string values
		for(TableColumn<Item, String> col : columnsString) {
			if(col.getText().equals("Name"))
				col.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		}
		
		// for columns with number values
		for(TableColumn<Item, Number> col : columnsNumber) {
			if(col.getText().equals("Price"))
				col.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
			else if(col.getText().equals("Amount in Stock"))
				col.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
		}
		
		table.getColumns().get(0).setVisible(false);
		table.getColumns().get(0).setVisible(true);
	}

}
