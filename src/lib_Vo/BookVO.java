package lib_Vo;

public class BookVO {
	private int seq;		//책seq(PK)
	private String bookname;//책이름
	private String publiser;//출판사
	private boolean book_state = true;//대여상태(대여가능 true/false면 대여불가 : 대여중)
	private boolean bookActivate = true;//활성화여부
	private int genre_seq;	//책장르seq(FK)
	
	public int getSeq() {
		return seq;
	}
	public String getBookname() {
		return bookname;
	}
	public String getPubliser() {
		return publiser;
	}
	public int getGenre_seq() {
		return genre_seq;
	}
	public boolean isBook_state() {
		return book_state;
	}
	public boolean isBookActivate() {
		return bookActivate;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public void setPubliser(String publiser) {
		this.publiser = publiser;
	}
	public void setGenre_seq(int genre_seq) {
		this.genre_seq = genre_seq;
	}
	public void setBook_state(boolean book_state) {
		this.book_state = book_state;
	}
	public void setBookActivate(boolean bookActivate) {
		this.bookActivate = bookActivate;
	}
	

	   
	

}
