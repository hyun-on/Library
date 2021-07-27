package lib_Vo;

public class GenreVO {
	private int seq;	//장르seq
	private String name;//장르명
	
	public int getSeq() {
		return seq;
	}
	public String getName() {
		return name;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "("+ seq +"  "+name+")";
	} 
	
}
