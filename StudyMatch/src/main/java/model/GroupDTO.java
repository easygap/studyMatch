package model;

import java.util.ArrayList;

public class GroupDTO {
	private String NickName;
	private String id;
	private String imageName;
	private String interest1;
	private String interest2;
	private String address;
	private String firstGroup;
	private String secondGroup;
	private String createFirstGroup;
	private String createSecondGroup;

//	private String GR1id1;
//	private String GR1id2;
//	private String GR1id3;
//	private String GR1id4;
//	private String GR1id5;
//	private String GR2id1;
//	private String GR2id2;
//	private String GR2id3;
//	private String GR2id4;
//	private String GR2id5;
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getInterest1() {
		return interest1;
	}

	public void setInterest1(String interest1) {
		this.interest1 = interest1;
	}

	public String getInterest2() {
		return interest2;
	}

	public void setInterest2(String interest2) {
		this.interest2 = interest2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstGroup() {
		return firstGroup;
	}

	public void setFirstGroup(String firstGroup) {
		this.firstGroup = firstGroup;
	}

	public String getSecondGroup() {
		return secondGroup;
	}

	public void setSecondGroup(String secondGroup) {
		this.secondGroup = secondGroup;
	}

	public String getCreateFirstGroup() {
		return createFirstGroup;
	}

	public void setCreateFirstGroup(String createFirstGroup) {
		this.createFirstGroup = createFirstGroup;
	}

	public String getCreateSecondGroup() {
		return createSecondGroup;
	}

	public void setCreateSecondGroup(String createSecondGroup) {
		this.createSecondGroup = createSecondGroup;
	}
	
}
