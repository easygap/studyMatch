package model;

public class CommentDTO {
	private String inter_num;
	private String board_num;
	private String commen_num;
	private String content;
	private String id;
	private java.sql.Date commen_date;
	private String like_count;
	private boolean isModified;
	 
	public boolean isModified() {
		return isModified;
	}

	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}

	public CommentDTO() {
	}

	public String getInter_num() {
		return inter_num;
	}

	public String getBoard_num() {
		return board_num;
	}

	public String getCommen_num() {
		return commen_num;
	}

	public String getContent() {
		return content;
	}

	public String getId() {
		return id;
	}

	public java.sql.Date getCommen_date() {
		return commen_date;
	}

	public String getLike_count() {
		return like_count;
	}

	public void setInter_num(String inter_num) {
		this.inter_num = inter_num;
	}

	public void setBoard_num(String board_num) {
		this.board_num = board_num;
	}

	public void setCommen_num(String commen_num) {
		this.commen_num = commen_num;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCommen_date(java.sql.Date commen_date) {
		this.commen_date = commen_date;
	}

	public void setLike_count(String like_count) {
		this.like_count = like_count;
	}
}
