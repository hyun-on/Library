package lib_Database;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		
		Calendar c = Calendar.getInstance();//캘린더 생성
		c.set(2021, 0, 26, 3, 25);//캘린더로 날짜생성
		Date d = c.getTime();//캘린더에서 만든 날짜 Date에 저장
		System.out.println(new SimpleDateFormat("yyyy-MM-dd  -hh:mm").format(d));
		//Data d 에 저장된 날짜 심플데이터로 출력
		
		
		}
	
	}

