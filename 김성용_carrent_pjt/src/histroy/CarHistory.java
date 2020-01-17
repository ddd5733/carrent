package histroy;

import java.util.ArrayList;
import java.util.Scanner;

import InterCommon.InterCarRent;
import common.CommonUtil;
import dao.CarHistory_DAO;
import dao.CarInfo_DAO;
import dao.MemberHistory_DAO;
import dto.CarInfo_DTO;
import dto.MemberHistory_DTO;

public class CarHistory implements InterCarRent{
	CarInfo_DAO dao = new CarInfo_DAO();
	Scanner sc = new Scanner(System.in);
	CommonUtil util = new CommonUtil();
	ArrayList<CarInfo_DTO> dtos = null;
	ArrayList<MemberHistory_DTO> dtos2 = null;
	CarHistory_DAO dao2 = new CarHistory_DAO();
	
	public void proc(){
		int gubun = 0;
		do{
			System.out.println(" 조회[1], 이전[0] ");
			gubun=util.inputNumberOnly();
		if(gubun==1) {
			dtos = dao.getList("all","");
			 System.out.println("등록번호\t차종류\t제조사\t제조일자\t주행거리\t렌트가능여부");
			 System.out.println(util.getRPad("", 60, "-"));
			for(int k=0; k<dtos.size(); k++) {
				System.out.print(dtos.get(k).getCar_id()+"\t");
				System.out.print(dtos.get(k).getCar_name()+"\t");
				System.out.print(dtos.get(k).getProduce()+"\t");
				System.out.print(dtos.get(k).getProduce_ym()+"\t");
				System.out.print(util.getRPad(dtos.get(k).getDriving_total_km()+"km",5," ")+"\t");
				System.out.print(dtos.get(k).getStatus()+"\n");
			 }
			 	System.out.println(util.getRPad("", 60, "-"));
			 	System.out.println("");
			 
				String incomCar_number = "";
				do {
						System.out.println("조회하실 차량의 등록번호 5자리 숫자를 입력해주세요.\n(ex)xx_xxx [숫자만 입력하세요] ");
						incomCar_number = sc.next();
						System.out.println("");
						String fIcC_n= incomCar_number.substring (0,2);
						String bIcC_n= incomCar_number.substring (2);
						String car_id = fIcC_n+"_"+bIcC_n;
					if(incomCar_number.length() !=5) {
							System.out.println(" 5자리숫자를 입력해주세요. ");
					}else {
						dtos2 = dao2.getList(car_id);
						if(dtos2.size() == 0) System.out.println(" 렌트내역이 존재 하지않습니다. ");
						if(dtos2.size() != 0) {
						System.out.println(util.getRPad("", 65, "="));
						System.out.println("렌트ID\t\t회원명\t차종류\t주행거리\t렌트시작일\t\t렌트종료일");
						System.out.println(util.getRPad("", 65, "-"));
						for(int k=0; k<dtos2.size(); k++) {
							System.out.print(dtos2.get(k).getRent_id()+"\t");
							System.out.print(dtos2.get(k).getName()+"\t");
							System.out.print(dtos2.get(k).getCar_name()+"\t");
							System.out.print(util.getRPad(dtos2.get(k).getDriving_km()+"km",5," ")+"\t");
							System.out.print(dtos2.get(k).getRent_start_date()+"\t");
							System.out.print(dtos2.get(k).getRent_return_date()+"\n");
						}
							System.out.println(util.getRPad("", 65, "="));
							System.out.println(" ");
						}
					}		
					}while(incomCar_number.length() !=5);
		}
		}while(gubun == 1 || gubun != 0);
	}
}
