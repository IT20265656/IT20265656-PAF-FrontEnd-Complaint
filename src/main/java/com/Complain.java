package com;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;


public class Complain {
	
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		// Connect to the DB
		public Connection connect()
		{
			Connection con = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", "");
				
				//for testing
				System.out.print("Succesfully connected to the DB");
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return con;
		}
		
		//insert a Complain
		public String insertComplain(String desc, String comDate, String UID)
		{
			System.out.println(desc+" "+comDate+" "+UID+" ");
			String output = "";
			
			String comEmpty = "^[A-Za-z0-9+_.-]{0}";
			
			try 
			{
				Connection con = connect();
				
				if(con == null)
				{return "Error while connecting to the database for inserting.";}
				
				//create a prepared statement 
				String query = " insert into complain_table (`Complain_id`,`Description`,`complainDate`,`User_ID`)"
						+ " values (?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, desc);
				preparedStmt.setString(3, comDate);
				preparedStmt.setString(4, UID);
				
				if((desc.matches(comEmpty))){
					output = "Description should not be Empty!";
				}else {
					
							//execute the statement
					preparedStmt.execute();
					con.close();
					
					String newComplain = readComplain();
					output = "{\"status\":\"success\", \"data\": \"" + 
							newComplain + "\"}"; 

				}
			}catch(Exception e)
			{
				output = "{\"status\":\"error\", \"data\": "
						+ "\"Error while inserting the Complaints.\"}"; 
				 System.err.println(e.getMessage()); 
			}
			return output;
		}
		
		
		
		//Read a Complain
				public String readComplain() 
				{
					String output = "";
					
					try
					{
						Connection con = connect();
						
						if(con == null)
						{
							return "Error while connecting to the database for Reading.";}
						
						//Prepare the html table to be displayed
						output = "<table border='1'><tr><th>Complain id</th>" 
						        +  "<th>Complain</th>"
								+  "<th>Date</th>"
								+  "<th>User ID</th></tr>";
						
						String query = "select * from complain_table";
						 java.sql.Statement stmt = con.createStatement(); 
						 ResultSet rs = stmt.executeQuery(query); 
						
						// iterate through the rows in the result set
						while(rs.next())
						{
							 String comId = Integer.toString(rs.getInt("Complain_id")); 
							 String comDec = rs.getString("Description"); 
							 String comDate = rs.getString("complainDate"); 
							 String UID = rs.getString("User_ID"); 
	
						
						 // Add into the html table
							 //output += "<tr><td><input id='hidcomIDUpdate' name='hidcomIDUpdate' type='hidden' value='" + comId + "'>"+"</td>"; 
						 output += "<form id ='ComplaintUpdate'  method='post' action='Complain.jsp'><tr><td>" +comId+"</td>";
						 output += "<td><input id='updatecomdec' name='updatecomdec' type='text' value='" + comDec + "'>"+"</td>";
						 output += "<td><input id='updatecomdate' name='updatecomdate' type='text' value='" + comDate + "'>"+"</td>";
						 output += "<td><input id='updatecomUID' name='updatecomUID' type='text' value='" + UID + "'>"+"</td>"; 
					
						 
						 // buttons
//						output += "<td><form method='post' action='#'>"
//								+ "<input name='btnUpdate' "
//								+ " type='submit' value='Update'>"
//								+ "<td><form method='post' action='#'>"
//								+ "<input name='btnRemove' "
//								+ " type='submit' value='Remove'>"
//								+ "<input name='comId' type='hidden' "
//							    + " value='" + comId + "'>" + "</form></td></tr>"; 
//						 output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate'></td>"
//									+ "<td><form method='post' action='Complain.jsp'>"
//									+ "<input name='btnRemove' "
//									+ " type='submit' value='Remove'>"
//									+ "<input name='hidcomIDDelete' type='hidden' "
//								    + " value='" + comId + "'>" + "</form></td></tr>";
						 
						 output += "<td><input id='btnUpdate' name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-itemid='" + comId + "'></td>"
						 + "<td><input id='btnRemove' name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-itemid='" + comId + "'></td></tr></form>"; 
						 		
						
						}
						 con.close();
						 // Complete the html table
						 output += "</table>"; 
					}
					catch(Exception e)
					{
						output = "Error while Reading the Complains ."; 
						System.err.println(e.getMessage());
					}
					return output;
					
				}
				
				
				//Update Complain		
				public String updateComplain(String desc, String comDate, String UID, String ComplainId)
				{
					String output = "";
					
					try
					{
						Connection con = connect();
						
						if(con == null)
						{return "Error while connecting to the database for Updating.";}
						
						//create a prepared statement
						String query = "UPDATE complain_table SET Description=?,complainDate=?,User_ID=? WHERE Complain_id=?";
						
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						//Binding Values
						preparedStmt.setString(1, desc);
						preparedStmt.setString(2, comDate);
						preparedStmt.setString(3, UID);
						preparedStmt.setInt(4, Integer.parseInt(ComplainId)); 
						
						// execute the statement
						 preparedStmt.execute(); 
						 con.close(); 

						 String newComplain = readComplain();
							output = "{\"status\":\"success\", \"data\": \"" + 
									newComplain + "\"}"; 
						 
						
					}catch(Exception e)
					{
						output = "{\"status\":\"error\", \"data\": "
								+ "\"Error while Updating the Complaints.\"}"; 
						 System.err.println(e.getMessage()); 
					}
					return output;
					
					
				}
				
				//Delete a Complain
				public String deleteComplain(String comID) 
				{
					String output = "";
					
					try 
					{
						Connection con = connect();
						
						if(con == null) 
						{return "Error while connecting to the database for deleting.";}
						
						//create a prepared statement
						String query = "delete from complain_table where Complain_id=?";
					
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						//binding values
						preparedStmt.setInt(1, Integer.parseInt(comID));
						
						
						//execute the statement
						preparedStmt.execute();
						con.close();
						
						 String newComplain = readComplain();
							output = "{\"status\":\"success\", \"data\": \"" + 
									newComplain + "\"}"; 
						
					}
					catch(Exception e) {
						output = "{\"status\":\"error\", \"data\": "
								+ "\"Error while Deleting the Complaints.\"}"; 
						 System.err.println(e.getMessage()); 
					}
					
					return output;
				}
				
		
}
