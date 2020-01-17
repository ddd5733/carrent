package common;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DBConnectionOracle{
	public Connection getConnection() throws RemoteException{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	 	
		} catch (Exception ex) {
			System.out.println("DBConnectionOracle.class = Exception: " + ex.getMessage());
		}	
		Connection con = null;
		try{
			// String DB_URL = "jdbc:oracle:thin:@admin-pc0:1521:ORCL";
			// String DB_USER = "hr";
			// String DB_PASSWORD= "1234";
			 String DB_URL = "jdbc:oracle:thin:@admin-pc04:1521:ORCL";
			 String DB_USER = "scott";
			 String DB_PASSWORD= "tiger";	
//			String DB_URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
//			String DB_USER = "hr";
//			String DB_PASSWORD= "1234";	
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);	
			if(con == null){
				System.out.println("DBConnectionOracle.class =  oracle db 연결실패===========");	
			}
		} catch (SQLException ex) {
			System.out.println("DBConnectionOracle.class = SQLException: " + ex.getMessage());
			System.out.println("DBConnectionOracle.class = SQLState: " + ex.getSQLState());
		} 
		return con;
	}	
	public void close(Connection con, PreparedStatement ps){
		try {
			if(ps != null) 		ps.close();
			if(con != null)		con.close();
		} catch(Exception e) {
			System.out.println("=========== 종료 error ===========");	
		}
    }
	public void close(Connection con, PreparedStatement ps, ResultSet result){
		try {
			if(result != null)	result.close();
			if(ps != null) 		ps.close();
			if(con != null)		con.close();
		} catch(Exception e) {
			System.out.println("=========== 종료 error ===========");	
		}
    }	
}	