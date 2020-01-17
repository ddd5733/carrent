package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnectionOracle;
import dto.MemberHistory_DTO;

public class CarHistory_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 		   con    = null;
	PreparedStatement  ps 	  = null;
	ResultSet 		   rs 	  = null;
	
	public ArrayList<MemberHistory_DTO> getList(String car_id){
		ArrayList<MemberHistory_DTO> dtos = new ArrayList<MemberHistory_DTO>();
		String query =  " select a.rent_id, b.name, c.car_name,to_char(a.rent_start_date,'yyyy-MM-dd') "+ 
						" ,decode(a.rent_return_date,null,'-',a.rent_return_date) , a.driving_km "+
						" from a06_track2_carrent a , a06_track2_member b ,a06_track2_carinfo c "+
						" where a.member_id = b.id "+
						" and a.car_id = c.car_id "+
						" and c.car_id = '"+car_id+"' order by a.rent_id desc ";
			
								       
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
			System.out.println("SQLException getList:"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getList:"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch (Exception e) {
				System.out.println("getList() close"+e.getMessage());
			}
		}
		
		return dtos;
	}
	
}
