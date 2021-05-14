package com;

import java.sql.*;

public class User {
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb?serverTimezone=UTC", "root", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	public String insertUser(String regno, String name, String fname, String cont,  String add, String email, String password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			String query = " insert into users(`userID`,`userCode`,`userName`,`fName`,`userCont`,`userAdd`,`userEmail`,`userPwd`) values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, regno);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, fname);
			preparedStmt.setString(5, cont);
			preparedStmt.setString(6, add);
			preparedStmt.setString(7, email);
			preparedStmt.setString(8, password);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	public String readUsers()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'>"
					+ "<tr><th>User ID</th>"
					+ "<th>First name</th>"
					+ "<th>Last name</th>"
					+ "<th>Phone number</th>"
					+ "<th>Address</th>"
					+ "<th>Email</th>"
					+ "<th>Password</th>"
					+ "<th>Update</th>"
					+ "<th>Delete</th></tr>";
	
			String query = "select * from users";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String userID = Integer.toString(rs.getInt("userID"));
				String userCode = rs.getString("userCode");
				String userName = rs.getString("userName");
				String fName = rs.getString("fName");
				String userCont = rs.getString("userCont");
				String userAdd = rs.getString("userAdd");
				String userEmail = rs.getString("userEmail");
				String userPwd = rs.getString("userPwd");
				
				// Add into the html table
				output += "<tr><td><input id='hidUserIDUpdate'name='hidUserIDUpdate' type='hidden' value='" + userID+ "'>" + userCode + "</td>";
				output += "<td>" + userName + "</td>";
				output += "<td>" + fName + "</td>";
				output += "<td>" + userCont + "</td>";
				output += "<td>" + userAdd + "</td>";
				output += "<td>" + userEmail + "</td>";
				output += "<td>" + userPwd + "</td>";
			
				// buttons
				output += "<td><input name='btnUpdate'type='button' "
						+ "value='Update'class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove'type='button' "
						+ "value='Remove'class='btnRemove btn btn-danger'data-userid='"+ userID + "'>" + "</td></tr>";
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
			
		}
		catch (Exception e)
		{
			output = "Error while reading the users.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	public String updateUser(String ID, String regno, String name, String fname, String cont, String add, String email, String password)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			String query = "UPDATE users SET userCode=?,userName=?,fName=?,userCont=?,userAdd=?,userEmail=?,userPwd=? WHERE userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, regno);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, fname);
			preparedStmt.setString(4, cont);
			preparedStmt.setString(5, add);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, password);
			preparedStmt.setInt(8, Integer.parseInt(ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	
	public String deleteUser(String userID)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "delete from users where userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(userID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
}
