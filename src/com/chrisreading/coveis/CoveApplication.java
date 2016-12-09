package com.chrisreading.coveis;

import java.io.IOException;

import com.chrisreading.coveis.control.InventoryController;
import com.chrisreading.coveis.control.RootController;
import com.chrisreading.coveis.control.dialog.AboutDialogController;
import com.chrisreading.coveis.control.dialog.AddItemDialogController;
import com.chrisreading.coveis.control.dialog.CartDialogController;
import com.chrisreading.coveis.control.dialog.ConfirmationDialogController;
import com.chrisreading.coveis.control.dialog.ConfirmationDialogController.ButtonsType;
import com.chrisreading.coveis.control.dialog.EditItemDialogController;
import com.chrisreading.coveis.model.Item;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

// 100 COMMITS. This is the most dedicated project I've had.
public class CoveApplication extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private final String CSS = "/res/main.css";
	
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
		scene.getStylesheets().add("/res/main.css");
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
	
	public boolean showConfirmationDialog(String title, String text, ButtonsType type) throws IOException {
		// load the fxml file
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CoveInventorySystem.class.getResource("view/dialog/ConfirmationDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
				
		// create the dialog stage
		Stage dialogStage = new Stage();
		dialogStage.setTitle(title);
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setResizable(false);
		dialogStage.getIcons().add(new Image(CoveInventorySystem.class.getResourceAsStream("/res/icon.png")));
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		scene.getStylesheets().add(CSS);
		dialogStage.setScene(scene);
		
		// set the controller
		ConfirmationDialogController controller = loader.getController();
		controller.setText(text);
		controller.setButtonsType(type);
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
		scene.getStylesheets().add(CSS);
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
		scene.getStylesheets().add(CSS);
		dialogStage.setScene(scene);
		
		// set the controller
		EditItemDialogController controller = loader.getController();
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
		Stage dialogStage= new Stage();
		dialogStage.setTitle("About");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setResizable(false);
		dialogStage.getIcons().add(new Image(CoveInventorySystem.class.getResourceAsStream("/res/icon.png")));
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		scene.getStylesheets().add(CSS);
		dialogStage.setScene(scene);
		
		// set the controller
		AboutDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		
		// show the dialog and wait til the user closes it
		dialogStage.showAndWait();
		
		return controller.isOkClicked();
	}
	
	public boolean showCartDialog(ObservableList<Item> inventory) throws IOException {
		// load the fxml file
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CoveInventorySystem.class.getResource("view/dialog/CartDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
				
		// create the dialog stage
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Cart");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setResizable(false);
		dialogStage.getIcons().add(new Image(CoveInventorySystem.class.getResourceAsStream("/res/icon.png")));
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		scene.getStylesheets().add(CSS);
		dialogStage.setScene(scene);
		
		// set the controller
		CartDialogController controller = loader.getController();
		controller.setInventoryList(inventory);
		controller.setDialogStage(dialogStage);
		
		// show the dialog and wait til the user closes it
		dialogStage.showAndWait();
		
		return controller.isOkClicked();
	}

}
