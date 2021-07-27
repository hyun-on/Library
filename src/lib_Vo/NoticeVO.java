package lib_Vo;

import java.util.Date;

public class NoticeVO {
	private int seq;		//공지사항seq(PK)
	private String title;	//공지제목
	private String content; //공지내용
	private Date date;		//공지날짜
	
	public int getSeq() {
		return seq;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Date getDate() {
		return date;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
