package com.chrisreading.coveis;

import java.io.IOException;

import com.chrisreading.coveis.control.InventoryController;
import com.chrisreading.coveis.control.RootController;
import com.chrisreading.coveis.control.dialog.AboutDialogController;
import com.chrisreading.coveis.control.dialog.AddItemDialogController;
import com.chrisreading.coveis.control.dialog.ConfirmationDialogController;
import com.chrisreading.coveis.control.dialog.EditItemDialogController;
import com.chrisreading.coveis.control.dialog.SellController;
import com.chrisreading.coveis.model.Item;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CoveApplication extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle(Vars.APP_TITLE);
		
		initRootLayout();
		showInventoryOverview();
	}
	
	public void initRootLayout() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CoveInventorySystem.class.getResource("view/RootLayout.fxml"));
		rootLayout = (BorderPane) loader.load();
		
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(CoveInventorySystem.class.getResourceAsStream("/res/icon.png")));
		primaryStage.show();
		
		RootController controller = loader.getController();
		controller.setApplication(this);
	}
	
	public void showInventoryOverview() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CoveInventorySystem.class.getResource("view/InventoryView.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();
		
		// set pane to the center of rootlayout
		rootLayout.setCenter(pane);
		
		InventoryController controller = loader.getController();
		controller.setApplication(this);
	}
	
	public boolean showConfirmationDialog(String text) throws IOException {
		// load the fxml file
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CoveInventorySystem.class.getResource("view/dialog/ConfirmationDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
				
		// create the dialog stage
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Are you sure?");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setResizable(false);
		dialogStage.getIcons().add(new Image(CoveInventorySystem.class.getResourceAsStream("/res/icon.png")));
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		// set the controller
		ConfirmationDialogController controller = loader.getController();
		controller.setText(text);
		controller.setDialogStage(dialogStage);
		
		// show the dialog and wait til the user closes it
		dialogStage.showAndWait();
		
		return controller.isOkClicked();
	}
	
	public boolean showAddDialog(Item item) throws IOException {
		// load the fxml file
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CoveInventorySystem.class.getResource("view/dialog/AddItemDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
				
		// create the dialog stage
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Add Item");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setResizable(false);
		dialogStage.getIcons().add(new Image(CoveInventorySystem.class.getResourceAsStream("/res/icon.png")));
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		// set the controller
		AddItemDialogController controller = loader.getController();
		controller.setItem(item);
		controller.setDialogStage(dialogStage);
		
		// show the dialog and wait til the user closes it
		dialogStage.showAndWait();
		
		return controller.isOkClicked();
	}
	
	public boolean showEditDialog(Item item) throws IOException {
		// load the fxml file
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CoveInventorySystem.class.getResource("view/dialog/EditItemDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
				
		// create the dialog stage
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Edit Item");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setResizable(false);
		dialogStage.getIcons().add(new Image(CoveInventorySystem.class.getResourceAsStream("/res/icon.png")));
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		// set the controller
		EditItemDialogController controller = loader.getController();
		controller.setItem(item);
		controller.setDialogStage(dialogStage);
		
		// show the dialog and wait til the user closes it
		dialogStage.showAndWait();
		
		return controller.isOkClicked();
	}
	
	public boolean showSellDialog(Item item) throws IOException {
		// load the fxml file
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CoveInventorySystem.class.getResource("view/dialog/SellDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
				
		// create the dialog stage
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Sell");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setResizable(false);
		dialogStage.getIcons().add(new Image(CoveInventorySystem.class.getResourceAsStream("/res/icon.png")));
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		// set the controller
		SellController controller = loader.getController();
		controller.setItem(item);
		controller.setDialogStage(dialogStage);
		
		// show the dialog and wait til the user closes it
		dialogStage.showAndWait();
		
		return controller.isOkClicked();
	}
	
	public boolean showAboutDialog() throws IOException {
		// load the fxml file
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CoveInventorySystem.class.getResource("view/dialog/AboutDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
				
		// create the dialog stage
		Stage dialogStage = new Stage();
		dialogStage.setTitle("About");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setResizable(false);
		dialogStage.getIcons().add(new Image(CoveInventorySystem.class.getResourceAsStream("/res/icon.png")));
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		// set the controller
		AboutDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		
		// show the dialog and wait til the user closes it
		dialogStage.showAndWait();
		
		return controller.isOkClicked();
	}

}
