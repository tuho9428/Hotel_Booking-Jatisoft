package application.controller;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminPageController extends Application {
	
	@FXML
	private Button SignOutButton;
    @FXML
    private Text userInfo;
    
	public static String loggedInAd;
   
    public void setLoggedInAd(String username) {
        AdminPageController.loggedInAd = username;
        // Update any UI elements with the logged-in username if needed
    }
	
    public void setAdInfo(String username, String guestId) {
        userInfo.setText("Welcome, " + username + " (Admin ID: " + guestId + ")");
    }
    
	@FXML
	private void seeReportButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("/application/view/AdminReportView.fxml"));
	        Scene scene = new Scene(root1);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@FXML
	private void handleSignOutButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("/application/view/RunApplicationView.fxml"));
	        Scene scene = new Scene(root1);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	        
	        Stage primaryStage = (Stage) SignOutButton.getScene().getWindow();
            primaryStage.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/view/AdminPageView.fxml"));
		primaryStage.setTitle("JA ADMIN PAGE");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
		
		SignOutButton = (Button) root.lookup("#SignOutButton");
		SignOutButton.setOnAction(event -> {
			handleSignOutButton(event);
        });
	}

	public static void main(String[] args) {
		launch(args);
	}
}
  

