package com.chrisreading.coveis.control.dialog;

import com.chrisreading.coveis.CoveApplication;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Used for all dialogs used in app
 */
public abstract class ADialogController {
	
	/** Reference to application class */
	private CoveApplication ca;
	
	/** Stage for this dialog */
	protected Stage dialogStage;
	
	protected boolean okClicked = false;
	
	/** FXML */
	@FXML
	protected Button okButton;
	@FXML
	protected Button cancelButton;
	
	/**
	 * Called before constructor
	 */
	@FXML
	protected abstract void initialize();

	@FXML
	protected abstract void handleOk();
	
	@FXML
	protected abstract void handleCancel();
	
	public void setApplication(CoveApplication ca) {
		this.ca = ca;
	}
	
	public void setDialogStage(Stage stage) {
		this.dialogStage = stage;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
}
