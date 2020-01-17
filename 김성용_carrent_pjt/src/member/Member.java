package member;
import java.util.ArrayList;
import java.util.Scanner;

import InterCommon.InterCarRent;
import common.CommonUtil;
import dao.Member_DAO;
import dto.Member_DTO;

public class Member implements InterCarRent{

//	public static void main(String[] args) {
	public void proc(){
		Scanner sc = new Scanner(System.in);
		Member_DAO dao = new Member_DAO();
		CommonUtil util = new CommonUtil();
		int gubun = 0;
		do {
			System.out.print("회원검색:[1], 등록:[2], 수정[3], 삭제:[4], [메인으로:0] =>  ");
			gubun = util.inputNumberOnly();
	
			if(gubun==1) {
				int searchGubun = 0;
				do {
					System.out.print("이름검색:[1], 부서검색:[2], 전체출력:[9], [이전메뉴:0] =>  ");
					searchGubun = util.inputNumberOnly();
					if(searchGubun==0) break;
					ArrayList<Member_DTO> dtos = null;
					if(searchGubun==1) {
						System.out.print(" 이름 ? => ");
						String name = sc.next();
						dtos = dao.getList("name",name);
					} else if(searchGubun==2) {
						System.out.println("부서검색 : 회계[10], 인사[20], 서무[30], 영엉[40] =>  ");
						String dept = sc.next();
						dtos = dao.getList("dept",dept);
					} else if(searchGubun==9) {
						String all="";
						dtos = dao.getList("all",all);
					}
					
					if(dtos.size()==0) {
						System.out.println("검색 내용이 존재하지 않습니다.");
					} else {
						System.out.println(util.getRPad("", 60, "="));
						System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t입사일");
						System.out.println(util.getRPad("", 60, "-"));
						for(int k=0; k<dtos.size(); k++) {
							System.out.print(dtos.get(k).getId()+"\t");
							System.out.print(dtos.get(k).getName()+"\t");
							System.out.print(dtos.get(k).getAge()+"\t");
							System.out.print(dtos.get(k).getDept()+"\t");
							System.out.print(dtos.get(k).getRank()+"\t");
							System.out.print(dtos.get(k).getAddress()+"\t");
							System.out.print(dtos.get(k).getReg_date()+"\n");
						}
						System.out.println(util.getRPad("", 60, "="));
					}
				}while(searchGubun==1||searchGubun==2||searchGubun==9);
			
			//등록
			} else if(gubun==2) {
				String name = "";
				do {
					System.out.print("성명 => ");
					name = sc.next();
					if(name.length()>3) System.out.println("성명 3자이내~");
				}while(name.length()>3);
				
				int age = 0;
				do {
					System.out.print("나이 => ");
					age = sc.nextInt();
					if(age>100 || age<1) System.out.println("나이는 1~100살 범위로 입력");
				}while(age>100 || age<1);
				
				String dept_no = "";
				do {
					System.out.print("부서등록 : 회계[10], 인사[20], 서무[30], 영엉[40] => ");
					dept_no = sc.next();
					if(dept_no.length()!=2) System.out.println("부서코드는 2자리로 입력해주세요");
					else if(!dept_no.equals("10")&&!dept_no.equals("20")&&!dept_no.equals("30")&&!dept_no.equals("40")) {
						System.out.println("해당 번호와 일치하는 부서가 존재하지 않습니다.");
					}
				}while(!dept_no.equals("10")&&!dept_no.equals("20")&&!dept_no.equals("30")&&!dept_no.equals("40"));
				
				String rank_no = "";
				do {
					System.out.print("직급등록 : 사원[10], 대리[20], 과장[30], 차장[40], 부장[50] => ");
					rank_no = sc.next();
					if(rank_no.length()!=2) System.out.println("직급코드는 2자리로 입력해주세요");
					if(!rank_no.equals("10")&&!rank_no.equals("20")&&!rank_no.equals("30")&&!rank_no.equals("40")&&!rank_no.equals("50")) {
						System.out.println("해당 번호와 일치하는 직급이 존재하지 않습니다.");
					}
				}while(!rank_no.equals("10")&&!rank_no.equals("20")&&!rank_no.equals("30")&&!rank_no.equals("40")&&!rank_no.equals("50"));
			
				String address = "";
				do {
					System.out.print("주소 => ");
					address = sc.next();
					if(address.length()>25) System.out.println("주소는 25자이내~");
				}while(address.length()>25);
				
				String reg_date = "";
				boolean result = true;
				do {
					System.out.println(" 등록일(YYYY-MM-DD) => ");
					reg_date = sc.next();
					result = util.checkDate(reg_date);
					if(!result) System.out.println("날짜형식 (YYYY-MM-DD) 다시입력 ! ");
				}while(!result);
				
				String id = dao.getMaxId();
//				int res = dao.insertMember(id,name,age,dept_no,rank_no,address,reg_date);
				int res = dao.insertMember_2(id,name,age,dept_no,rank_no,address,reg_date);
//				Member_DTO dto = new Member_DTO(id,name,age,dept_no,rank_no,address,reg_date);
//				int res = dao.insertMember_DTO(dto);
				if(res==1) {
					System.out.println("회원등록 완료~~");
					break;
				} else {
					System.out.println("회원등록 실패~~");
				}
			
			//수정
			} else if(gubun == 3) {
				String id = "";
				do {
					System.out.println(" 수정 ID : ? [이전:0] => ");
					id = sc.next();
					if(id.equals("0")) break;
					
					Member_DTO dto = dao.getMemberInfo(id);
					if(dto==null) {
						System.out.println("검색한 ID가 존재하지 않습니다");
					} else {
						System.out.println(util.getRPad("", 60, "="));
						System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t입사일");
						System.out.println(util.getRPad("", 60, "-"));
							System.out.print(dto.getId()+"\t");
							System.out.print(dto.getName()+"\t");
							System.out.print(dto.getAge()+"\t");
							System.out.print(dto.getDept()+"\t");
							System.out.print(dto.getRank()+"\t");
							System.out.print(dto.getAddress()+"\t");
							System.out.print(dto.getReg_date()+"\n");
						System.out.println(util.getRPad("", 60, "="));
						System.out.println("수정하겠습니까? 예:y, 아니요:n => ");
						String workYN = sc.next();
						if(workYN.equals("y")||workYN.equals("Y")||workYN.equals("ㅛ")) {
							String name = "";
							do {
								System.out.print("성명["+dto.getName()+"] => ");
								name = sc.next();
								if(name.length()>3) System.out.println("성명 3자이내~");
							}while(name.length()>3);
							
							int age = 0;
							do {
								System.out.print("나이["+dto.getAge()+"] => ");
								age = sc.nextInt();
								if(age>100 || age<1) System.out.println("나이는 1~100살 범위로 입력");
							}while(age>100 || age<1);
							
							String dept_no = "";
							do {
								System.out.print("부서등록 ["+dto.getDept()+"]: 회계[10], 인사[20], 서무[30], 영엉[40] => ");
								dept_no = sc.next();
								if(dept_no.length()!=2) System.out.println("부서코드는 2자리로 입력해주세요");
								else if(!dept_no.equals("10")&&!dept_no.equals("20")&&!dept_no.equals("30")&&!dept_no.equals("40")) {
									System.out.println("해당 번호와 일치하는 부서가 존재하지 않습니다.");
								}
							}while(!dept_no.equals("10")&&!dept_no.equals("20")&&!dept_no.equals("30")&&!dept_no.equals("40"));
							
							String rank_no = "";
							do {
								System.out.print("직급등록["+dto.getRank()+"] : 사원[10], 대리[20], 과장[30], 차장[40], 부장[50] => ");
								rank_no = sc.next();
								if(rank_no.length()!=2) System.out.println("직급코드는 2자리로 입력해주세요");
								if(!rank_no.equals("10")&&!rank_no.equals("20")&&!rank_no.equals("30")&&!rank_no.equals("40")&&!rank_no.equals("50")) {
									System.out.println("해당 번호와 일치하는 직급이 존재하지 않습니다.");
								}
							}while(!rank_no.equals("10")&&!rank_no.equals("20")&&!rank_no.equals("30")&&!rank_no.equals("40")&&!rank_no.equals("50"));
						
							String address = "";
							do {
								System.out.print("주소["+dto.getAddress()+"] => ");
								address = sc.next();
								if(address.length()>25) System.out.println("주소는 25자이내~");
							}while(address.length()>25);
							
							String reg_date = "";
							boolean result = true;
							do {
								System.out.println(" 등록일(YYYY-MM-DD) ["+dto.getReg_date()+"] => ");
								reg_date = sc.next();
								result = util.checkDate(reg_date);
								if(!result) System.out.println("날짜형식 (YYYY-MM-DD) 다시입력 ! ");
							}while(!result);
							
							int res = dao.updateMember(id,name,age,dept_no,rank_no,address,reg_date);
							if(res==1) {
								System.out.println("회원정보수정 완료~~");
								break;
							} else {
								System.out.println("회원정보수정 실패~~");
							}
							
						}
					}
				}while(!id.equals("0"));
			//수정
			} else if(gubun==4) {
				String id = "";
				do {
					System.out.println(" 삭제 ID : ? [이전:0] => ");
					id = sc.next();
					if(id.equals("0")) break;
					
					Member_DTO dto = dao.getMemberInfo(id);
					if(dto==null) {
						System.out.println("삭제할 ID가 john재하지 않습니다");
					} else {
						System.out.println(util.getRPad("", 60, "="));
						System.out.println("회원ID\t회원명\t나이\t부서명\t직위\t주소\t입사일");
						System.out.println(util.getRPad("", 60, "-"));
							System.out.print(dto.getId()+"\t");
							System.out.print(dto.getName()+"\t");
							System.out.print(dto.getAge()+"\t");
							System.out.print(dto.getDept()+"\t");
							System.out.print(dto.getRank()+"\t");
							System.out.print(dto.getAddress()+"\t");
							System.out.print(dto.getReg_date()+"\n");
						System.out.println(util.getRPad("", 60, "="));
						System.out.println("삭제하겠습니까? 예:y, 아니요:n => ");
						String workYN = sc.next();
						if(workYN.equals("y")||workYN.equals("Y")||workYN.equals("ㅛ")) {
							int res = dao.deleteMember(id);
							if(res==1) {
								System.out.println("회원정보삭제 완료~~");
								break;
							} else {
								System.out.println("회원정보삭제 실패~~");
							}
						}
					}
				}while(!id.equals("0"));
			}
		}while(gubun==1||gubun==2||gubun==3||gubun==4);
	}
}
