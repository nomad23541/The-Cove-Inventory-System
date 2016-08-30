package com.chrisreading.coveis;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
		loader.setLocation(CoveInventorySystem.class.getResource("view//RootLayout.fxml"));
		rootLayout = (BorderPane) loader.load();
		
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image(CoveInventorySystem.class.getResourceAsStream("/res/icon.png")));
		primaryStage.show();
	}
	
	public void showInventoryOverview() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(CoveInventorySystem.class.getResource("view//InventoryView.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();
		
		// set pane to the center of rootlayout
		rootLayout.setCenter(pane);
		
		// TODO: add controller
	}

}
