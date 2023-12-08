package member;

public class MemberDTO {
	// 회원 정보
	private String id;
	private String name;
	private String birth;
	private String job;
	private String nick;
	private String pass;
	private String phone;
	private String email;
	private String address;
	private String interest1;
	private String interest2;
	private String interest3;
	private String image;
	
	// 그룹 정보
	private int grNum;
	private String grId1;
	private String grId2;
	private String grId3;
	private String grId4;
	private String grId5;
	private String grImport;
	private String grAddress;
	
	// 개인 및 그룹 일정
	private java.sql.Date dday;
	private String todo;
	private java.sql.Date grDday;
	private String grTodo;
	private String grLoca;
	
	public MemberDTO() {
	}
	
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public String getPass() {
		return pass;
	}
	public String getBirth() {
		return birth;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getNick() {
		return nick;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getInterest1() {
		return interest1;
	}
	public String getInterest2() {
		return interest2;
	}
	public String getInterest3() {
		return interest3;
	}	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getGrNum() {
		return grNum;
	}
	public String getGrId1() {
		return grId1;
	}
	public String getGrId2() {
		return grId2;
	}
	public String getGrId3() {
		return grId3;
	}
	public String getGrId4() {
		return grId4;
	}
	public String getGrId5() {
		return grId5;
	}
	public String getGrImport() {
		return grImport;
	}
	public String getGrAddress() {
		return grAddress;
	}
	public java.sql.Date getDday() {
		return dday;
	}
	public String getTodo() {
		return todo;
	}
	public java.sql.Date getGrDday() {
		return grDday;
	}
	public String getGrTodo() {
		return grTodo;
	}
	public String getGrLoca() {
		return grLoca;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setInterest1(String interest1) {
		this.interest1 = interest1;
	}
	public void setInterest2(String interest2) {
		this.interest2 = interest2;
	}
	public void setInterest3(String interest3) {
		this.interest3 = interest3;
	}
	public void setGrNum(int grNum) {
		this.grNum = grNum;
	}
	public void setGrId1(String grId1) {
		this.grId1 = grId1;
	}
	public void setGrId2(String grId2) {
		this.grId2 = grId2;
	}
	public void setGrId3(String grId3) {
		this.grId3 = grId3;
	}
	public void setGrId4(String grId4) {
		this.grId4 = grId4;
	}
	public void setGrId5(String grId5) {
		this.grId5 = grId5;
	}
	public void setGrImport(String grImport) {
		this.grImport = grImport;
	}
	public void setGrAddress(String grAddress) {
		this.grAddress = grAddress;
	}
	public void setDday(java.sql.Date dday) {
		this.dday = dday;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public void setGrDday(java.sql.Date grDday) {
		this.grDday = grDday;
	}
	public void setGrTodo(String grTodo) {
		this.grTodo = grTodo;
	}
	public void setGrLoca(String grLoca) {
		this.grLoca = grLoca;
	}
}