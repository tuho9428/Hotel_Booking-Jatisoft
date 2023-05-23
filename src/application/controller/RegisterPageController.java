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

public class RegisterPageController extends Application {
	
	@FXML
	private Button BackButton;
	@FXML
	private Button DashBoardButton;
	@FXML
	private void handleBackButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("/application/view/GuestLoginView.fxml"));
	        Scene scene = new Scene(root1);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	        
	        Stage primaryStage = (Stage) BackButton.getScene().getWindow();
            primaryStage.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/view/AfterRegisterView.fxml"));
		primaryStage.setTitle("JA REGISTER");
		primaryStage.setScene(new Scene(root, 600, 500));
		primaryStage.show();
		
		BackButton = (Button) root.lookup("#BackButton");
		BackButton.setOnAction(event -> {
			handleBackButton(event);
        });
	
	}

	public static void main(String[] args) {
		launch(args);
	}
}