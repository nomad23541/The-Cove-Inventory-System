package com.chrisreading.coveis.control.dialog;

import com.chrisreading.coveis.model.Item;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddItemDialogController extends ADialogController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField amountField;
	
	private Item item; // item to be added

	protected void initialize() {
		item = new Item();
	}

	protected void handleOk() {
		item.setName(nameField.getText());
		item.setPrice(item.getPrice());
		item.setAmount(item.getAmount());
		
		okClicked = true;
		dialogStage.close();
	}

	protected void handleCancel() {
		dialogStage.close();
	}
	
	public Item getItem() {
		return item;
	}
	
	

}
