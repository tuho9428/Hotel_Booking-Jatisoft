package application.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CancelBookingController extends Application {

    @FXML
    private TextField bookingNumberField;
    @FXML
    private Label resultLabel;
    @FXML 
    private Button ExitButton;
    @FXML
    private Button cancelButton;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123465";

    @FXML
    private void cancelBooking(ActionEvent event) {
        String bookingNumber = bookingNumberField.getText();
        if (bookingNumber.isEmpty()) {
        	resultLabel.setText("Please enter a booking number.");
            return;
        }
        if (!bookingNumber.matches("\\d+")) {
            resultLabel.setText("Please enter a numeric value for the booking room number.");
            return;
        }
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM booking WHERE booking_num = ?")) {
            statement.setString(1, bookingNumber);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                resultLabel.setText("Booking with number: " + bookingNumber + " canceled successfully.");
            } else {
                resultLabel.setText("Failed to cancel booking. The booking number may not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	@FXML
	private void handleExitButton(ActionEvent event) {

	    Stage primaryStage = (Stage) ExitButton.getScene().getWindow();
		primaryStage.close();
	}

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/application/view/CancelBookingView.fxml"));
        primaryStage.setTitle("Cancel Booking");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        
        ExitButton = (Button) root.lookup("#ExitButton");
		ExitButton.setOnAction(event -> {
			handleExitButton(event);
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
