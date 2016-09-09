package com.chrisreading.coveis.control.dialog;

import javafx.fxml.FXML;

public class AboutDialogController extends ADialogController {

	@Override
	protected void initialize() {}
	
	@FXML
	protected void handleOk() {
		dialogStage.close();
	}

	@Override
	protected void handleCancel() {}
}
