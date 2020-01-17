package histroy;

import java.util.ArrayList;
import java.util.Scanner;

import InterCommon.InterCarRent;
import common.CommonUtil;
import dao.MemberHistory_DAO;
import dao.Member_DAO;
import dto.MemberHistory_DTO;
import dto.Member_DTO;

public class MemberHistory implements InterCarRent{
	Scanner sc = new Scanner(System.in);
	Member_DAO dao = new Member_DAO();
	CommonUtil util = new CommonUtil();
	ArrayList<Member_DTO> dtos = null;
	ArrayList<MemberHistory_DTO> dtos2 = null;
	MemberHistory_DAO dao2 = new MemberHistory_DAO();
	public void proc(){
		int gubun = 0;
		do{
			System.out.println(" 조회[1], 이전[0] ");
			gubun=util.inputNumberOnly();
			if(gubun==0) break;
			dtos = dao.getList("all","");
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
			System.out.println(" ");
			String id ="";
			do {
			System.out.println(" 렌트내역을 조회 하실 id를 입력해주세요 이전으로[0] ");
			id = sc.next();
			if(id.equals("0")) break;
			Member_DTO dto = dao.getMemberInfo(id);
			if(dto==null) {
				System.out.println("검색한 ID가 존재하지 않습니다");
			} else {
				dtos2 = dao2.getList(id);
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
			}while(dtos2.size() == 0);
		}while(gubun == 1 || gubun != 0);
		
	}
}
