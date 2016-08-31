package com.chrisreading.coveis.control.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Confirmation dialog, only return a boolean
 */
public class ConfirmationDialogController extends ADialogController {
	
	@FXML
	private Label text;
	
	public ConfirmationDialogController(String text) {
		this.text.setText(text);
	}

	@Override
	protected void initialize() {}

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
