package service;

public class ServiceDTO {
	private int inquiry_num;
	private String category_name;
	private String detail_name;
	private String id;
	private String title;
	private String content;
	private String img;
	private String pass;
	private java.sql.Date post_date;
	private String answer_status;
	private java.sql.Date answer_date;
	private String answer_content;
	private String answer_id;
	private int answer_num;
	
	public int getInquiry_num() {
		return inquiry_num;
	}
	public String getCategory_name() {
		return category_name;
	}
	public String getDetail_name() {
		return detail_name;
	}
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getImg() {
		return img;
	}
	public String getPass() {
		return pass;
	}
	public java.sql.Date getPost_date() {
		return post_date;
	}
	public String getAnswer_status() {
		return answer_status;
	}
	public java.sql.Date getAnswer_date() {
		return answer_date;
	}
	public String getAnswer_content() {
		return answer_content;
	}
	public String getAnswer_id() {
		return answer_id;
	}
	public int getAnswer_num() {
		return answer_num;
	}
	
	
	public void setInquiry_num(int inquiry_num) {
		this.inquiry_num = inquiry_num;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public void setDetail_name(String detail_name) {
		this.detail_name = detail_name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setPost_date(java.sql.Date post_date) {
		this.post_date = post_date;
	}
	public void setAnswer_status(String answer_status) {
		this.answer_status = answer_status;
	}
	public void setAnswer_date(java.sql.Date answer_date) {
		this.answer_date = answer_date;
	}
	public void setAnswer_content(String answer_content) {
		this.answer_content = answer_content;
	}
	public void setAnswer_id(String answer_id) {
		this.answer_id = answer_id;
	}
	public void setAnswer_num(int answer_num) {
		this.answer_num = answer_num;
	}

}