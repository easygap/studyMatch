package model;

public class BoardDTO {
	// 게시글
	private String num;
	private String title;
	private String content;
	private String writer;
	private String visitcount;
	private String likecount;
	private String commcount;
	private java.sql.Date postdate;
	
	// 댓글
	private String commnum;
	private String commcontent;
	private String commlike;
	private String commwriter;
	private java.sql.Date commdate;
	
	public String getNum() {
		return num;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getWriter() {
		return writer;
	}
	public String getVisitcount() {
		return visitcount;
	}
	public String getLikecount() {
		return likecount;
	}
	public String getCommcount() {
		return commcount;
	}
	public java.sql.Date getPostdate() {
		return postdate;
	}
	public String getCommnum() {
		return commnum;
	}
	public String getCommcontent() {
		return commcontent;
	}
	public String getCommlike() {
		return commlike;
	}
	public String getCommwriter() {
		return commwriter;
	}
	public java.sql.Date getCommdate() {
		return commdate;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setVisitcount(String visitcount) {
		this.visitcount = visitcount;
	}
	public void setLikecount(String likecount) {
		this.likecount = likecount;
	}
	public void setCommcount(String commcount) {
		this.commcount = commcount;
	}
	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}
	public void setCommnum(String commnum) {
		this.commnum = commnum;
	}
	public void setCommcontent(String commcontent) {
		this.commcontent = commcontent;
	}
	public void setCommlike(String commlike) {
		this.commlike = commlike;
	}
	public void setCommwriter(String commwriter) {
		this.commwriter = commwriter;
	}
	public void setCommdate(java.sql.Date commdate) {
		this.commdate = commdate;
	}
}