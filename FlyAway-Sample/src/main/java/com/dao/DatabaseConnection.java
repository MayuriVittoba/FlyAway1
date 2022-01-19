package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.model.Flight;

public class DatabaseConnection {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private String DB_NAME = "mydb";

	public DatabaseConnection(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	public DatabaseConnection() {
		this("jdbc:mysql://localhost/mydb", "mayuri", "password");
	}

	public Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public ResultSet selectQuery(String query) {
		ResultSet resSet = null;
		try {
			connect();

			PreparedStatement statement = jdbcConnection.prepareStatement(query);
			resSet = statement.executeQuery();

			statement.close();
			disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resSet;
	}

	protected void createDB() {
		String sql = "CREATE DATABASE IF NOT EXISTS mydb";
		sql = "USE mydb";

		try {
			PreparedStatement stmt = jdbcConnection.prepareStatement(sql);

			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createUserTable() throws SQLException {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS User(UserId INT NOT NULL auto_increment,"
				+ "Name VARCHAR(300) NOT NULL,Address VARCHAR(500) NULL,PhoneNumber VARCHAR(45) NULL,"
				+ "PRIMARY KEY(UserId))";
		Statement stmt = jdbcConnection.createStatement();
		stmt.execute(sqlCreate);
	}

	private void createAirlinesTable() throws SQLException {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS Airlines" + "(AirlineId INT NOT NULL AUTO_INCREMENT,"
				+ "AirlineName VARCHAR(100) NOT NULL," + "PRIMARY KEY (AirlineId))";
		Statement stmt = jdbcConnection.createStatement();
		stmt.execute(sqlCreate);
	}

	private void createBookingTable() throws SQLException {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS BookingDetails" + "  (BookingId INT NOT NULL AUTO_INCREMENT,"
				+ "  UserId INT NOT NULL," + "  FlightId INT NOT NULL," + "  NumberOfPerson INT NULL,"
				+ "  AmtPaid DOUBLE NOT NULL," + "  TransactionNumber VARCHAR(200) NOT NULL,"
				+ "Source VARCHAR(300) NOT NULL," + "Destination VARCHAR(300) NOT NULL," + "FlyDate DATE NOT NULL,"
				+ "  PRIMARY KEY (BookingId,UserId,FlightId)," + "  CONSTRAINT UserId" + "    FOREIGN KEY (UserId)"
				+ "    REFERENCES User(UserId)," + "  CONSTRAINT FlightId" + "    FOREIGN KEY (FlightId)"
				+ "    REFERENCES Flight(FlightId))";
		Statement stmt = jdbcConnection.createStatement();
		stmt.execute(sqlCreate);
	}

	private void createFlightTable() throws SQLException {
		String sqlCreate;
		sqlCreate = "CREATE TABLE IF NOT EXISTS Flight(" + "  FlightId INT NOT NULL AUTO_INCREMENT,"
				+ "  FlightName VARCHAR(100) NOT NULL," + "  IdAirline INT NOT NULL,"
				+ "  PRIMARY KEY (FlightId, IdAirline)," + "  CONSTRAINT IdAirline" + "    FOREIGN KEY (IdAirline)"
				+ "    REFERENCES Airlines(AirlineId))";
		Statement stmt = jdbcConnection.createStatement();
		stmt.execute(sqlCreate);
	}

	private void createSourceTable() throws SQLException {
		String sqlCreate;
		sqlCreate = "CREATE TABLE IF NOT EXISTS `SourceTable` (`SrcId` INT NOT NULL AUTO_INCREMENT,`SrcName` VARCHAR(200) NOT NULL,PRIMARY KEY (`SrcId`))";
		Statement stmt = jdbcConnection.createStatement();
		stmt.execute(sqlCreate);
	}

	private void createDestinationTable() throws SQLException {
		String sqlCreate;
		sqlCreate = "CREATE TABLE IF NOT EXISTS `DestinationTable` (`DestId` INT NOT NULL AUTO_INCREMENT,`DestName` VARCHAR(200) NOT NULL,PRIMARY KEY (`DestId`))";
		Statement stmt = jdbcConnection.createStatement();
		stmt.execute(sqlCreate);
	}

	private void createFlightDetailsTable() throws SQLException {
		String sqlCreate;
		sqlCreate = "CREATE TABLE IF NOT EXISTS FlightDetails" + "(FlyId INT NOT NULL," + "SrcId INT NOT NULL,"
				+ "DestId INT NOT NULL," + "TicketPrice DOUBLE NOT NULL,"
				+ "FlyDate DATE NOT NULL,NoOfPersons INT NOT NULL," + "PRIMARY KEY (`FlyId`, `SrcId`, `DestId`),"
				+ "CONSTRAINT `FlyId` FOREIGN KEY (`FlyId`) REFERENCES `Flight` (`FlightId`),"
				+ "CONSTRAINT `SrcId` FOREIGN KEY (`SrcId`) REFERENCES `SourceTable` (`SrcId`),"
				+ "CONSTRAINT `DestId` FOREIGN KEY (`DestId`) REFERENCES `DestinationTable` (`DestId`))";
		Statement stmt = jdbcConnection.createStatement();
		stmt.execute(sqlCreate);
	}

	public void createAdminTable() throws SQLException {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS Admin(AdminId INT NOT NULL auto_increment,"
				+ "AdminEmail VARCHAR(300) NOT NULL,AdminPassword VARCHAR(200) NOT NULL," + "PRIMARY KEY(AdminId))";
		Statement stmt = jdbcConnection.createStatement();
		stmt.execute(sqlCreate);

	}
}
