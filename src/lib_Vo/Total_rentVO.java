package lib_Vo;

import java.util.Date;

public class Total_rentVO {
	private int seq;		//장소대여seq(PK)
	private Date start;		//통합 대여시작날짜
	private Date end;		//통합 대여종료날짜
	private String userid;	//유저아이디(FK)
	private int pt_seq;		//장소seq(FK)
	
	public int getSeq() {
		return seq;
	}
	public Date getStart() {
		return start;
	}
	public Date getEnd() {
		return end;
	}
	public String getUserid() {
		return userid;
	}
	public int getPt_seq() {
		return pt_seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setPt_seq(int pt_seq) {
		this.pt_seq = pt_seq;
	}
	@Override
	public String toString() {
		return "Total_rentVO [seq=" + seq + ", start=" + start + ", end=" + end
				+ ", userid=" + userid + ", pt_seq=" + pt_seq + "]";
	}
	

}
