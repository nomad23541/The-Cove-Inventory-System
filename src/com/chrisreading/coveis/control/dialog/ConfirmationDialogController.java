package com.chrisreading.coveis.control.dialog;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Confirmation dialog, only return a boolean
 */
public class ConfirmationDialogController extends ADialogController {
	
	@FXML
	private Label text;
	@FXML
	private Button okButton;
	@FXML
	private ImageView image;
	
	public static enum ButtonsType {
		OK_CANCEL,
		OK
	}
	
	public static enum DialogType {
		WARNING,
		ERROR,
		BLANK
	}
	
	public ConfirmationDialogController() {
	}

	@Override
	protected void initialize() {
		Platform.runLater(new Runnable() {
			public void run() {
				okButton.requestFocus();
			}
		});
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
	
	public void setText(String text) {
		this.text.setText(text);
	}
	
	public void setButtonsType(ButtonsType type) {
		switch(type) {
		case OK_CANCEL:
			break;
		case OK:
			cancelButton.setText("OK");
			okButton.setVisible(false);
			break;
		
		}
	}
	
	public void setDialogType(DialogType type) {
		switch(type) {
		case BLANK:
			break;
		case WARNING:
			image.setImage(new Image("/res/warning.png"));
			break;
		case ERROR:
			image.setImage(new Image("/res/error.png"));
			break;
		
		}
	}

}
