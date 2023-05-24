package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import application.model.Address;
import application.model.BookingSystem;
import application.model.Guest;
import application.model.Room;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class ApplicaitionTabsController extends Application {
	
	private static Connection connection;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "5alasomot";
	static final String QUERY = "SELECT * FROM guestlogin WHERE username=? AND password=?";
	static final String QUERY1 = "SELECT * FROM guestlogin WHERE username=? AND guest_id=?";
	private static final String SQL_INSERT = "INSERT INTO booking(aldutNum, childNum, checkindate, checkoutdate,guest_id) VALUES(?,?,?,?,?)";
	static final String QUERY2 = "SELECT * FROM booking";
	static final String QUERY3 = "SELECT * FROM room";
    private static int adultNum;
    private static int childNum;
    private static LocalDate checkIn;
    private static LocalDate checkOut;
    private static Guest guest1;
	@FXML
	private Button SignInButton;
	@FXML
	private Button RegisterButton;
	@FXML
	private TextField user;
	@FXML
	private PasswordField pass;
	@FXML
	private Button BackButton;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab3;
    @FXML
    private Tab tab4;
    @FXML
    private Tab tab5;
    @FXML
    private Tab tab6;
    @FXML
    private Tab tab7;
    @FXML
    private Tab tab8;
    @FXML
    private TabPane tp;
	@FXML
	private Button SignOutButton;
	@FXML
	private Button DashBoardButton;
    @FXML
    private Text userInfo;
	@FXML
	private Button BackButton1;
	@FXML
	private ChoiceBox<String> adultNumBox;
	@FXML
	private ChoiceBox<String> childNumBox;
    @FXML
    private DatePicker checkInDate;
    @FXML
    private DatePicker checkOutDate;
    @FXML
    private Text details;
	@FXML
	private VBox roomContainer;
	@FXML
	private Button BackButton2;
	private static Guest guest;
	private static Address address;
	private static final String SQL_INSERT1 = "INSERT INTO guest(pf, fname, mname, lname, phone, email, booking_id) VALUES(?,?,?,?,?,?,?)";
	private static final String SQL_INSERT2 = "INSERT INTO address(country, ad1, ad2, cty, zipcode, detail, booking_id) VALUES(?,?,?,?,?,?,?)";
	@FXML
	private TextField prfix;
	@FXML
	private TextField fname;
	@FXML
	private TextField mname;
	@FXML
	private TextField lname;
	@FXML
	private TextField phone;
	@FXML
	private TextField email;
	@FXML
	private TextField country;
	@FXML
	private TextField adress1;
	@FXML
	private TextField address2;
	@FXML
	private TextField city;
	@FXML
	private TextField zip;
	@FXML
	private TextArea detail;
	@FXML
	private Button InfoButton;
	@FXML
	private Text entryLine;
	static final String QUERY6 = "SELECT * FROM guest";
	static final String QUERY7 = "SELECT * FROM address";
	static final String QUERY8 = "SELECT * FROM booking";
	@FXML
	private Button FinishButton;
	@FXML
    private Text dates;
    @FXML
    private Text guestInfo;
    @FXML
    private Text peopleNum;
    @FXML
    private Text roomInfo;
    @FXML
    private Text addressInfo;
    @FXML
    private Text totalPrice;
	@FXML
	private Button BackButton3;
	@FXML 
	private Label bookingNum;
    @FXML
	private Button handleCheckButton;
    @FXML
	private Button handleBackButtonP3;
    @FXML
 	private Button handleBackButtonP5;
    @FXML
 	private Button handleBackButtonP6;
    private static int bookingID = getBookingID();
    
    private static String loggedInUser; // Variable to store the logged-in username
	
    //cencal
	// handleCheckButton
	@FXML 
	private void handleCancelButton(ActionEvent event) {
		//tab3.setDisable(true);
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("/application/view/CancelBookingView.fxml"));
	        Scene scene = new Scene(root1,600,600);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    	//tp.getSelectionModel().select(tab2);
	}
    
    //tab2
    public void setLoggedInUser(String username) {
        ApplicaitionTabsController.loggedInUser = username;
        // Update any UI elements with the logged-in username if needed
    }
    //handleBackButtonP6
	@FXML 
	private void handleBackButtonP6(ActionEvent event) {
    	tp.getSelectionModel().select(tab5);
	}
    //handleBackButtonP5
	@FXML 
	private void handleBackButtonP5(ActionEvent event) {
    	tp.getSelectionModel().select(tab5);
	}
    //handleBackButtonP3
	@FXML 
	private void handleBackButtonP3(ActionEvent event) {
    	tp.getSelectionModel().select(tab2);
	}
	// handleCheckButton
	@FXML 
	private void handleCheckButton(ActionEvent event) {
		//tab3.setDisable(true);
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("/application/view/CheckBookingNumberView.fxml"));
	        Scene scene = new Scene(root1,600,600);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    	//tp.getSelectionModel().select(tab2);
	}
    //tab4
	public static int getBookingID() {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int bookingId = 0;
	    try {
	        conn = getConnection();
	        pstmt = conn.prepareStatement("SELECT MAX(booking_id) FROM booking");
	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            bookingId = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (pstmt != null) {
	                pstmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return bookingId;
	}
	@FXML
	private void initialize() throws SQLException {
		//tab4
	    // Configure date picker converters
        StringConverter<LocalDate> converter = new LocalDateStringConverter();
        checkInDate.setConverter(converter);
        checkOutDate.setConverter(converter);
        
        checkInDate.setDayCellFactory(getDayCellFactory(LocalDate.now()));
        checkOutDate.setDayCellFactory(getDayCellFactory(LocalDate.now()));
        // Add change listener to the first date picker
        checkInDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Set the minimum date of the second date picker
        	checkOutDate.setDayCellFactory(getDayCellFactory(newValue.plusDays(1)));
        });
	    ObservableList<String> adultNumBoxList = FXCollections.observableArrayList("0", "1", "2", "3", "4");
	    adultNumBox.setValue("0");
	    adultNumBox.setItems(adultNumBoxList);
	    //adultNumBoxList.add("5");
	    ObservableList<String> childNumBoxList = FXCollections.observableArrayList("0", "1", "2", "3", "4");
	    childNumBox.setValue("0");
	    childNumBox.setItems(childNumBoxList);
	    //adultNumBoxList.add("5");   
	}
	// is room Available
    public boolean isRoomAvailable(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        //
    	try {
    	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "5alasomot");
    	    // Assign the connection to the appropriate field or variable in your class
    	    ApplicaitionTabsController.connection = connection;
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}

    	int roomNumber = room.getRoomNumber();
        String query = "SELECT COUNT(*) FROM booking WHERE room_num = ? " +
                       "AND checkoutdate > ? AND checkindate < ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roomNumber);
            statement.setDate(2, java.sql.Date.valueOf(checkInDate));
            statement.setDate(3, java.sql.Date.valueOf(checkOutDate));

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            return count == 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // In case of an exception, consider the room as unavailable
    }
	//tab5
	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	}
	
	public static ResultSet retrieveData(String line) throws SQLException {
	    Connection connection = getConnection();
	    Statement statement = connection.createStatement();
	    return statement.executeQuery(line);
	}
	
	public static ObservableList<Room> getRoom(ResultSet resultSet) throws SQLException {
	        ObservableList<Room> roomList = FXCollections.observableArrayList();
	
	        while (resultSet.next()) {
	            int roomID = resultSet.getInt("roomno");
	            String roomType = resultSet.getString("roomtype");
	            int price = resultSet.getInt("price");
	            int isAvailable = resultSet.getInt("isavailable");
	
	            Room room = new Room(roomID, roomType, price, isAvailable);
	            roomList.add(room);
	        }
	
	        return roomList;
	}
	
	public static int getNumGuest(ResultSet resultSet) throws SQLException {
		int total = 0;
		while (resultSet.next()) {
	        int adult = resultSet.getInt("aldutNum");
	        int child = resultSet.getInt("childNum");
	        total = adult + child;
		}
		return total;
	}

	@FXML
	private void handleBackButton2(ActionEvent event) {
		tp.getSelectionModel().select(tab4);
	}
	//tab4
	@FXML
    public void handleButtonAction() throws SQLException {
		
		adultNum = Integer.parseInt(adultNumBox.getValue());
		childNum = Integer.parseInt(childNumBox.getValue());
		checkIn = checkInDate.getValue();
		checkOut = checkOutDate.getValue();
		
		if(adultNumBox != null && childNumBox != null && checkIn != null && checkOut != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
			String checkInformattedDate = checkIn.format(formatter);
			String checkOutformattedDate = checkOut.format(formatter);
		
			System.out.println(adultNum);
			System.out.println(childNum);
			System.out.println(checkInformattedDate);
			System.out.println(checkOutformattedDate);
			ConnectSQL();
		
			//tab5
	        List<Room> availableRooms = new ArrayList<>();
	        availableRooms = getRoom(retrieveData(QUERY3));
	        int guestNum = getNumGuest(retrieveData(QUERY2)); 
	        List<Room> filterRoom = new ArrayList<>();
	       
	        for (Room room : availableRooms) {
	            if (guestNum <= 2 && room.getRoomType().equals("single")) {
	            	filterRoom.add(room);
	            } else if (guestNum >= 3 && guestNum <= 4 && room.getRoomType().equals("double")) {
	            	filterRoom.add(room);
	            } else if (guestNum >= 5 && guestNum <= 6 && room.getRoomType().equals("triple")) {
	            	filterRoom.add(room);
	            } else if (guestNum >= 7 && guestNum <= 8 && room.getRoomType().equals("quad")) {
	            	filterRoom.add(room);
	            }
	        }
	        for (Room room : filterRoom) {
	        	if (isRoomAvailable(room, checkIn, checkOut)) {
	                Label roomLabel = new Label("Number: " + room.getRoomNumber() + 
	                							"\nType: " + room.getRoomType() + 
	                							"\nPrice: " + room.getPrice());
	                roomLabel.setStyle("-fx-font-size: 12px; -fx-spacing: 14px;");
	                Button bookButton = new Button("Book");
	                bookButton.setStyle("-fx-font-size: 12px; -fx-spacing: 14px;");
	                bookButton.setOnAction(e -> {
	                    // Handle button action here
	                	tab6.setDisable(false);
	        	    	tp.getSelectionModel().select(tab6);
	            	    int roomID = room.getRoomNumber();
	            	    Connection connection = null;
	    				try {
	    					connection = getConnection();
	    				} catch (SQLException e2) {
	    					// TODO Auto-generated catch block
	    					e2.printStackTrace();
	    				}
	            	    String query = "UPDATE room SET isavailable = ? WHERE roomno = ?";
	                    try (PreparedStatement statement = connection.prepareStatement(query)) {
	                        statement.setInt(1, 0);
	                        statement.setInt(2, roomID);
	                        statement.executeUpdate();
	                    } catch (SQLException e1) {
	    					// TODO Auto-generated catch block
	    					e1.printStackTrace();
	    				}
	                    String SQL_UPDATE = "UPDATE booking SET room_num = ? WHERE booking_id = ?";

	                    try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
	                    	int booking_id = getBookingID();
	                        statement.setInt(1, roomID);
	                        statement.setInt(2, booking_id);
	                        statement.executeUpdate();
	                    } catch (SQLException e1) {
	                        e1.printStackTrace();
	                    }
	                    int booking_id = getBookingID();
	    				System.out.println("Room number " + roomID + " booked for guest with ID " + loggedInUser 
	    						+ " and booking ID: " + booking_id );
	                });
	                HBox roomBox = new HBox(roomLabel, bookButton);
	                roomBox.setSpacing(10); // Set the spacing between the nodes
                    roomBox.setPadding(new Insets(10)); // Set padding around the container
	                roomContainer.getChildren().add(roomBox);
	            }	
	        }
	        // Check if any room options are available
	        if (roomContainer.getChildren().isEmpty()) {
	            Label messageLabel = new Label("No room options available. Please pick a different date.");
	            roomContainer.getChildren().add(messageLabel);
	        }
			tab5.setDisable(false);
	    	tp.getSelectionModel().select(tab5);
		}
		else {
			details.setText("Please enter the information above!");
		}
    }
	
	public static void ConnectSQL() {
        try {
            connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "5alasomot");
            System.out.println("Connected With the database successfully");
            // Creating PreparedStatement object
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            // Setting values for Each Parameter
            preparedStatement.setInt(1, adultNum);
            preparedStatement.setInt(2, childNum);
            Date newDateIn = Date.valueOf(checkIn);
   
            Date newDateOut = Date.valueOf(checkOut);
            preparedStatement.setDate(3, newDateIn);
            preparedStatement.setDate(4, newDateOut);
            //String id = getUserID(username,guestId);
            preparedStatement.setString(5, loggedInUser);
            int row = preparedStatement.executeUpdate();
            // rows affected
            System.out.println(row); // 1
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database");
            System.out.println(e);
        }
    }
	
    private Callback<DatePicker, DateCell> getDayCellFactory(LocalDate minDate) {
        return picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(minDate));
            }
        };
    }
	//tab3
	@FXML
	private void handleBackButton1(ActionEvent event) {
    	tab2.setDisable(false);
    	tp.getSelectionModel().select(tab2);
	}
	@FXML
	private void handleBookingButton(ActionEvent event) {
		tab4.setDisable(false);
		tp.getSelectionModel().select(tab4);
	}
    // tab2
    public void setUserInfo(String username, String guestId) {
        userInfo.setText("Welcome, " + username + " (User ID: " + guestId + ")");
    }
	@FXML
	private void handleDashBoardButton(ActionEvent event) {
		tab3.setDisable(false);
		tp.getSelectionModel().select(tab3);
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
    //tab6
	public static void ConnectSQL2() {
		int bookingID = getBookingID();
	    try {
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "5alasomot");
	        System.out.println("Connected With the database successfully");
	        // Creating PreparedStatement object
	        PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_INSERT1);
	        PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_INSERT2);
	        // Setting values for Each Parameter
	        preparedStatement1.setString(1, guest1.getPrefix());
	        preparedStatement1.setString(2, guest1.getFirstName());
	        if (guest1.getMiddleInitial() != null && !guest1.getMiddleInitial().isEmpty()) {
	            preparedStatement1.setString(3, guest1.getMiddleInitial());
	        } else {
	            preparedStatement1.setNull(3, Types.VARCHAR);
	        }
	        preparedStatement1.setString(4, guest1.getLastName());
	        preparedStatement1.setString(5, guest1.getPhoneNumber());
	        preparedStatement1.setString(6, guest1.getEmailAddress());
	        preparedStatement1.setInt(7, bookingID);

	        preparedStatement2.setString(1, address.getCountry());
	        preparedStatement2.setString(2, address.getAddress1());
	        if (address.getAddress2() != null && !address.getAddress2().isEmpty()) {
	            preparedStatement2.setString(3, address.getAddress2());
	        } else {
	            preparedStatement2.setNull(3, Types.VARCHAR);
	        }
	        preparedStatement2.setString(4, address.getCity());
	        preparedStatement2.setString(5, address.getZipCode());
	        if (address.getAdditionalDetails() != null && !address.getAdditionalDetails().isEmpty()) {
	            preparedStatement2.setString(6, address.getAdditionalDetails());
	        } else {
	            preparedStatement2.setNull(6, Types.VARCHAR);
	        }
	        preparedStatement2.setInt(7, bookingID);
	        
	        
	        int row1 = preparedStatement1.executeUpdate();
	        int row2 = preparedStatement2.executeUpdate();
	        // rows affected
	        System.out.println(row1); // 1
	        System.out.println(row2);
	    } catch (SQLException e) {
	        System.out.println("Error while connecting to the database");
	        System.out.println(e);
	    }
	}

	@FXML
	private void handleInfoButton(ActionEvent event) throws SQLException {
		String country1 = country.getText();
		String ad1 = adress1.getText();
		String ad2= "";
		if (address2.getText() == null){
			ad2 = "";
		} else {
			ad2 = address2.getText();
		}
		String cty = city.getText();
		String zipCode = zip.getText();
		String additionalDetails = detail.getText();
		if (additionalDetails == null){
			additionalDetails = "";
		}
		address = new Address(country1, ad1, ad2, cty, zipCode, additionalDetails);
		
		String prefix = prfix.getText();
		String firstName = fname.getText();
		String middleInitial = mname.getText();
		if (middleInitial == null){
			middleInitial = "";
		}
		String lastName = lname.getText();
		String phoneNumber = phone.getText();
		String emailAddress = email.getText();
		guest1 = new Guest(prefix, firstName, middleInitial, lastName, phoneNumber, emailAddress, address);	

		if(prefix.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty()
				|| country1.isEmpty() || ad1.isEmpty() || cty.isEmpty() || zipCode.isEmpty() ) {
			entryLine.setText("Please enter the information above!");
		}
		else {
			ConnectSQL2();
		
			
			tab7.setDisable(false);
	    	tp.getSelectionModel().select(tab7);
		}

        int bookingId = getBookingID();
        System.out.println("Here is your id: " + bookingId);
        //tab7
    	guest1 = getGuestInfo(retrieveData(QUERY6,bookingId));
    	Address address1 = getAddress(retrieveData(QUERY7,bookingId));
    	int room_num = getRoomNum(retrieveData(QUERY8,bookingId));
    	Date dateIn = getDateIn(retrieveData(QUERY8,bookingId));
    	Date dateOut = getDateOut(retrieveData(QUERY8,bookingId));
    	int adu_num = getAldutNum(retrieveData(QUERY8,bookingId));
    	int chil_num = getChildNum(retrieveData(QUERY8,bookingId));
    	Room roomShow = retrieveRoomData(room_num);
    	if (roomShow != null) {
		    String roomInfor = "Room Number: " + roomShow.getRoomNumber() + "\n"
		            + "Type: " + roomShow.getRoomType() + "\n"
		            + "Price: " + roomShow.getPrice();
		            
		    roomInfo.setText(roomInfor);
		} else {
			roomInfo.setText("Room not found");
		}
        //dates
        LocalDate checkIn = dateIn.toLocalDate();
        LocalDate checkOut = dateOut.toLocalDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String checkInformattedDate = checkIn.format(formatter);
		String checkOutformattedDate = checkOut.format(formatter);
		
        guestInfo.setText(guest1.toString());
        //roomInfo.setText(room1.toString());
        addressInfo.setText(address1.toString());
        String datesInOut = "Check in date: " + checkInformattedDate + 
        		"\nCheck out date: " + checkOutformattedDate;
        dates.setText(datesInOut);
        peopleNum.setText("Number of Adults: " + adu_num +
        		"\nNumber of Children: " + chil_num);
        long daysBetween = ChronoUnit.DAYS.between(checkIn, checkOut);
        double price = daysBetween*roomShow.getPrice();
        String str = "Total price: " + price;
        totalPrice.setText(str);
		
		
	}
    //tab7
    // get Room details
    public Room retrieveRoomData(int roomNumber) throws SQLException {
        String query = "SELECT * FROM room WHERE roomno = " + roomNumber;
        ResultSet resultSet = executeQuery(query);
        if (resultSet.next()) {
            int roomNum = resultSet.getInt("roomno");
            String type = resultSet.getString("roomtype");
            int price = resultSet.getInt("price");
            int capacity = resultSet.getInt("isavailable");

            return new Room(roomNum, type, price, capacity);
        }
        return null;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
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

    public static Address getAddress(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String country = resultSet.getString("country");
            String address1 = resultSet.getString("ad1");
            String address2 = resultSet.getString("ad2");
            if (address2 == null) {
            	address2 = "";
            }
            String city = resultSet.getString("cty");
            String zipcode = resultSet.getString("zipcode");
            String detail = resultSet.getString("detail"); 
            if (detail == null) {
            	detail = "";
            }
            int id1 = resultSet.getInt("booking_id");    
            Address address = new Address(country, address1, address2, city, zipcode, detail);
            return address;
        }
        return null;
    }

    //getBookingDetails
   public String getBookingDetails(ResultSet resultSet) throws SQLException {
       if (resultSet.next()) {
    	   String guest_id = resultSet.getString("guest_id");
    	   int booking_id = resultSet.getInt("booking_id");
           int aldutNum = resultSet.getInt("aldutNum");
           int childNum = resultSet.getInt("childNum");
           Date checkindate = resultSet.getDate("checkindate");
           Date checkoutdate = resultSet.getDate("checkoutdate");
           int room_num = resultSet.getInt("room_num");

           String bookingDetails = guest_id + " " + booking_id + " "
        		   + aldutNum + " " + childNum + " " + checkindate 
        		   + checkoutdate + " " + room_num ;
            return bookingDetails;
       }
       return "";
   }
   
   //
   public int getBooking_id(ResultSet resultSet) throws SQLException {
       if (resultSet.next()) {

    	   int booking_id = resultSet.getInt("booking_id");

            return booking_id;
       }
       return -1;
   }
   
   
   // get Child Num
   public int getChildNum(ResultSet resultSet) throws SQLException {
       if (resultSet.next()) {
           int childNum = resultSet.getInt("childNum");
            return childNum;
       }
       return -1;
   }
   
   // get Aldut Num
   public int getAldutNum(ResultSet resultSet) throws SQLException {
       if (resultSet.next()) {
           int aldutNum = resultSet.getInt("aldutNum");
            return aldutNum;
       }
       return -1;
   }
   
   //get Room Number
   public int getRoomNum(ResultSet resultSet) throws SQLException {
       if (resultSet.next()) {
           int room_num = resultSet.getInt("room_num");
            return room_num;
       }
       return -1;
   }
   
   //get Date In
   public Date getDateIn(ResultSet resultSet) throws SQLException {
       if (resultSet.next()) {
           Date checkindate = resultSet.getDate("checkindate");
           //System.out.println("Hello");

            return checkindate;
       }
       return null;
   }
   
   //get Date Out
   public Date getDateOut(ResultSet resultSet) throws SQLException {
       if (resultSet.next()) {
           Date checkoutdate = resultSet.getDate("checkoutdate");

            return checkoutdate;
       }
       return null;
   }
	
   @FXML
	private void handleFinishButton(ActionEvent event) throws SQLException {
	   
	   tab8.setDisable(false);
	   tp.getSelectionModel().select(tab8);
	   String booking_num = BookingSystem.generateBookingNumber();
	   //guest1 = getGuestInfo(retrieveData(QUERY6,bookingID));
	   String str =  "An Confirmation email has sent to \nyour email: " + guest1.getEmailAddress();
	   bookingNum.setText(str + "\nYour boking number is: " + booking_num);
	   System.out.println(booking_num);
	
	   String query = "UPDATE booking SET booking_num = ? WHERE booking_id = ?";
	
       try (PreparedStatement statement = connection.prepareStatement(query)) {
       int booking_id = getBookingID();
           statement.setString(1, booking_num);
           statement.setInt(2, booking_id);
           statement.executeUpdate();
       } catch (SQLException e1) {
    	   e1.printStackTrace();
       }
	}

	//tab8
	@FXML
	private void handleBackButton3(ActionEvent event) {
		tab7.setDisable(true);
		tab6.setDisable(true);
		tab8.setDisable(true);
		tab5.setDisable(true);
		tab4.setDisable(true);
		tab3.setDisable(true);
    	tp.getSelectionModel().select(tab2);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/view/ApplicaitionTabsView.fxml"));
		primaryStage.setTitle("JATISOFT BOOKING");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
		
		SignOutButton = (Button) root.lookup("#SignOutButton");
		SignOutButton.setOnAction(event -> {
			handleSignOutButton(event);
        });
		
		DashBoardButton = (Button) root.lookup("#DashBoardButton");
		DashBoardButton.setOnAction(event -> {
			handleDashBoardButton(event);
        });
	}

	public static void main(String[] args) {
		launch(args);
	}
}
