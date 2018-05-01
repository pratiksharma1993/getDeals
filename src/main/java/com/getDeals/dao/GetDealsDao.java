package com.getDeals.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.getDeals.data.GetOrderResponse;
import com.getDeals.data.SaleDetails;

public class GetDealsDao {

	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "system";
	private static final String DB_PASSWORD = "system";
	
	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
                            DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}
	
	public ArrayList<GetOrderResponse> getShops(String pincode,String shopCategory)
	{
		ArrayList<GetOrderResponse> listOfShops=new ArrayList<GetOrderResponse>();
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement preparedStatement = null;
		
		if(pincode != null && shopCategory != null){
				String sql="select categoryid from shop_category_ref where category=?";
				String sql2="select SHOPNAME,SHOPCONTACTNUM from shop_details where PINCODE = ? and SHOPCATEGORYID=?";
				String sql3="select SHOPNAME, SHOPCONTACTNUM, DISCOUNT,DESCRIPTION, ENDDATE from DEAL_DETAILS where SHOPNAME = ? and SHOPCONTACTNUM = ?";
				try {
						conn = getDBConnection(); 
						stmt = conn.createStatement(); 
						
				        //ResultSet rs = stmt.executeQuery(sql);
				        preparedStatement = conn.prepareStatement(sql);
		
						preparedStatement.setString(1, shopCategory);
						ResultSet rs =preparedStatement.executeQuery(sql);
					    while (rs.next()) {
					        //ResultSet rs2 = stmt.executeQuery(sql2);
					        preparedStatement = conn.prepareStatement(sql2);
			
							preparedStatement.setString(1, pincode);
							preparedStatement.setInt(2, rs.getInt(1));
							ResultSet rs2 =preparedStatement.executeQuery(sql2);
							while (rs2.next()) {
						        //ResultSet rs3 = stmt.executeQuery(sql3);
						        preparedStatement = conn.prepareStatement(sql3);
				
								preparedStatement.setString(1, rs2.getString(1));
								preparedStatement.setInt(2, rs2.getInt(2));
								ResultSet rs3 =preparedStatement.executeQuery(sql3);
								while (rs3.next()) {
									GetOrderResponse resp = new GetOrderResponse();
									resp.setShopName(rs3.getString(1));
									resp.setShopContactNum(rs3.getInt(2));
									
									SaleDetails saleDetails = new SaleDetails();
									saleDetails.setDiscount(rs3.getString(3));
									saleDetails.setDescription(rs3.getString(4));
									saleDetails.setExpiry(rs3.getString(5));
									
									resp.setSaleDetails(saleDetails);
									if(listOfShops.size()<5)
										listOfShops.add(resp);
								}
					    	  
							}
					    	  
					    }
						

				    } catch (SQLException e) {
				      e.printStackTrace();
				    }
				
				return listOfShops;
		}
		else if(pincode == null && shopCategory != null){
			String sql="select categoryid from shop_category_ref where category=?";
			String sql2="select SHOPNAME,SHOPCONTACTNUM from shop_details where SHOPCATEGORYID=?";
			String sql3="select SHOPNAME, SHOPCONTACTNUM, DISCOUNT,DESCRIPTION, ENDDATE from DEAL_DETAILS where SHOPNAME = ? and SHOPCONTACTNUM = ?";
			
			try {
					conn = getDBConnection(); 
					stmt = conn.createStatement(); 
					
			        //ResultSet rs = stmt.executeQuery(sql);
			        preparedStatement = conn.prepareStatement(sql);
	
					preparedStatement.setString(1, shopCategory);
					ResultSet rs =preparedStatement.executeQuery();

			      while (rs.next()) {
				        //ResultSet rs2 = stmt.executeQuery(sql2);
				        preparedStatement = conn.prepareStatement(sql2);
		
						preparedStatement.setInt(1, rs.getInt(1));
						//preparedStatement.setInt(2, rs.getInt(1));
						ResultSet rs2 =preparedStatement.executeQuery();
						while (rs2.next()) {
					        //ResultSet rs3 = stmt.executeQuery(sql3);
					        preparedStatement = conn.prepareStatement(sql3);
			
							preparedStatement.setString(1, rs2.getString(1));
							preparedStatement.setInt(2, rs2.getInt(2));
							ResultSet rs3 =preparedStatement.executeQuery();
							while (rs3.next()) {
								GetOrderResponse resp = new GetOrderResponse();
								resp.setShopName(rs3.getString(1));
								resp.setShopContactNum(rs3.getInt(2));
								
								SaleDetails saleDetails = new SaleDetails();
								saleDetails.setDiscount(rs3.getString(3));
								saleDetails.setDescription(rs3.getString(4));
								saleDetails.setExpiry(rs3.getString(5));
								
								resp.setSaleDetails(saleDetails);
								if(listOfShops.size()<5)
									listOfShops.add(resp);
							}
				    	  
						}
				    	  
				    }
			    } catch (SQLException e) {
			      e.printStackTrace();
			    }
			
			return listOfShops;
		}
		else if(pincode != null && shopCategory == null){
			String sql="select SHOPNAME,SHOPCONTACTNUM from shop_details where PINCODE = ?";
			String sql2="select SHOPNAME, SHOPCONTACTNUM, DISCOUNT,DESCRIPTION, ENDDATE from DEAL_DETAILS where SHOPNAME = ? and SHOPCONTACTNUM = ?";
			try {
					conn = getDBConnection(); 
					stmt = conn.createStatement(); 
					
			        //ResultSet rs = stmt.executeQuery(sql);
			        preparedStatement = conn.prepareStatement(sql);
	
					preparedStatement.setString(1, pincode);
					ResultSet rs =preparedStatement.executeQuery(sql);

			      while (rs.next()) {
				        //ResultSet rs2 = stmt.executeQuery(sql2);
				        preparedStatement = conn.prepareStatement(sql2);
		
						preparedStatement.setString(1, rs.getString(1));
						preparedStatement.setInt(2, rs.getInt(2));
						ResultSet rs2 =preparedStatement.executeQuery(sql2);
						while (rs2.next()) {
							GetOrderResponse resp = new GetOrderResponse();
							resp.setShopName(rs2.getString(1));
							resp.setShopContactNum(rs2.getInt(2));
							
							SaleDetails saleDetails = new SaleDetails();
							saleDetails.setDiscount(rs2.getString(3));
							saleDetails.setDescription(rs2.getString(4));
							saleDetails.setExpiry(rs2.getString(5));
							
							resp.setSaleDetails(saleDetails);
							if(listOfShops.size()<5)
								listOfShops.add(resp);

				    	  
						}
				    	  
				    }
			    } catch (SQLException e) {
			      e.printStackTrace();
			    }
			
			return listOfShops;
		}
		else{
			String sql="select SHOPNAME, SHOPCONTACTNUM, DISCOUNT,DESCRIPTION, ENDDATE from DEAL_DETAILS where rownum<6";
			try {
					conn = getDBConnection(); 
					stmt = conn.createStatement(); 
					
			        //ResultSet rs = stmt.executeQuery(sql);
			        preparedStatement = conn.prepareStatement(sql);
	
					preparedStatement.setString(1, pincode);
					ResultSet rs = preparedStatement.executeQuery(sql);

			      while (rs.next()) {
						GetOrderResponse resp = new GetOrderResponse();
						resp.setShopName(rs.getString(1));
						resp.setShopContactNum(rs.getInt(2));
						
						SaleDetails saleDetails = new SaleDetails();
						saleDetails.setDiscount(rs.getString(3));
						saleDetails.setDescription(rs.getString(4));
						saleDetails.setExpiry(rs.getString(5));
						
						resp.setSaleDetails(saleDetails);
						if(listOfShops.size()<5)
							listOfShops.add(resp);

			    	  
					}
			    } catch (SQLException e) {
			      e.printStackTrace();
			    }
			
			return listOfShops;
		}
	}
}
