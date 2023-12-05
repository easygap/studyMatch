package model;

public class BoardDTO {
	// 게시글
	private String groud_num;
	private String inter_num;
	private String board_num;
	private String title;
	private String content;
	private String img;
	private String id;
	private String visit_count;
	private String like_count;
	private java.sql.Date post_date;
	
	// 댓글
	private String commen_num;
	private String commen_content;
	private String commen_id;
	private java.sql.Date commen_date;
	private String commen_like;
//	private String commen_count;
	
	public BoardDTO() {
	}

	public String getGroud_num() {
		return groud_num;
	}

	public String getInter_num() {
		return inter_num;
	}

	public String getBoard_num() {
		return board_num;
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

	public String getId() {
		return id;
	}

	public String getVisit_count() {
		return visit_count;
	}

	public String getLike_count() {
		return like_count;
	}

	public java.sql.Date getPost_date() {
		return post_date;
	}

	public String getCommen_num() {
		return commen_num;
	}

	public String getCommen_content() {
		return commen_content;
	}

	public String getCommen_id() {
		return commen_id;
	}

	public java.sql.Date getCommen_date() {
		return commen_date;
	}

	public String getCommen_like() {
		return commen_like;
	}

	public void setGroud_num(String groud_num) {
		this.groud_num = groud_num;
	}

	public void setInter_num(String inter_num) {
		this.inter_num = inter_num;
	}

	public void setBoard_num(String board_num) {
		this.board_num = board_num;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setVisit_count(String visit_count) {
		this.visit_count = visit_count;
	}

	public void setLike_count(String like_count) {
		this.like_count = like_count;
	}

	public void setPost_date(java.sql.Date post_date) {
		this.post_date = post_date;
	}

	public void setCommen_num(String commen_num) {
		this.commen_num = commen_num;
	}

	public void setCommen_content(String commen_content) {
		this.commen_content = commen_content;
	}

	public void setCommen_id(String commen_id) {
		this.commen_id = commen_id;
	}

	public void setCommen_date(java.sql.Date commen_date) {
		this.commen_date = commen_date;
	}

	public void setCommen_like(String commen_like) {
		this.commen_like = commen_like;
	}

//	public String getCommen_count() {
//		return commen_count;
//	}
//
//	public void setCommen_count(String commen_count) {
//		this.commen_count = commen_count;
//	}
	
}