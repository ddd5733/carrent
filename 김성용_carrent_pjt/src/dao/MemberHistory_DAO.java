package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnectionOracle;
import dto.CarRent_DTO;
import dto.MemberHistory_DTO;
import dto.Member_DTO;

public class MemberHistory_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 		   con    = null;
	PreparedStatement  ps 	  = null;
	ResultSet 		   rs 	  = null;
	
	
	public ArrayList<MemberHistory_DTO> getList(String id){
		ArrayList<MemberHistory_DTO> dtos = new ArrayList<MemberHistory_DTO>();
		String query =  " select a.rent_id, b.name, c.car_name,to_char(a.rent_start_date,'yyyy-MM-dd') "+ 
						" ,decode(a.rent_return_date,null,'-',a.rent_return_date) , a.driving_km "+
						" from a06_track2_carrent a , a06_track2_member b ,a06_track2_carinfo c "+
						" where a.member_id = b.id "+
						" and a.car_id = c.car_id "+
						" and b.id = '"+id+"' order by a.rent_id desc ";	
								       
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
			MemberHistory_DTO dto = new MemberHistory_DTO();
				dto.setRent_id(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setCar_name(rs.getString(3));
				dto.setRent_start_date(rs.getString(4));
				dto.setRent_return_date(rs.getString(5));
				dto.setDriving_km(rs.getString(6));
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
	
}
