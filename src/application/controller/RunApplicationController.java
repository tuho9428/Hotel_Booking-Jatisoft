package application.controller;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RunApplicationController extends Application {
	
	@FXML
	private Button GuestButton;
	@FXML
	private Button AdminButton;

	@FXML
	private void handleGuestButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("/application/view/GuestLoginView.fxml"));
	        Scene scene = new Scene(root1);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	        
	        Stage primaryStage = (Stage) GuestButton.getScene().getWindow();
            primaryStage.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	private void handleAdminButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("/application/view/AdminLoginView.fxml"));
	        Scene scene = new Scene(root1);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	        // cmt
	        Stage primaryStage = (Stage) AdminButton.getScene().getWindow();
            primaryStage.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/view/RunApplicationView.fxml"));
		primaryStage.setTitle("JATISOFT BOOKING");
		primaryStage.setScene(new Scene(root, 600, 250));
		primaryStage.show();
		
		GuestButton = (Button) root.lookup("#GuestButton");
		GuestButton.setOnAction(event -> {
			handleGuestButton(event);
        });
        
		AdminButton = (Button) root.lookup("#AdminButton");
		AdminButton.setOnAction(event -> {
			handleAdminButton(event);
        });
	}

	public static void main(String[] args) {
		launch(args);
	}
}