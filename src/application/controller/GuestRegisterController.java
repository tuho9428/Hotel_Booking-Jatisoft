package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GuestRegisterController extends Application {
	
	private static Connection connection;
	private static String username;
    private static String password;
	static final String QUERY = "SELECT * FROM guestlogin WHERE username=? AND password=?";
	private static final String SQL_INSERT = "INSERT INTO guestlogin(username, password) VALUES(?,?)";
	@FXML
	private Button SignUpButton;
	@FXML
	private TextField user;
	@FXML
	private PasswordField pass;
	@FXML
	private Text line;
	@FXML
	private Button BackButton;

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

	@FXML
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/application/view/GuestRegisterView.fxml"));
		primaryStage.setTitle("JA GUEST REGISTER");
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();
		
		BackButton = (Button) root.lookup("#BackButton");
        BackButton.setOnAction(event -> {
        	handleBackButton(event);
        });
	}
	
	@FXML
	private void handleSignUpButton(ActionEvent event)  throws Exception {
		username = user.getText();
		password = pass.getText();
		
		if(username.isEmpty() || password.isEmpty()) {
			line.setText("Please enter the information above!");
		}
		else {
			ConnectSQL();
		
			Stage primaryStage = (Stage) SignUpButton.getScene().getWindow();
			primaryStage.close();

			Stage adminPageStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/AfterRegisterView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/view/application.css").toExternalForm());
			adminPageStage.setScene(scene);
			adminPageStage.show();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void ConnectSQL() {
		try {
			connection =
			DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "5alasomot");
			System.out.println("Connected With the database successfully");
			// Creating PreparedStatement object
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            // Setting values for Each Parameter
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);;

            int row = preparedStatement.executeUpdate();
            // rows affected
            System.out.println(row); // 1
		} catch (SQLException e) {
			System.out.println("Error while connecting to the database");
			System.out.println(e);
		}
	}
}