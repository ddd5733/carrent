package main;

import java.util.Scanner;

import InterCommon.InterCarRent;
import car.*;
import common.CommonUtil;
import histroy.CarHistory;
import histroy.MemberHistory;
import member.*;
import rent.CarRent;
import rent.CarReturn;


public class CarRentMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CommonUtil util = new CommonUtil();
		int gubun =0;
		do {
		System.out.println("* 회원관리:[1] ");
		System.out.println("* 차량관리:[2] ");
		System.out.println("* 렌트관리:[3] ");
		System.out.println("* 차량반납:[4] ");
		System.out.println("* 차량별 렌트조회:[5] ");
		System.out.println("* 회원별 렌트조회:[6] ");
		System.out.println("* 작업선택 ? => ");
		gubun = util.inputNumberOnly();
		if(gubun == 1) {
//			Member member = new Member();
			InterCarRent member = new Member();
			member.proc();
		} else if(gubun == 2) {
			InterCarRent carInfo = new CarInfo();
			carInfo.proc();
		} else if(gubun == 3) {
			InterCarRent carrent = new CarRent();
			carrent.proc();
		} else if(gubun == 4) {
			InterCarRent carreturn = new CarReturn();
			carreturn.proc();
		} else if(gubun == 5) {
			InterCarRent ch = new CarHistory();
			ch.proc();
		} else if(gubun == 6) {
			InterCarRent mh = new MemberHistory();
			mh.proc();
		}
		}while(gubun != 0);
	}

}
