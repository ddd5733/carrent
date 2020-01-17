package dao;

import java.sql.Statement;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import car.CarInfo;
import common.CommonUtil;
import common.DBConnectionOracle;
import dto.CarInfo_DTO;
import dto.CarRent_DTO;
import dto.Member_DTO;

public class CarRent_DAO {
	
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 		   con    = null;
	PreparedStatement  ps 	  = null;
	ResultSet 		   rs 	  = null;
	
	
	public int insertCarRent_commit(String rent_id, String car_id, String id, String rent_start_date) {
		String query =	" insert into a06_track2_carrent(rent_id,car_id,member_id,rent_start_date) "+
						" VALUES('"+rent_id+"','"+car_id+"','"+id+"','"+rent_start_date+"') ";
		
		String queryUpdate =  " update a06_track2_carinfo "+
			 			" set status = 'n' "+
			 			" where car_id = '"+car_id+"' ";
		
		
		int result = 0;
		try {
			con = common.getConnection();
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			result =stmt.executeUpdate(query);
			result =stmt.executeUpdate(queryUpdate);
			con.commit();
			
			
//			ps = con.prepareStatement(query);
//			result = ps.executeUpdate();
		}catch(RemoteException re) {
			System.out.println("RemoteException insertCarRent_commit():"+re.getMessage());
		}catch(SQLException se) {
			System.out.println("SQLException insertCarRent_commit():"+se.getMessage());
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}catch(Exception e) {
			System.out.println("오류 Exception insertCarRent_commit():"+e.getMessage());
		}finally {
			try {
				con.setAutoCommit(true);
				common.close(con, ps);
			}catch (Exception e
					) {
				System.out.println("insertMember() close"+e.getMessage());
			}
		}
		return result;
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
	
	
	
	public CarRent_DTO getrentList(String rent_id){
		CarRent_DTO dto = null;
		String query =  " SELECT  a.rent_id , b.car_name ,c.name ,a.driving_km ,to_char(a.rent_start_date, 'yyyy-MM-dd'), "+
					 	" decode(a.rent_return_date, null, '렌트중' , '반납완료') , a.car_id"+
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
				dto.setDriving_km(rs.getString(4));
				dto.setRent_start_date(rs.getString(5));
				dto.setRent_return(rs.getString(6));
				dto.setCar_id(rs.getString(7));
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
		return dto;
	}
	
	
	

	public ArrayList<CarRent_DTO> getRentInfoList(){
		ArrayList<CarRent_DTO> dtos = new ArrayList<CarRent_DTO>();
		
		String query =  " SELECT  a.rent_id , b.car_name ,c.name ,a.driving_km ,to_char(a.rent_start_date, 'yyyy-MM-dd'), "+
					 	" decode(a.rent_return_date, null, '렌트중' , '반납완료') , a.car_id"+
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
				dtos.add(dto);
			}
			
		}catch(SQLException se) {
			System.out.println("SQLException getRentInfoList:"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getRentInfoList:"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch (Exception e) {
				System.out.println("getRentInfoList() close"+e.getMessage());
			}
		}
		
		return dtos;
	}
	
	
	public CarInfo_DTO getList2(String fid, String bid){
		CarInfo_DTO dto = null;
		String query =  " SELECT CAR_ID, CAR_NAME, PRODUCE, PRODUCE_YM, DRIVING_TOTAL_KM, decode(STATUS, 'y', '대여가능' , '대여불가능') "+
						" from a06_track2_carinfo "+
						" where car_id = '"+fid+"_"+bid+"' "+
						" and status ='y' ";
		
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new CarInfo_DTO();
				dto.setCar_id(rs.getString(1)); 
				dto.setCar_name(rs.getString(2)); 
				dto.setProduce(rs.getString(3)); 
				dto.setProduce_ym(rs.getString(4));
				dto.setDriving_total_km(rs.getString(5));
				dto.setStatus(rs.getString(6));
			}
			
		}catch(SQLException se) {
			System.out.println("SQLException getList2:"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getList2:"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch (Exception e) {
				System.out.println("getList2() close"+e.getMessage());
			}
		}
		return dto;
	}
	

	public ArrayList<CarInfo_DTO> getAllList(){
		ArrayList<CarInfo_DTO> dtos = new ArrayList<CarInfo_DTO>();
		
		String query =  " SELECT CAR_ID, CAR_NAME, PRODUCE, PRODUCE_YM, DRIVING_TOTAL_KM, decode(STATUS, 'y', '대여가능' , '대여불가능') "+
						" from a06_track2_carinfo "+
						" WHERE status ='y' order by CAR_ID desc ";
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				CarInfo_DTO dto = new CarInfo_DTO();
				dto.setCar_id(rs.getString(1));
				dto.setCar_name(rs.getString(2));
				dto.setProduce(rs.getString(3));
				dto.setProduce_ym(rs.getString(4));
				dto.setDriving_total_km(rs.getString(5));
				dto.setStatus(rs.getString(6));
				dtos.add(dto);
			}
			
		}catch(SQLException se) {
			System.out.println("SQLException getAllList:"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getAllList:"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch (Exception e) {
				System.out.println("getAllList() close"+e.getMessage());
			}
		}
		
		return dtos;
	}
	
	
			
			public int updateStatus(int gubun,String gubun2,String car_id) {
		String query =  " update a06_track2_carinfo ";
		if(gubun==1) {
			query += " set status = 'n' "+
					 " where car_id = '"+car_id+"' ";
		}
		if(gubun2.equals("y")) {
			query += " set status = 'y' "+
					 " where car_id ='"+car_id+"' ";
		}
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
	
	
	public String getMaxRent_id() {
		String query = " select max(rent_id) from a06_track2_carrent ";
		String result = null;
		
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
			
		}catch(SQLException se) {
			System.out.println("SQLException getMaxRent_id():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMaxRent_id():"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch (Exception e) {
				System.out.println("getMaxRent_id() close"+e.getMessage());
			}
		}
		
		return result;
	}
	
	public String getMaxRentId(String frent_id) {
		String carMaxId = getMaxRent_id();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String dataYear ="";
		if(carMaxId == null) {
			carMaxId = frent_id +"_0001";
		} else {
			 dataYear = carMaxId.substring(0,4);
		}
	
		if(frent_id.equals(dataYear)) {
			int y =	Integer.parseInt(carMaxId.substring(5));
			y++;
			String r = CommonUtil.getLPad(Integer.toString(y), 4, "0");
			carMaxId = dataYear +"_"+r;
		}else {
			carMaxId = frent_id+"_0001";
		}
		return carMaxId;
	}


	public int insertCarRent(String rent_id, String car_id, String id, String rent_start_date) {
		String query =	" insert into a06_track2_carrent(rent_id,car_id,member_id,rent_start_date) "+
						" VALUES('"+rent_id+"','"+car_id+"','"+id+"','"+rent_start_date+"') ";
				
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
}

	
