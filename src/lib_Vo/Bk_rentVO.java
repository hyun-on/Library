package lib_Vo;

import java.util.Date;

public class Bk_rentVO {
	private int seq;        //책대여 시퀀스(PK)
	private Date start;     //대여한 날짜
	private Date end;		//대여 종료날짜
	private String user_id;	//유저 아이디(FK)
	private int book_seq;	//도서 시퀀스(FK)
	
	public int getSeq() {
		return seq;
	}
	public Date getStart() {
		return start;
	}
	public Date getEnd() {
		return end;
	}
	public String getUser_id() {
		return user_id;
	}
	public int getBook_seq() {
		return book_seq;
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
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setBook_seq(int book_seq) {
		this.book_seq = book_seq;
	}
	
	@Override
	   public String toString() {
	      return "Bk_rentVO [seq=" + seq + ", start=" + start + ", end=" + end
	            + ", user_id=" + user_id + ", book_seq=" + book_seq + "]";
	   }
	   
	
}
