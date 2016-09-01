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
	}

	protected void handleOk() {
		item.setName(nameField.getText());
		item.setPrice(Double.parseDouble(priceField.getText()));
		item.setAmount(Integer.parseInt(amountField.getText()));
		
		okClicked = true;
		dialogStage.close();
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	protected void handleCancel() {
		dialogStage.close();
	}
	
	public Item getItem() {
		return item;
	}
	
	

}