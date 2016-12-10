package com.chrisreading.coveis.control.dialog;

import com.chrisreading.coveis.control.dialog.ConfirmationDialogController.ButtonsType;
import com.chrisreading.coveis.model.Item;
import com.chrisreading.coveis.util.ValidationUtil;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Edit an existing item in the table
 */
public class EditItemDialogController extends ADialogController {
	
	@FXML
	private TextField nameField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField amountField;
	
	private Item item; // item to be added

	protected void initialize() {
		// make name field first selected (easier input)
		// use runLater because initialize() isn't ready at first
		Platform.runLater(new Runnable() {
			public void run() {
				nameField.requestFocus();	
			
				// fill fields with item info
				nameField.setText(item.getName());
				priceField.setText(Double.toString(item.getPrice()));
				amountField.setText(Integer.toString(item.getAmount()));
				
				// validate fields
				ValidationUtil.validateDecimal(priceField);
				ValidationUtil.validateNumeral(amountField);
			}
		});
	}

	protected void handleOk() {
		if(nameField.getText().isEmpty() || priceField.getText().isEmpty() || amountField.getText().isEmpty()) {
			// show error dialog
			ca.showConfirmationDialog("Field Error", "Make sure all fields are filled in correctly", ButtonsType.OK);
		} else {
			// save new item info
			item.setName(nameField.getText());
			item.setPrice(Double.parseDouble(priceField.getText()));
			item.setAmount(Integer.parseInt(amountField.getText()));
			okClicked = true;
			dialogStage.close();
		}
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
