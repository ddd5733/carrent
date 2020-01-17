package car;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Scanner;

import InterCommon.InterCarRent;
import common.CommonUtil;
import dao.CarInfo_DAO;
import dto.CarInfo_DTO;

public class CarInfo implements InterCarRent{

	//public static void main(String[] args) {
	public void proc(){
	CarInfo_DAO dao = new CarInfo_DAO();
	Scanner sc = new Scanner(System.in);
	CommonUtil util = new CommonUtil();
	ArrayList<CarInfo_DTO> dtos = null;
	
	int gubun =0;
do {
	System.out.println(" [차량] 조회[1], 등록[2], 수정[3], 삭제[4], 이전[0] ");
	gubun = util.inputNumberOnly();
		if(gubun == 1) {
			int selectgubun = 0;
		do {
				System.out.println(" [차량조회] 차량명조회 [1], 제조사조회[2], 전체[9], 이전[0] ");
				 selectgubun = util.inputNumberOnly();
				 System.out.println(" ");
				if(selectgubun == 0) break;
				if(selectgubun == 1) {
					String car_name ="";
					do {
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
						 System.out.println(" ");
						 System.out.println(" [조회하실 차량명을 입력해주세요] 이전[0]");
						 car_name = sc.next(); 
						 dtos = dao.getList("car_name",car_name);
						 if(dtos.size() == 0 && !car_name.equals("0")) {
							 System.out.println(" [차량을 조회할수없습니다. 다시확인해주세요.]");
						 }else if(dtos.size() != 0) {
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
							 System.out.println(" ");
						 }
						 if(car_name.length() > 20) System.out.println(" [차량이름을 20자이내로 입력 해주세요] ");
						 if(car_name.equals("0")) break; 
					}while(car_name.length() > 20 || dtos.size() ==0);
				} else if(selectgubun == 2) {
					int produceselect = 0;
					do {
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
						System.out.println(" 기아[1], 현대[2], 삼성[3], 쌍용[4] 이전[0]");
						produceselect = util.inputNumberOnly();
						String produce ="";
						if(produceselect==1) {
							produce = "기아";
						} else if(produceselect==2) {
							produce = "현대";
						} else if(produceselect==3) {
							produce = "삼성";
						} else if(produceselect==4) {
							produce = "쌍용";
						} else if(produceselect==0) {
							break;
						}else {
							System.out.println(" [1~4중선택해주세요] ");
						}
						
						 dtos = dao.getList("produce",produce);
						 if(dtos.size() == 0) {
							 System.out.println(" [차량을 조회할수없습니다. 다시확인해주세요.]");
						 }else if(dtos.size() != 0) {
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
						 }
					}while(produceselect != 1 && produceselect != 2 && produceselect != 3 && produceselect != 4 );
				} else if(selectgubun == 9) {
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
				}
			}while(selectgubun==1||selectgubun==2||selectgubun==9);
		} else if (gubun == 2) {
			String car_name ="";
			String produce_ym ="";
			String produce ="";
			//do {
				do {
					System.out.println("등록하실 차량 이름은? ");
					car_name = sc.next();
					if(car_name.length() > 20) System.out.println(" [차량이름을 20자이내로 입력 해주세요] ");
				} while(car_name.length() > 20 );
				boolean result = true;
				do {
					System.out.println("차의 제조일자[yyyymm]를 입력해주세요. ");
					produce_ym = sc.next();
					result = util.checkYm(produce_ym);
					if(!result) System.out.println("차의 제조일자[yyyymm]의 형식이 맞지않습니다.");
				} while(produce_ym.length() > 6 || !result );
				int produceselect = 0;
				do {
					System.out.println(" 기아[1], 현대[2], 삼성[3], 쌍용[4] 이전[0]");
					produceselect = util.inputNumberOnly();
					if(produceselect==1) {
						produce = "기아";
					} else if(produceselect==2) {
						produce = "현대";
					} else if(produceselect==3){
						produce = "삼성";
					} else if(produceselect==4) {
						produce = "쌍용";
					} else {
						System.out.println(" [1~4중선택해주세요] ");
					}
					
				}while(produceselect != 1 && produceselect != 2 && produceselect != 3 && produceselect != 4 );
				String car_id =dao.getMaxCarId();
				int result2 = dao.insertCarInfo(car_id,car_name,produce,produce_ym);
				if(result2 == 1) {
					System.out.println(" [입력하신 차가 등록 되었습니다] ");
				} else {
					System.out.println(" [입력과정중 오류가 발생했습니다] ");
				}
		} else if(gubun == 3) {
			String fcar_id ="";
			do{
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
				System.out.println("수정하실 등록번호의 앞두자리를 입력해주세요 이전[0]");
				fcar_id =sc.next();
				System.out.println("");
				dtos = dao.getList("ah",fcar_id);
				if(fcar_id.length() != 2) {
					System.out.println("2자리로 입력해주세요");
				}else {
					if(dtos.size() == 0) {
						 System.out.println(" [차량을 조회할수없습니다. 다시확인해주세요.]");
					} else if(dtos.size() != 0) {
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
					}
				}
			}while(!fcar_id.equals("0") && fcar_id.length() != 2 || dtos.size() == 0);
				String bcar_id ="";
			do {
				System.out.println("등록번호의 뒷3자리를 입력해주세요");
				bcar_id = sc.next();
				System.out.println("");
				dtos = dao.getList2(fcar_id,bcar_id);
				if(bcar_id.length() != 3) {
					System.out.println("3자리로 입력해주세요");
				}else {
					if(dtos.size() == 0) {
						 System.out.println(" [차량을 조회할수없습니다. 다시확인해주세요.]");
					} else if(dtos.size() != 0) {
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
					}
				}
			} while(Integer.parseInt(bcar_id) !=0 && bcar_id.length() != 3 || dtos.size() == 0);
				String car_id =fcar_id+"_"+bcar_id;
				System.out.println(car_id);
			int chage = 0;
			String car_name ="";
			String produce ="";
			String produce_ym ="";
			int result =0;
			do {
				System.out.println(" 차량명변경[1] 제조사변경[2] 제조일자[3] 취소[0] ");
				chage = util.inputNumberOnly();
				System.out.println("");
				if(chage ==1) {
					do {
						System.out.println("변경하실 차량명을 입력하세요");
						car_name = sc.next();
						System.out.println("");
						if(car_name.length() > 20) System.out.println(" [차량이름을 20자이내로 입력 해주세요] ");
					} while(car_name.length() > 20 );
					result = dao.updateCarInfo("car_name",car_id,car_name);
					if(result ==1) {
						System.out.println(" 변경이 완료되었습니다. ");
					} else {
						System.out.println(" 변경에 실패 하였습니다. ");
					}
				}else if(chage ==2) {
					int produceselect = 0;
					do {
						System.out.println("변경하실  제조사를 선택해주세요. 기아[1], 현대[2], 삼성[3], 쌍용[4] ");
						produceselect = sc.nextInt();
						if(produceselect==1) {
							produce = "기아";
						} else if(produceselect==2) {
							produce = "현대";
						} else if(produceselect==3){
							produce = "삼성";
						} else if(produceselect==4) {
							produce = "쌍용";
						} else if(produceselect==0) {
							break;
						} else {
							System.out.println(" [1~4중선택해주세요] ");
						}
					}while(produceselect != 1 && produceselect != 2 && produceselect != 3 && produceselect != 4 );
					result = dao.updateCarInfo("produce",car_id,produce);
					if(result ==1) {
						System.out.println(" 변경이 완료되었습니다. ");
					} else {
						System.out.println(" 변경에 실패 하였습니다. ");
					}
				}else if(chage ==3) {
					boolean rrs = true;
					do {
						System.out.println("변경하실 차의 제조일자[yyyymm]를 입력해주세요. ");
						produce_ym = sc.next();
						rrs = util.checkYm(produce_ym);
						if(!rrs) System.out.println("차의 제조일자[yyyymm]의 형식이 맞지않습니다.");
					} while(produce_ym.length() > 6 || !rrs );
					result = dao.updateCarInfo("produce_ym",car_id,produce_ym);
					if(result ==1) {
						System.out.println(" 변경이 완료되었습니다. ");
					} else {
						System.out.println(" 변경에 실패 하였습니다. ");
					}
				}
			} while(chage !=0 && chage !=1 && chage !=2 && chage !=3);
		} else if (gubun == 4) {
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
				System.out.println(" 삭제할 차량등록번호를 입력해주세요[xx_xxx]5자리의 숫자 ");
				incomCar_number = sc.next();
				System.out.println("");
				String fIcC_n= incomCar_number.substring (0,2);
				String bIcC_n= incomCar_number.substring (2);
				String car_id = fIcC_n+"_"+bIcC_n;
				if(incomCar_number.length() !=5) {
					System.out.println(" 5자리숫자를 입력해주세요. ");
				} else {
					dtos = dao.getList2(fIcC_n,bIcC_n);
					if(dtos.size() == 0) {
						System.out.println(" 등록된 차량인지 다시 확인해주세요 ");
					} else {
						System.out.println("==========="+dtos.get(0).getDriving_total_km()+"\t");
						System.out.println("==========="+dtos.size()+"\t");
						
						System.out.println("등록번호\t차종류\t제조사\t제조일자\t주행거리\t렌트가능여부");
						 System.out.println(util.getRPad("", 60, "-"));
						 for(int k=0; k<dtos.size(); k++) {
							System.out.print(dtos.get(k).getCar_id()+"\t");
							System.out.print(dtos.get(k).getCar_name()+"\t");
							System.out.print(dtos.get(k).getProduce()+"\t");
							System.out.print(dtos.get(k).getProduce_ym()+"\t");
							System.out.print(dtos.get(k).getDriving_total_km()+"\t");
							System.out.print(dtos.get(k).getStatus()+"\n");
						 }
					 System.out.println(util.getRPad("", 60, "-"));
					 System.out.println(" ");
					 System.out.println(" 정말 삭제 하시겠습니까? 예[1] 아니요[0]");
					 	int yn = util.inputNumberOnly();
					 if(yn==1) {
						 int dResult = dao.deleteCarinfo(car_id);
							if(dResult ==1) {
								System.out.println(" 삭제가 완료 되었습니다. ");
							} else {
								System.out.println(" 삭제가 진행되지 않았습니다. ");
							}
					 } 
					}
					
				}
			}while(!incomCar_number.equals("0") && incomCar_number.length() !=5 ||dtos.size() == 0);
		
		} 
	}while(gubun==1||gubun==2||gubun==3||gubun==4);
		
	}
}
