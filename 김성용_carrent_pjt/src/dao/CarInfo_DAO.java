package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import common.CommonUtil;
import common.DBConnectionOracle;
import dto.CarInfo_DTO;
import dto.Member_DTO;

public class CarInfo_DAO {
	private static final CarInfo_DTO CarInfo_DTO = null;
	DBConnectionOracle common = new DBConnectionOracle();
	Connection          con      = null;
	ResultSet	    	rs	     = null;
	PreparedStatement   ps       = null;
	
	
	public int deleteCarinfo(String car_id) {
		String query = " delete from a06_track2_carinfo "+
					   " where car_id = '"+car_id+"' ";
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
	
	
	
	public int updateCarInfo(String gubun,String car_id, String sel) {
		String query = " update a06_track2_carinfo ";
					   
		if(gubun.equals("car_name")) {
			query += "set car_name = '"+sel+"' " +
					 " where car_id = '"+car_id+"' ";	
		} else if (gubun.equals("produce")) {
			query += "set produce = '"+sel+"' " +
					 " where car_id = '"+car_id+"' ";	
		} else if(gubun.equals("produce_ym")) {
			query += "set produce_ym = '"+sel+"' " +
					 " where car_id = '"+car_id+"' ";	
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
	
	
	
	
	
	
	public ArrayList<CarInfo_DTO> getList2(String fid, String bid){
		ArrayList<CarInfo_DTO> dtos = new ArrayList<CarInfo_DTO>();
		
		String query =  " SELECT CAR_ID, CAR_NAME, PRODUCE, PRODUCE_YM, DRIVING_TOTAL_KM, decode(STATUS, 'y', '대여가능' , '대여불가능') "+
						" from a06_track2_carinfo "+
						" where car_id = '"+fid+"_"+bid+"' ";
		
		
		
		CarInfo_DTO dto  =null;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto  = new CarInfo_DTO();
				dto.setCar_id(rs.getString(1));
				dto.setCar_name(rs.getString(2));
				dto.setProduce(rs.getString(3));
				dto.setProduce_ym(rs.getString(4));
				dto.setDriving_total_km(rs.getString(5));
				dto.setStatus(rs.getString(6));
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
	
	
	public int insertCarInfo(String car_id, String car_name, String produce, String produce_ym) {
		String query =  " insert into a06_track2_carinfo(CAR_ID, CAR_NAME, PRODUCE, PRODUCE_YM) "+
						" VALUES('"+car_id+"', '"+car_name+"', '"+produce+"', '"+produce_ym+"') ";
				
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
		
	
	
	public ArrayList<CarInfo_DTO> getList(String gubun, String search){
		ArrayList<CarInfo_DTO> dtos = new ArrayList<CarInfo_DTO>();
		
		String query =  " SELECT CAR_ID, CAR_NAME, PRODUCE, PRODUCE_YM, DRIVING_TOTAL_KM, decode(STATUS, 'y', '대여가능' , '대여불가능') "+
						" from a06_track2_carinfo ";
		
		if(gubun.equals("all")) {
			query += " order by CAR_ID desc ";
		} else if (gubun.equals("car_name")) {
			query += " WHERE CAR_NAME like '%"+search+"%' order by CAR_ID desc ";
		} else if (gubun.equals("produce")) {
			query += " WHERE PRODUCE like '%"+search+"%' order by CAR_ID desc ";
		} else if (gubun.equals("ah")) {
			query += "  where substr(CAR_ID, 0,2) = '"+search+"' order by CAR_ID desc ";
		}else if (gubun.equals("ah2")) {
			
		}
		
		CarInfo_DTO dto  =null;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto  = new CarInfo_DTO();
				dto.setCar_id(rs.getString(1));
				dto.setCar_name(rs.getString(2));
				dto.setProduce(rs.getString(3));
				dto.setProduce_ym(rs.getString(4));
				dto.setDriving_total_km(rs.getString(5));
				dto.setStatus(rs.getString(6));
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
	
	
	
	
	public String getMaxid() {
		String query = " select max(car_id) from a06_track2_carinfo ";
		String result = null;
		
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
			
		}catch(SQLException se) {
			System.out.println("SQLException getMaxid():"+se.getMessage());
		}catch(Exception e) {
			System.out.println("Exception getMaxid():"+e.getMessage());
		}finally {
			try {
				common.close(con, ps, rs);
			}catch (Exception e) {
				System.out.println("getMemberList() close"+e.getMessage());
			}
		}
		
		return result;
	}
	

	
	
	public String getMaxCarId() {
		String carMaxId = getMaxid();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		String nowYear = Integer.toString(year).substring(2,4);
		String dataYear ="";
		if(carMaxId == null) {
			carMaxId = nowYear +"_001";
		} else {
			 dataYear = carMaxId.substring(0,2);
		}
	
		if(nowYear.equals(dataYear)) {
			int y =	Integer.parseInt(carMaxId.substring(3));
			y++;
			String r = CommonUtil.getLPad(Integer.toString(y), 3, "0");
			carMaxId = dataYear +"_"+r;
		}else {
			carMaxId = nowYear+"_001";
		}
		return carMaxId;
	}

}
