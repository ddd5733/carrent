package rent;

import java.util.ArrayList;
import java.util.Scanner;

import InterCommon.InterCarRent;
import common.CommonUtil;
import dao.CarInfo_DAO;
import dao.CarRent_DAO;
import dao.Member_DAO;
import dto.CarInfo_DTO;
import dto.CarRent_DTO;
import dto.Member_DTO;

public class CarRent implements InterCarRent{
	public void proc(){
		Scanner sc = new Scanner(System.in);
		CommonUtil util = new CommonUtil();
		CarInfo_DAO dao2 = new CarInfo_DAO();
		ArrayList<CarInfo_DTO> dtos2 = null;
		ArrayList<CarRent_DTO> dtos = null;
		Member_DAO dao3 = new Member_DAO();
		ArrayList<Member_DTO> dtos3 = null;
		Member_DTO dto3 = null;
		CarInfo_DTO dto2 = new CarInfo_DTO();
		CarRent_DTO dto = new CarRent_DTO();
		CarRent_DAO dao = new CarRent_DAO();
		String query = "";
		int gubun = 0;
		do {
			System.out.println(" 차량렌트[1] 렌트취소[2] 메인으로[0] ");
			gubun = util.inputNumberOnly();
			if(gubun==1) {
				 dtos2 = dao.getAllList();
				 System.out.println("등록번호\t차종류\t제조사\t제조일자\t주행거리\t렌트가능여부");
				 System.out.println(util.getRPad("", 60, "-"));
				 for(int k=0; k<dtos2.size(); k++) {
					System.out.print(dtos2.get(k).getCar_id()+"\t");
					System.out.print(dtos2.get(k).getCar_name()+"\t");
					System.out.print(dtos2.get(k).getProduce()+"\t");
					System.out.print(dtos2.get(k).getProduce_ym()+"\t");
					System.out.print(util.getRPad(dtos2.get(k).getDriving_total_km()+"km",5," ")+"\t");
					System.out.print(dtos2.get(k).getStatus()+"\n");
				 }
				 	System.out.println(util.getRPad("", 60, "-"));
				 	System.out.println("");
					String incomCar_number = "";
				do {
						System.out.println("렌트하실 차량의 등록번호 5자리 숫자를 입력해주세요.\n(ex)xx_xxx [숫자만 입력하세요] ");
						incomCar_number = sc.next();
						System.out.println("");
						String fIcC_n= incomCar_number.substring (0,2);
						String bIcC_n= incomCar_number.substring (2);
						String car_id = fIcC_n+"_"+bIcC_n;
						if(incomCar_number.length() !=5) {
							System.out.println(" 5자리숫자를 입력해주세요. ");
						}else {
							dto2 = dao.getList2(fIcC_n , bIcC_n);
							if(dto2 == null) {
								System.out.println(" [다시 확인해주세요] ");
							} else {
								System.out.println("등록번호\t차종류\t제조사\t제조일자\t주행거리\t렌트가능여부");
								System.out.println(util.getRPad("", 60, "-"));
									System.out.print(dto2.getCar_id()+"\t");
									System.out.print(dto2.getCar_name()+"\t");
									System.out.print(dto2.getProduce()+"\t");
									System.out.print(dto2.getProduce_ym()+"\t");
									System.out.print(util.getRPad(dto2.getDriving_total_km()+"km",5," ")+"\t");
									System.out.print(dto2.getStatus()+"\n");
							 System.out.println(util.getRPad("", 60, "-"));
							 System.out.println(" ");
							 String all="";
							 dtos3 = dao3.getList("all","");
							 System.out.println(util.getRPad("", 60, "="));
								System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t입사일");
								System.out.println(util.getRPad("", 60, "-"));
								for(int k=0; k<dtos3.size(); k++) {
									System.out.print(dtos3.get(k).getId()+"\t");
									System.out.print(dtos3.get(k).getName()+"\t");
									System.out.print(dtos3.get(k).getAge()+"\t");
									System.out.print(dtos3.get(k).getDept()+"\t");
									System.out.print(dtos3.get(k).getRank()+"\t");
									System.out.print(dtos3.get(k).getAddress()+"\t");
									System.out.print(dtos3.get(k).getReg_date()+"\n");
								}
								System.out.println(util.getRPad("", 60, "="));
								System.out.println(" ");
							 String id = "";
								do {
									System.out.println(" 회원ID를 입력 해주세요  => ");
									id = sc.next();
									 dto3 = dao3.getMemberInfo(id);
									if(dto3==null) {
										System.out.println("검색한 ID가 존재하지 않습니다");
									} else {
										System.out.println(util.getRPad("", 60, "="));
										System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t입사일");
										System.out.println(util.getRPad("", 60, "-"));
											System.out.print(dto3.getId()+"\t");
											System.out.print(dto3.getName()+"\t");
											System.out.print(dto3.getAge()+"\t");
											System.out.print(dto3.getDept()+"\t");
											System.out.print(dto3.getRank()+"\t");
											System.out.print(dto3.getAddress()+"\t");
											System.out.print(dto3.getReg_date()+"\n");
										System.out.println(util.getRPad("", 60, "="));
									}
								}while(id.equals("0") || dto3==null);
								int rgubun =0;
								String rent_id ="";
								System.out.println("오늘의 날짜("+util.getToday()+")로 렌트하시 겠습니까?[\"1\"]     \n예약렌트 [\"2\"], 렌트취소[\"0\"] ");
								rgubun = util.inputNumberOnly();
								if(rgubun==0) {
									break;
								}else if(rgubun==1) {
									String rent_start_date = util.getToday();
									String frent_id = util.getTodayWithoutUnderBar().substring (2,6);
									rent_id = dao.getMaxRentId(frent_id);
									 int result = dao.insertCarRent_commit(rent_id,car_id,id,rent_start_date);
									 if(result==1) {
										 System.out.println("렌트가 완료 되었습니다.");
									 } else {
										 System.out.println("렌트에 실패 하였습니다.");
									 }
								}else if (rgubun==2) {
									String preRentDate ="";
									String today ="";
									boolean ckeckd = true;
									String rent_start_date ="";
								do {
									System.out.println(" 렌트 날짜를 입력해주세요. [YYYYMMDD] ");
									preRentDate = sc.next();
									rent_start_date = preRentDate;
									ckeckd = util.checkDateWithout(preRentDate);
									today = util.getTodayWithoutUnderBar();
									if(ckeckd != true) System.out.println("[YYYYMMDD]형식을 확인해주세요");
									if(Integer.parseInt(today) > Integer.parseInt(preRentDate)) System.out.println("날짜는 오늘이후로만 입력가능합니다.");
									String frent_id = util.getTodayWithoutUnderBar().substring (2,6);
									 rent_id = dao.getMaxRentId(frent_id);
									
								}while(Integer.parseInt(today) > Integer.parseInt(preRentDate) || ckeckd != true);
									int result = dao.insertCarRent(rent_id,car_id,id,rent_start_date);
									if(result==1) {
										System.out.println("렌트가 완료 되었습니다.");
									} else {
									 System.out.println("렌트에 실패 하였습니다.");
								 }
								}
							}
					}
				}while(incomCar_number.length() !=5 || dto2 == null);
			}else if(gubun==2) {
				dtos =dao.getRentInfoList();
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
					do {
					System.out.println("취소할 렌트번호를 입력해주세요.[xxxx_xxxx]8자리의 숫자만 입력해주세요");
				 	rent_number =sc.next();
					}while(rent_number.length()!=8);
				 	if (rent_number.length()!=8) System.out.println(" [xxxx_xxxx] 8자리의 숫자만 입력해주세요 ");
				 	String fri= rent_number.substring (0,4);
					String bri= rent_number.substring (4);
					String rent_id = fri+"_"+bri;
					dto = dao.getrentList(rent_id);
					if(dto==null) {
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
					
						String reply ="";
						do {
							System.out.println(" 렌트 취소를 진행 하시겠습니까? 예[\"y\"] 아니요[\"n\"] ");
							reply = sc.next();
							if(!reply.equals("n") && !reply.equals("y")) System.out.println(" 예[\\\"y\\\"] 아니요[\\\"n\\\"] 중 선택해주세요 ");
							if(reply.equals("y")) {
							query = " delete from a06_track2_carrent "+
									" where rent_id = '"+rent_id+"' ";
							int result = dao.delete(query);
							if(result ==1) {
								System.out.println(" 렌트가 취소 되었습니다. ");
								query = " update a06_track2_carinfo "+
										" set status = 'y' "+
										" where car_id = '"+dto.getCar_id()+"' " ;
								int fres = dao.update(query);
							}else {
								System.out.println(" 렌트가 취소 되지 않았습니다 . ");
							}
							}
						}while(!reply.equals("n") && !reply.equals("y"));
						
					}
					
				 	}while(rent_number.length()!=8 || dto==null);
			}
		}while( gubun == 1 || gubun == 2);
	}
}
