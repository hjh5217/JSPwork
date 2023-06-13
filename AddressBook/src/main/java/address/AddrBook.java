package address;

import java.io.Serializable;

public class AddrBook implements Serializable{
	
	// 버전 아이디
	private static final long serialVersionUID = 10L;
	
	// 필드
	private String username; // 이름
	private String tel; 	 // 전화번호
	private String email; 	 // 이메일
	private String gender; 	 // 성별
	
	//getter/setter
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public AddrBook() {
	
	}
	
	// 생성자
	public AddrBook(String username, String tel, String email, String gender) {
		
		this.username = username;
		this.tel = tel;
		this.email = email;
		this.gender = gender;
	}
	

	
	
	
}
