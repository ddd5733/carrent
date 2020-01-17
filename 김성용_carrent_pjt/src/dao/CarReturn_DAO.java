package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.CarRent_DTO;

public class CarReturn_DAO {
	
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 		   con    = null;
	PreparedStatement  ps 	  = null;
	ResultSet 		   rs 	  = null;
	
	public int delete(String query) {
		int result = 0;
		try {
		con = common.getConnection();
		ps = con.prepareStatement(query);
		result = ps.executeUpdate();
		
		}catch(SQLException se) {
			System.out.println("SQLException deleteCarinfo():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception deleteCarinfo():"+e.getMessage());
		}finally {
			try {
				common.close(con, ps);
			}catch (Exception e) {
				System.out.println("deleteCarinfo() close"+e.getMessage());
			}
		}
		return result;
	}
	
	
	public CarRent_DTO getCancel(String rent_id){
		CarRent_DTO dto = null;
		String query =  " SELECT  a.rent_id , b.car_name ,c.name , a.Driving_km ,to_char(a.rent_start_date, 'yyyy-MM-dd'), "+ 
						" decode(a.rent_return_date, null, '렌트중' , '반납완료') , a.car_id  "+
						" from  a06_track2_carrent a, a06_track2_carinfo b , a06_track2_member c "+ 
						" where a.car_id = b.car_id "+
						" and a.member_id =c.id "+ 
						" and a.rent_return_date is not null "+ 
						" and rent_id in (select max(rent_id) from a06_track2_carrent group by car_id) "+
						" and a.rent_id = '"+rent_id+"'order by a.rent_id desc ";
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new CarRent_DTO();
				dto.setRent_id(rs.getString(1)); 
				dto.setCar_name(rs.getString(2)); 
				dto.setMember_id(rs.getString(3)); 
				dto.setDriving_km(rs.getString(4));
				dto.setRent_start_date(rs.getString(5));
				dto.setRent_return(rs.getString(6));
				dto.setCar_id(rs.getString(7));
			}
			
		}catch(SQLException se) {
			System.out.println("SQLException getCancel:"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getCancel:"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch (Exception e) {
				System.out.println("getMemberList() close"+e.getMessage());
			}
		}
		return dto;
	}
	
	
	
	public ArrayList<CarRent_DTO> getCancelList(){
		ArrayList<CarRent_DTO> dtos = new ArrayList<CarRent_DTO>();
		
		String query =  " SELECT  a.rent_id , b.car_name ,c.name ,a.driving_km ,to_char(a.rent_start_date, 'yyyy-MM-dd'), "+ 
						" decode(a.rent_return_date, null, '렌트중' , '반납완료') , a.car_id  "+
						" from  a06_track2_carrent a, a06_track2_carinfo b , a06_track2_member c "+ 
						" where a.car_id = b.car_id "+
						" and a.member_id =c.id "+ 
						" and a.rent_return_date is not null "+ 
						" and rent_id in (select max(rent_id) from a06_track2_carrent group by car_id) ";
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				CarRent_DTO dto = new CarRent_DTO();
				dto.setRent_id(rs.getString(1));
				dto.setCar_name(rs.getString(2));
				dto.setMember_id(rs.getString(3));
				dto.setDriving_km(rs.getString(4));
				dto.setRent_start_date(rs.getString(5));
				dto.setRent_return(rs.getString(6));
				dto.setCar_id(rs.getString(7));
				dtos.add(dto);
			}
			
		}catch(SQLException se) {
			System.out.println("SQLException getCancelList:"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getCancelList:"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch (Exception e) {
				System.out.println("getMemberList() close"+e.getMessage());
			}
		}
		
		return dtos;
	}
	
	
	
	public ArrayList<CarRent_DTO> getRentInfoList(){
		ArrayList<CarRent_DTO> dtos = new ArrayList<CarRent_DTO>();
		
		String query =  " SELECT  a.rent_id , b.car_name ,c.name ,a.driving_km ,to_char(a.rent_start_date, 'yyyy-MM-dd'), "+
					 	" decode(a.rent_return_date, null, '렌트중' , '반납완료') , a.car_id , b.Driving_total_km "+
						" from  a06_track2_carrent a, a06_track2_carinfo b , a06_track2_member c "+
						" where a.car_id = b.car_id "+
						" and a.member_id =c.id "+
						" and a.rent_return_date is null order by a.rent_id desc ";
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				CarRent_DTO dto = new CarRent_DTO();
				dto.setRent_id(rs.getString(1));
				dto.setCar_name(rs.getString(2));
				dto.setMember_id(rs.getString(3));
				dto.setDriving_km(rs.getString(4));
				dto.setRent_start_date(rs.getString(5));
				dto.setRent_return(rs.getString(6));
				dto.setCar_id(rs.getString(7));
				dto.setDriving_total_km(rs.getString(8));
				dtos.add(dto);
			}
			
		}catch(SQLException se) {
			System.out.println("SQLException getMemberList:"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception executeQuery:"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch (Exception e) {
				System.out.println("getMemberList() close"+e.getMessage());
			}
		}
		
		return dtos;
	}
	
	public int update(String query) {
	
	int result = 0;
	try {
	con = common.getConnection();
	ps = con.prepareStatement(query);
	result = ps.executeUpdate();
	
	}catch(SQLException se) {
		System.out.println("SQLException insertMember():"+se.getMessage());
	}catch(Exception e) {
		System.out.println("Exception insertMember():"+e.getMessage());
	}finally {
		try {
			common.close(con, ps);
		}catch (Exception e) {
			System.out.println("insertMember() close"+e.getMessage());
		}
	}
	return result;
}
	
	public CarRent_DTO getrentList(String rent_id){
		CarRent_DTO dto = null;
		String query =  " SELECT  a.rent_id , b.car_name ,c.name , b.driving_total_km,to_char(a.rent_start_date, 'yyyy-MM-dd'), "+
					 	" decode(a.rent_return_date, null, '렌트중' , '반납완료') , a.car_id ,a.driving_km " +
						" from  a06_track2_carrent a, a06_track2_carinfo b , a06_track2_member c "+
						" where a.car_id = b.car_id "+
						" and a.member_id =c.id "+
						" and a.rent_return_date is null "+
						" and a.rent_id = '"+rent_id+"' order by a.rent_id desc ";
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new CarRent_DTO();
				dto.setRent_id(rs.getString(1)); 
				dto.setCar_name(rs.getString(2)); 
				dto.setMember_id(rs.getString(3)); 
				dto.setDriving_total_km(rs.getString(4));
				dto.setRent_start_date(rs.getString(5));
				dto.setRent_return(rs.getString(6));
				dto.setCar_id(rs.getString(7));
				dto.setDriving_km(rs.getString(8));
			}
			
		}catch(SQLException se) {
			System.out.println("SQLException getrentList:"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getrentList:"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch (Exception e) {
				System.out.println("getMemberList() close"+e.getMessage());
			}
		}
		return dto;
	}
	
	
}
