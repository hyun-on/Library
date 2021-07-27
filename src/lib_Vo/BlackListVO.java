package lib_Vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BlackListVO {
	private int seq;        //블랙리스트 seq(PK)
	private Date start;		//블랙리스트 시작일
	private Date end;		//블랙리스트 종료일
	private String user_id; //유저아이디(FK)
	
	public int getSeq() {
		return seq;
	}
	public String getUser_id() {
		return user_id;
	}
	public Date getStart() {
		return start;
	}
	public Date getEnd() {
		return end;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	SimpleDateFormat place = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public String toString() {
		return seq +"    " +place.format(start)+"   "+place.format(end)+"  "+user_id;
		
	}
	
	
	
}
