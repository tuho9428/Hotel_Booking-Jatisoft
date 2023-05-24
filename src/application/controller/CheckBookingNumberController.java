package application.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CheckBookingNumberController extends Application {

	@FXML
    private TextField bookingRoomNumberTextField;
    @FXML
    private Label resultLabel;
    @FXML 
    private Button OutButton;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "5alasomot";

    @FXML
    public void retrieveData() {
        String bookingRoomNumberText = bookingRoomNumberTextField.getText();
        // Check if the input is empty
        if (bookingRoomNumberText.isEmpty()) {
            resultLabel.setText("Please enter a booking room number.");
            return;
        }
        // Check if the input is numeric
        if (!bookingRoomNumberText.matches("\\d+")) {
            resultLabel.setText("Please enter a numeric value for the booking room number.");
            return;
        }
        int bookingRoomNumber = Integer.parseInt(bookingRoomNumberTextField.getText());
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM booking WHERE booking_num = " + bookingRoomNumber;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int bookingId = resultSet.getInt("booking_id");
                int roomId = resultSet.getInt("room_num");
                int aldutNum = resultSet.getInt("aldutNum");
                int childNum = resultSet.getInt("childNum");
                String checkInDate = resultSet.getString("checkindate");
                String checkOutDate = resultSet.getString("checkoutdate");

                resultLabel.setText("Booking ID: " + bookingId + "\n"
                		+ "Room Number: " + roomId + "\n"
                        + "Adults: " + aldutNum + "\n"
                        + "Children: " + childNum + "\n"
                        + "Check-in Date: " + checkInDate + "\n"
                        + "Check-out Date: " + checkOutDate);
            } else {
                resultLabel.setText("No data found for booking number: " + bookingRoomNumber);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            resultLabel.setText("Error retrieving data from database.");
        }
    }
    
	@FXML
	private void handleSignOutButton(ActionEvent event) {

	    Stage primaryStage = (Stage) OutButton.getScene().getWindow();
		primaryStage.close();
	}
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/application/view/CheckBookingNumberView.fxml"));
        primaryStage.setTitle("Booking Data");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        
		OutButton = (Button) root.lookup("#OutButton");
		OutButton.setOnAction(event -> {
			handleSignOutButton(event);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
