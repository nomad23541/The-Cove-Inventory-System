package com.chrisreading.coveis.control.dialog;

import com.chrisreading.coveis.model.Item;
import com.chrisreading.coveis.util.FormatUtils;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

/**
 * Controller for sell dialog
 */
public class SellController extends ADialogController {
	
	private Item item; // item selling
	
	@FXML
	private Label priceLabel;
	@FXML
	private Spinner<Integer> spinner;

	@Override
	protected void initialize() {
		Platform.runLater(new Runnable() {
			public void run() {
				// set min to 1 and max to total amount
				SpinnerValueFactory<Integer> vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, item.getAmount());
				spinner.setValueFactory(vf);
				spinner.setEditable(false);
			}
		});

	}
	
	@FXML
	private void onScroll() {
		int amount = (Integer) spinner.getValue(); // get amount
		double price = item.getPrice() * amount; // get total price
		priceLabel.setText(FormatUtils.doubleToPrice(price));
	}

	@Override
	protected void handleOk() {
		item.setAmount(item.getAmount() - (Integer) spinner.getValue());
		okClicked = true;
		dialogStage.close();
	}

	@Override
	protected void handleCancel() {
		dialogStage.close();
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
