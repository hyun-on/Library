package lib_Vo;

public class UserVO {
	private String id; 			//유저아이디(PK)
	private String pw;			//유저비밀번호
	private String name;		//유저이름
	private String tel;			//유저전화번호
	private boolean activity = true;	//회원활동확인
	
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public String getName() {
		return name;
	}
	public String getTel() {
		return tel;
	}
	public boolean isActivity() {
		return activity;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setActivity(boolean activity) {
		this.activity = activity;
	} 

}
