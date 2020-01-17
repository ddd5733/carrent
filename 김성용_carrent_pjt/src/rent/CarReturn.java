package rent;

import java.util.ArrayList;
import java.util.Scanner;

import InterCommon.InterCarRent;
import common.CommonUtil;
import dao.CarRent_DAO;
import dao.CarReturn_DAO;
import dao.Member_DAO;
import dto.CarRent_DTO;
import dto.Member_DTO;

public class CarReturn implements InterCarRent{
	public void proc(){
		Scanner sc = new Scanner(System.in);
		CommonUtil util = new CommonUtil();
		ArrayList<CarRent_DTO> dtos = null;
		CarRent_DTO dto = new CarRent_DTO();
		CarReturn_DAO dao = new CarReturn_DAO();
		Member_DAO dao3 = new Member_DAO();
		ArrayList<Member_DTO> dtos3 = null;
		Member_DTO dto3 = null;
		String query ="";
		int gubun =0;
		do {
			System.out.println(" 차량 반납[1], 반납취소[2] 이전으로[0] ");
			gubun= util.inputNumberOnly();
			if(gubun==1) {
				dtos = dao.getRentInfoList();
				if(dtos.size()==0) {
					System.out.println("반납하실 내역이 존재하지 않습니다.");
					break;
				}
					
				 System.out.println("렌트번호\t\t차종류\t대여자\t주행거리\t대여시작일\t\t대여상태");
				 System.out.println(util.getRPad("", 60, "-"));
				 for(int k=0; k<dtos.size(); k++) {
					System.out.print(dtos.get(k).getRent_id()+"\t");
					System.out.print(dtos.get(k).getCar_name()+"\t");
					System.out.print(dtos.get(k).getMember_id()+"\t");
					System.out.print(util.getRPad(dtos.get(k).getDriving_km()+"km",5," ")+"\t");
					System.out.print(dtos.get(k).getRent_start_date()+"\t");
					System.out.print(dtos.get(k).getRent_return()+"\n");
				 }
				 	System.out.println(util.getRPad("", 60, "-"));
				 	System.out.println("");
					String rent_number ="";
					 do {
					 	System.out.println("반납하실 렌트번호를 입력해주세요.[xxxx_xxxx]8자리의 숫자만 입력해주세요");
					 	rent_number =sc.next();
					 	if (rent_number.length()!=8) System.out.println(" [xxxx_xxxx] 8자리의 숫자만 입력해주세요 ");
					 	String fri= rent_number.substring (0,4);
						String bri= rent_number.substring (4);
						String rent_id = fri+"_"+bri;
						dto = dao.getrentList(rent_id);
						if(dto==null ) {
							System.out.println(" 조회할수 없습니다");
						} else {
							System.out.println(util.getRPad("", 60, "="));
							System.out.println("렌트번호\t\t차종류\t대여자\t주행거리\t대여시작일\t\t대여상태");
							System.out.println(util.getRPad("", 60, "-"));
							System.out.print(dto.getRent_id()+"\t");
							System.out.print(dto.getCar_name()+"\t");
							System.out.print(dto.getMember_id()+"\t");
							System.out.print(util.getRPad(dto.getDriving_km()+"km",5," ")+"\t");
							System.out.print(dto.getRent_start_date()+"\t");
							System.out.print(dto.getRent_return()+"\n");
							System.out.println(util.getRPad("", 60, "="));
							System.out.println("");
						}		
					 }while(rent_number.length()!=8 || dto==null);
					int answer = 0;
					String rent_return ="";
					do {	
						System.out.println(" 정말 반납 하시겠습니까? 예[1] 아니요[0] ");
						 answer = util.inputNumberOnly();
						 if(answer==1) {
							 int goodday =0;
							 
							System.out.println("오늘의 날짜("+util.getToday()+")로 반납하시 겠습니까?[\"1\"]     \n날짜입력 [\"2\"] ");
							 goodday = util.inputNumberOnly();
							 if(goodday==1) {
								 rent_return = util.getToday();
							 }else if(goodday==2) {
								 String preRentDate ="";
								 boolean ckeckd = true;
								 String last_return ="";
								 
								 do {
									 do {
										System.out.println(" 반납날짜를 입력해주세요. [YYYYMMDD] ");
										preRentDate = sc.next();
										ckeckd = util.checkDateWithout(preRentDate);
										if(ckeckd != true) System.out.println("[YYYYMMDD]형식을 확인해주세요");
									 }while(ckeckd != true);
									 last_return = dto.getRent_start_date().replace("-","");
									 
//										String y = dto.getRent_start_date().substring (0,4);
//										String m = dto.getRent_start_date().substring (5,7);
//										String d = dto.getRent_start_date().substring (8,10);
										rent_return = preRentDate;
//										last_return = y+m+d; 
										if(Integer.parseInt(last_return) > Integer.parseInt(preRentDate)) System.out.println(" 반납일을 확인해주세요.");
									}while(Integer.parseInt(last_return) > Integer.parseInt(preRentDate));
								 
							 }
							int che =0;
							 int km1=0;
							do {
							 System.out.println(" 주행거리를 입력해주세요. ");
							  km1 =util.inputNumberOnly();
							 System.out.println("입력한 주행거리는"+km1+"km 입니다 맞으면[1] 다시입력[0]");
							 che = util.inputNumberOnly();
							 if(che==1) {
								 
							 }
							}while(che==0);
							 query = " update a06_track2_carrent "+
									 " set driving_km = '"+km1+"', rent_return_date = '"+rent_return+"' "+
									 " where   rent_id ='"+dto.getRent_id()+"' ";
							 int result = dao.update(query);
							 if(result==1) {
								 query = " update a06_track2_carinfo " +
										 " set driving_total_km = driving_total_km + '"+km1+"' , status = 'y' "+
										 " where car_id = '"+dto.getCar_id()+"' ";
								 int result2 = dao.update(query);
								System.out.println(" 반납성공 "); 
							 } else {
								 System.out.println(" 반납실패 ");
							 }
						 }else if(answer==0){
							 break;
						 }
					}while(answer!=1);
					
					 
			} else if(gubun ==2) {
					dtos = dao.getCancelList();
					if(dtos.size()==0) {
						System.out.println(" 반납내역이 없습니다. ");
						break;
					}else {
					System.out.println("렌트번호\t\t차종류\t대여자\t주행거리\t대여시작일\t\t대여상태");
					 System.out.println(util.getRPad("", 60, "-"));
					 for(int k=0; k<dtos.size(); k++) {
						System.out.print(dtos.get(k).getRent_id()+"\t");
						System.out.print(dtos.get(k).getCar_name()+"\t");
						System.out.print(dtos.get(k).getMember_id()+"\t");
						System.out.print(util.getRPad(dtos.get(k).getDriving_km()+"km",5," ")+"\t");
						System.out.print(dtos.get(k).getRent_start_date()+"\t");
						System.out.print(dtos.get(k).getRent_return()+"\n");
					 }
					 	System.out.println(util.getRPad("", 60, "-"));
					 	System.out.println("");
					}
						String rent_number ="";
						 do {
						 	System.out.println(" 반납을 취소하실 렌트번호를 입력해주세요. [xxxx_xxxx]8자리의 숫자만 입력해주세요.\n 메인으로[0]");
						 	rent_number =sc.next();
						 	if(rent_number.equals("0")) return;
						 	if (rent_number.length()!=8) System.out.println(" [xxxx_xxxx] 8자리의 숫자만 입력해주세요 ");
						 	String fri= rent_number.substring (0,4);
							String bri= rent_number.substring (4);
							String rent_id = fri+"_"+bri;
							dto = dao.getCancel(rent_id);
							if(dto==null ) {
								System.out.println(" 조회할수 없습니다");
							} else {
								System.out.println(util.getRPad("", 60, "="));
								System.out.println("렌트번호\t\t차종류\t대여자\t주행거리\t대여시작일\t\t대여상태");
								System.out.println(util.getRPad("", 60, "-"));
								System.out.print(dto.getRent_id()+"\t");
								System.out.print(dto.getCar_name()+"\t");
								System.out.print(dto.getMember_id()+"\t");
								System.out.print(util.getRPad(dto.getDriving_km()+"km",5," ")+"\t");
								System.out.print(dto.getRent_start_date()+"\t");
								System.out.print(dto.getRent_return()+"\n");
								System.out.println(util.getRPad("", 60, "="));
								System.out.println("");
							}		
							
							String reply ="";
							 do {
								System.out.println(" 반납 취소를 진행 하시겠습니까? 예[\"y\"] 아니요[\"n\"] ");
								reply = sc.next();
									if(!reply.equals("n") && !reply.equals("y")) System.out.println(" 예[\"y\\\"] 아니요[\"n\"] 중 선택해주세요 ");
									if(reply.equals("y")) {
										query = " update a06_track2_carrent "+
												" set rent_return_date = null , Driving_km = '0' "+
												" where rent_id = '"+dto.getRent_id()+"' ";
										int result = dao.delete(query);
										if(result ==1) {
											System.out.println("+++++++++++"+dto.getDriving_km());
											query = " update a06_track2_carinfo "+
													" set status = 'n' , driving_total_km = driving_total_km - '"+dto.getDriving_km()+"' "+
													" where car_id = '"+dto.getCar_id()+"' " ;
											int qq = dao.delete(query);
											System.out.println(" 반납이 취소 되었습니다. ");
										}else {
											System.out.println(" 반납이 취소 되지 않았습니다 . ");
										}
									}
							}while(!reply.equals("n") && !reply.equals("y"));
						 }while(rent_number.length()!=8 || dto==null || !rent_number.equals("0"));
			}
		}while(gubun == 1 || gubun == 2);
	}

}
