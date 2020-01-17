package common;
import java.util.*;

import java.text.*;
public class CommonUtil{
Scanner sc = new Scanner(System.in);
	//입력값 숫자만 받기
	public int inputNumberOnly() {
		int number=0;
		while(true) {
			try{
				number = sc.nextInt();
				break;
			}
			catch(InputMismatchException e){
				sc = new Scanner(System.in);
				System.out.println(" 숫자만 입력해주세요 ");
				System.out.print(" 다시입력 : ");
			}
			catch(Exception e){
			}
		}
		return number;
	}
	
	
	// 날짜형식 검사 -없이
	public boolean checkDateWithout(String checkDate){
		SimpleDateFormat dateFormat = 
			new SimpleDateFormat("yyyyMMdd");
		boolean checkD = true;
		dateFormat.setLenient(false);
		try{
			dateFormat.parse(checkDate);
		}catch(ParseException e){
			checkD = false;
		}
		return checkD;
	}	
	
	//잘라서 년월구하기
		public String getTodayWithoutUnderBar(){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			String today = sdf.format(date);
			return today;
		}
		
	//오늘날짜
	public String getToday(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String today = sdf.format(date);
		return today;
	}
	//제조일자검사 yyyyMM형
	public boolean checkYm(String checkDate){
		SimpleDateFormat dateFormat = 
			new SimpleDateFormat("yyyyMM");
		boolean checkD = true;
		dateFormat.setLenient(false);
		try{
			dateFormat.parse(checkDate);
		}catch(ParseException e){
			checkD = false;
		}
		return checkD;
	}	
	
	
	// 날짜형식 검사
	public boolean checkDate(String checkDate){
		SimpleDateFormat dateFormat = 
			new SimpleDateFormat("yyyy-MM-dd");
		boolean checkD = true;
		dateFormat.setLenient(false);
		try{
			dateFormat.parse(checkDate);
		}catch(ParseException e){
			checkD = false;
		}
		return checkD;
	}	
	// 부족한 자리수 만큼 채우기 왼쪽채우기
	public static String getLPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str = strFillText + str;
        }
        return str;
    }
	// 부족한 자리수 만큼 채우기 양쪽채우기
	public static String getCPad(String str, int size, String strFillText) {
        int intPadPos = 0;
        for(int i = (str.getBytes()).length; i < size; i++) {
            if(intPadPos == 0) {
                str += strFillText;
                intPadPos = 1;
            } else {
                str = strFillText + str;
                intPadPos = 0;
            }
        }
        return str;
    }
	// 부족한 자리수 만큼 채우기 오른쪽채우기
	public static String getRPad(String str, int size, String strFillText) {
        for(int i = (str.getBytes()).length; i < size; i++) {
            str += strFillText;
        }
        return str;
        
    }
}

