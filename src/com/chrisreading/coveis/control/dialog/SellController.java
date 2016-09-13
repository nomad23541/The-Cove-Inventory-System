package com.chrisreading.coveis.control.dialog;

import com.chrisreading.coveis.model.Item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

/**
 * Controller for sell dialog
 */
public class SellController extends ADialogController {
	
	private Item item; // item selling
	
	private int amount; // amount to sell
	private double total; // total price
	
	@FXML
	private Label priceLabel;
	@FXML
	private Spinner spinner;

	@Override
	protected void initialize() {
		spinner
	}
	
	private void onScroll() {
		
	}

	@Override
	protected void handleOk() {
		okClicked = true;
		dialogStage.close();
	}

	@Override
	protected void handleCancel() {
		dialogStage.close();
	}

}
