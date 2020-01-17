package dao;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnectionOracle;
import dto.Member_DTO;

public class Member_DAO {
	DBConnectionOracle common = new DBConnectionOracle();
	Connection 		   con    = null;
	PreparedStatement  ps 	  = null;
	ResultSet 		   rs 	  = null;
	
	public int deleteMember(String id) {
		String query = " delete from a06_track2_member "+
					   " where id = '"+id+"' ";
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
	
	public int updateMember(String id, String name, int age,
			String dept_no, String rank_no,
			String address, String reg_date) {
		String query = " update a06_track2_member "+
					   " set name='"+name+"', age='"+age+"', dept_no='"+dept_no+"', rank_no='"+rank_no+"', "+
					   " address = '"+address+"', reg_date='"+reg_date+"' "+
					   " where id='"+id+"' ";   
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
	
	public int insertMember_2(String id, String name, int age,
			String dept_no, String rank_no,
			String address, String reg_date) {
		String query = " insert into a06_track2_member (id, name, age, dept_no, rank_no, "+ 
					   " address, reg_date) "+
					   " values(?,?,?,?,?,?,?) ";   
		int result = 0;
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, dept_no);
			ps.setString(5, rank_no);
			ps.setString(6, address);
			ps.setString(7, reg_date);
			
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

	
	public int insertMember_DTO(Member_DTO dto) {
		String query = " insert into a06_track2_member (id, name, age, dept_no, rank_no, "+ 
					   " address, reg_date) "+
					   " values('"+dto.getId()+"','"+dto.getName()+"','"+dto.getAge()+"','"+dto.getDept()+"','"+dto.getRank()+"','"+dto.getAddress()+"','"+dto.getReg_date()+"') ";   
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
	
	
	
	public int insertMember(String id, String name, int age,
							String dept_no, String rank_no,
							String address, String reg_date) {
		String query = " insert into a06_track2_member (id, name, age, dept_no, rank_no, "+ 
                	   " address, reg_date) "+
					   " values('"+id+"','"+name+"','"+age+"','"+dept_no+"','"+rank_no+"','"+address+"','"+reg_date+"') ";   
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
	

	public Member_DTO getMemberInfo(String id){
		Member_DTO dto = null;
		String query = " select a.id, a.name, a.age, b.dept_name, "+
				       " c.rank_name, nvl(a.address,'-') 주소, to_char(a.reg_date,'yyyy-MM-dd') 등록일 "+
				       " from a06_track2_member a, "+
				       "      a06_track2_dept_desc b, "+
				       "      a06_track2_rank_desc c "+
				       " where a.dept_no = b.dept_no "+
				       " and a.rank_no = c.rank_no "+
				       " and a.id = '"+id+"' order by a.id ";				       
		
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				dto = new Member_DTO();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setDept(rs.getString(4));
				dto.setRank(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setReg_date(rs.getString(7));
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
	
	public ArrayList<Member_DTO> getList(String gubun, String search){
		ArrayList<Member_DTO> dtos = new ArrayList<Member_DTO>();
		String query = " select a.id, a.name, a.age, b.dept_name, "+
				       " c.rank_name, nvl(a.address,'-') 주소, to_char(a.reg_date,'yyyy-MM-dd') 등록일 "+
				       " from a06_track2_member a, "+
				       "      a06_track2_dept_desc b, "+
				       "      a06_track2_rank_desc c "+
				       " where a.dept_no = b.dept_no "+
				       " and a.rank_no = c.rank_no ";				       
		if(gubun.contentEquals("name")) query += " and a.name like '%"+search+"%' order by a.id ";
		else if(gubun.contentEquals("dept")) query += " and a.dept_no = '"+search+"' order by a.id ";
		else if(gubun.contentEquals("all")) query += " order by a.id ";
		
		try {
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				Member_DTO dto = new Member_DTO();
				dto.setId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setDept(rs.getString(4));
				dto.setRank(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setReg_date(rs.getString(7));
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
	
	//아이디 번호 부여
	public String getMaxId(){
		String query = " select max(id) from a06_track2_member ";
		int maxId=0;
		try{
			con = common.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				maxId = rs.getInt(1);
			}
		} catch(RemoteException re){
			System.out.println("RemoteException getMaxId : "+re.getMessage());
		} catch(SQLException se){
			System.out.println("SQLException getMaxId : "+se.getMessage());
		} catch(Exception e){
			System.out.println("Exception getMaxId : "+e.getMessage());
		}
		try{
			common.close(con,ps,rs);
		} catch(Exception e){
			System.out.print("close() 오류~ "+e.getMessage());
		}
		if(maxId==0) maxId = 101;
		else maxId = maxId+1;
		return Integer.toString(maxId);
	}
	
	
}


