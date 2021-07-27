package lib_Vo;

public class PlaceTotalVO 	{//사물함 과 열람실
	private int seq;		 //seq(PK)
	private int px;			 //방번호     
	private int py;			 //자리번호
	private int pt_status;	 //사용상태 0. 대기중, 1.사용중, 2. 수리중
	private int place;		 //공간상태 0. 사물함, 1.열람실 ............  
	
	
	public int getSeq() {
		return seq;
	}
	public int getPx() {
		return px;
	}
	public int getPy() {
		return py;
	}
	public int getPt_status() {
		return pt_status;
	}
	public int getPlace() {
		return place;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void setPx(int px) {
		this.px = px;
	}
	public void setPy(int py) {
		this.py = py;
	}
	public void setPt_status(int pt_status) {
		this.pt_status = pt_status;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	@Override
	public String toString() {
		return "PlaceTotalVO [seq=" + seq + ", px=" + px + ", py=" + py
				+ ", pt_status=" + pt_status + ", place=" + place + "]";
	}
	
}
