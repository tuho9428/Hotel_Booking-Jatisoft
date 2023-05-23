module JatisoftBooking {
	
		requires javafx.controls;
		requires javafx.fxml;
		requires javafx.base;
		requires javafx.graphics;
		requires java.sql;
		//requires java.mail;
		//exports application;
		exports application.controller;
		opens application.controller to javafx.graphics, javafx.fxml, javafx.base;
		//opens application to javafx.graphics, javafx.fxml, javafx.base;

		
		

}
