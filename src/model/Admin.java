package model;

import java.sql.*;

public class Admin {
	
	
//connection to db
	public Connection connect() 
	{ 
	Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", ""); 
	 //For testing
	 System.out.println("Successfully connected to database"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	}
	
//===========================================================================================================
//===========================================================================================================
	


	//inserting
		public String insertNotice(String noticeContent, String issuedate) {
			String output = " ";
			
			try {
				
				Connection con = connect(); 
				 if (con == null) 
				 {return "Error while connecting to the database for inserting."; } 
				 // create a prepared statement
				 String query = " insert into notices(`nid`,`ncontent`,`issuedate`)"
				 + " values (?, ?, ?)"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, noticeContent); 
				 preparedStmt.setString(3, issuedate); 
				  
				 
				// execute the statement
				 preparedStmt.execute(); 
				 
				 con.close(); 
				 output = "Inserted successfully";
				
			}catch(Exception e){
				output = "Error in adding new notice";
				System.err.println(e.getMessage()); 
			}
			return output;
		}
		
//===========================================================================================================
//===========================================================================================================
			
			
			//View previous notices
			public String viewNotices() {
				String output = " ";
				
				try {
					
					Connection con = connect(); 
					
					if (con == null) 
					{return "Error while connecting to the database for reading."; }
					
					// create a HTML table to display values
					output = "<table border='2'><tr><th>Notice Id</th><th>Notice</th><th>Issued Date and Time</th><th>Update</th><th>Remove</th></tr>"; 
					String query = "select * from notices"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query); 
					
					//Iteration through rows
					while (rs.next()) { 
						String nID = Integer.toString(rs.getInt("nid")); 
						String ncontent = rs.getString("ncontent"); 
						String issuedate = rs.getString("issuedate"); 
						
						// Inserting to HTML table
						output += "<tr><td>" + nID + "</td>"; 
						output += "<td>" + ncontent + "</td>"; 	
						output += "<td>" + issuedate + "</td>"; 	
						
						// buttons
//						output += "<td><form method='post' action='#'>" 
//						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" 
//						+ "<input name='finishedPID' type='hidden' value='Edit'" 
//						+ nID + "'>" + "</form></td></tr>";
						
						output += "<td><input name='btupdate'"
						+"type='button' value='Update' onclick=></td>"
						+"<td><form method='post' action=''>"
						+"<input name= 'btremove'"
						+"type='submit' value='Remove'>"
						+ "<input name = '' type = 'hidden' "
						+"value='" + nID + "'>"
						+"</form></td></tr>"; 
					}
						con.close(); 
						// Closing HTML table
						output += "</table>"; 
					
				}catch(Exception e) {
					output = "Error while reading notices."; 
					System.err.println(e.getMessage()); 
				}
				return output;
			}

}