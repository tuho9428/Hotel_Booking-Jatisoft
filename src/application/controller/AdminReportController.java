package application.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.model.Guest;

import java.sql.Date;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminReportController extends Application {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "5alasomot";
	private static final String QUERY6 = "SELECT * FROM guest";

    @FXML
    private TableView<Booking> bookingTableView;
    @FXML
    private TableColumn<Booking, String> guestNameColumn;
    @FXML
    private TableColumn<Booking, String> guestIdColumn;
    @FXML
    private TableColumn<Booking, Integer> bookingIdColumn;
    @FXML
    private TableColumn<Booking, Integer> adultsColumn;
    @FXML
    private TableColumn<Booking, Integer> childrenColumn;
    @FXML
    private TableColumn<Booking, Date> checkInDateColumn;
    @FXML
    private TableColumn<Booking, Date> checkOutDateColumn;
    @FXML
    private TableColumn<Booking, Integer> roomNumberColumn;
    @FXML
    private TableColumn<Booking, Integer> bookingNumberColumn;
    @FXML 
    private Button OutButton;
	@FXML
	private void handleSignOutButton(ActionEvent event) {

	    Stage primaryStage = (Stage) OutButton.getScene().getWindow();
		primaryStage.close();
	}

    public static void main(String[] args) throws SQLException {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/view/AdminReportView.fxml"));
            Scene scene = new Scene(root, 900, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Booking Viewer");
            primaryStage.show();
            
    		OutButton = (Button) root.lookup("#OutButton");
    		OutButton.setOnAction(event -> {
    			handleSignOutButton(event);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
    	
        guestIdColumn.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        bookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        adultsColumn.setCellValueFactory(new PropertyValueFactory<>("adults"));
        childrenColumn.setCellValueFactory(new PropertyValueFactory<>("children"));
        checkInDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        checkOutDateColumn.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        bookingNumberColumn.setCellValueFactory(new PropertyValueFactory<>("bookingNumber"));
        guestNameColumn.setCellValueFactory(new PropertyValueFactory<>("guestName"));

        try {
            // Establish the database connection
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Create a SQL statement
            Statement statement = connection.createStatement();
            // Create the SQL query
            String query = "SELECT * FROM booking";
            // Execute the query
            ResultSet resultSet = statement.executeQuery(query); 
            // Create a list to store the bookings
            List<Booking> bookings = new ArrayList<>();

            // Process the result set and populate the list
            while (resultSet.next()) {
            	//String guestName = resultSet.getString("fname");
                String guestId = resultSet.getString("guest_id");
                int bookingId = resultSet.getInt("booking_id");
                int adults = resultSet.getInt("aldutNum");
                int children = resultSet.getInt("childNum");
                Date checkInDate = resultSet.getDate("checkindate");
                Date checkOutDate = resultSet.getDate("checkoutdate");
                int roomNumber = resultSet.getInt("room_num");
                int bookingNumber = resultSet.getInt("booking_num");
                Guest guest1 = getGuestInfo(retrieveData(QUERY6,bookingId));
                String  fname = guest1.getFirstName();
                String lname = guest1.getLastName(); 
                String guestName = fname +" " + lname;

                Booking booking = new Booking( guestId, bookingId, adults, children, checkInDate, checkOutDate,
                        roomNumber, bookingNumber, guestName);
                bookings.add(booking);
            }
            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
            // Add the bookings to the table view
            bookingTableView.getItems().addAll(bookings);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //
	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	}

    public static ResultSet retrieveData(String line, int bookingId) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = line + " WHERE booking_id = " + bookingId;
        return statement.executeQuery(query);
    }
    public static Guest getGuestInfo(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String pfx = resultSet.getString("pf");
            String fname = resultSet.getString("fname");
            String mname = resultSet.getString("mname");
            if (mname == null) {
            	mname = "";
            }
            String lname = resultSet.getString("lname");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            int id = resultSet.getInt("booking_id");
            Guest guest = new Guest(pfx, fname, mname, lname, phone, email);
            return guest;
        }
        return null;
    }

    // Define the Booking class with appropriate getters and setters
    public static class Booking {
    	
        private String guestId;
        private int bookingId;
        private int adults;
        private int children;
        private Date checkInDate;
        private Date checkOutDate;
        private int roomNumber;
        private int bookingNumber;
        private String guestName;

        public Booking(String guestId, int bookingId, int adults, int children, Date checkInDate, Date checkOutDate,
                int roomNumber, int bookingNumber, String guestName) {
        	
            this.guestId = guestId;
            this.bookingId = bookingId;
            this.adults = adults;
            this.children = children;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            this.roomNumber = roomNumber;
            this.bookingNumber = bookingNumber;
            this.guestName = guestName;
        }

        public String getGuestId() {
            return guestId;
        }

        public void setGuestId(String guestId) {
            this.guestId = guestId;
        }

        public int getBookingId() {
            return bookingId;
        }

        public void setBookingId(int bookingId) {
            this.bookingId = bookingId;
        }

        public int getAdults() {
            return adults;
        }

        public void setAdults(int adults) {
            this.adults = adults;
        }

        public int getChildren() {
            return children;
        }

        public void setChildren(int children) {
            this.children = children;
        }

        public Date getCheckInDate() {
            return checkInDate;
        }

        public void setCheckInDate(Date checkInDate) {
            this.checkInDate = checkInDate;
        }

        public Date getCheckOutDate() {
            return checkOutDate;
        }

        public void setCheckOutDate(Date checkOutDate) {
            this.checkOutDate = checkOutDate;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
        }

        public int getBookingNumber() {
            return bookingNumber;
        }

        public void setBookingNumber(int bookingNumber) {
            this.bookingNumber = bookingNumber;
        }

		public String getGuestName() {
			return guestName;
		}

		public void setGuestName(String guestName) {
			this.guestName = guestName;
		}
    }
}
