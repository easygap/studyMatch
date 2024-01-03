package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import common.DBConnPool;

public class GroupDAO extends DBConnPool {

	private DataSource dataSource;
	private Connection con;
	private Statement stmt;
	private PreparedStatement psmt;
	private ResultSet rs;
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	/** 생성자, DB 연결 */
	public GroupDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/dbcp_myoracle");
			con = dataSource.getConnection();
			System.out.println("GroupDAO DB 연동 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** GroupDAO DB 연동 중 예외 발생 ***");
		}
	}

	/** 사용자 정보 가져오기 */
	public String getUserName(String id) {
		String NickName = null;
		String query = "SELECT nickname FROM member WHERE id=?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next())
				NickName = rs.getString("nickname");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 조회중 에러 발생 ***");
		}
		return NickName;
	}
	
	/** 사용자가 그룹에 가입했는지 확인 */
	public int getGroupNum(String ID) {
		String query = "SELECT GROUP_NUM FROM MATCHGROUP WHERE ? IN (ID1, ID2, ID3, ID4, ID5)";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, ID);
			rs = psmt.executeQuery();

			if (rs.next())
				return 1;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 조회중 에러 발생 ***");
		}
		return 0;
	}

	public ArrayList<String> getMemberInterest(String id) {
		int count = 0;
		String query1 = "SELECT COUNT(INTEREST1) + COUNT(INTEREST2) + COUNT(INTEREST3) AS interest FROM MEMBER WHERE id=?";

		try {
			psmt = con.prepareStatement(query1);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("interest");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> interest = new ArrayList<String>(count);

		String query2 = "SELECT INTEREST1, INTEREST2, INTEREST3 FROM member WHERE id=?";

		try {
			psmt = con.prepareStatement(query2);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				if (count == 1)
					interest.add(rs.getString("interest1"));
				else if (count == 2) {
					interest.add(rs.getString("interest1"));
					interest.add(rs.getString("interest2"));
				} else if (count == 3) {
					interest.add(rs.getString("interest1"));
					interest.add(rs.getString("interest2"));
					interest.add(rs.getString("interest3"));
				} else {
					interest.add("error");
				}
			}
			Collections.shuffle(interest);
			System.out.println("데이터베이스에서 interest : " + interest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return interest;

	}

	/** 현재 회원 ID의 주소 정보 가져오기 */
	public String getaddress(String id) {
		String address = null;

		String query = "SELECT address FROM member WHERE id=?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				address = rs.getString("address");
			}

			StringTokenizer st = new StringTokenizer(address);

			st.nextToken();

			address = st.nextToken();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}

	/** 프로필 사진, 이름, 그룹번호 가져오기 */
	public Map<String, List< String>> getProfile(String id) {
		Map<String, List< String>> Profile = new HashMap<>();
	    List<String> Img = new ArrayList<>();
		List<String> Names = new ArrayList<>();
		List<String> Group_num = new ArrayList<>();
		
		String query = "SELECT m.img, m.name, mg.group_num " + " FROM member m "
				+ "JOIN matchgroup mg ON m.id = mg.id1 OR m.id = mg.id2 OR m.id = mg.id3 OR m.id = mg.id4 OR m.id = mg.id5 "
				+ "WHERE mg.group_num IN "
				+ "    (SELECT group_num FROM matchgroup WHERE id1 = ? OR id2 = ? OR id3 = ? OR id4 = ? OR id5 = ?)";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, id);
			psmt.setString(3, id);
			psmt.setString(4, id);
			psmt.setString(5, id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Img.add(rs.getString("img"));
				Names.add(rs.getString("name"));
				Group_num.add(rs.getString("group_num"));
			}
			Profile.put("Img", Img);
			Profile.put("Names", Names);
			Profile.put("group_num", Group_num);
			
		} catch (Exception e) {
			System.out.println("DB 이미지 불러오기 실패");
			e.printStackTrace();
		}
		return Profile;
	}
	
	/** 이전에 매칭된 기록 조회 */
	public Map<String, List<String>> PreviousList(String id){
		Map<String, List< String>> Previous = new HashMap<>();
	    List<String> PreviousImg = new ArrayList<>();
		List<String> PreviousNames = new ArrayList<>();
		
		GroupDTO dto = new GroupDTO();
		
		String query1 = "SELECT GROUP_NUM FROM AGREEMATCH A WHERE A.AGREE_CREATE  = 'N' AND id=?";
		try {
			psmt = con.prepareStatement(query1);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setGroup_Num(rs.getString("GROUP_NUM"));
			}

			System.out.println(" 이전 그룹 매칭되었던 리스트 DB 연결 성공 ! ! ! ");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(" 이전 그룹 매칭되었던 리스트 DB 연결 실패 . . . ");
		}
		
		String query2 = " SELECT M.name, M.img "
				+ " FROM MEMBER M "
				+ " JOIN MATCHGROUP MG ON M.id IN (MG.id1, MG.id2, MG.id3, MG.id4, MG.id5) "
				+ " WHERE MG.GROUP_NUM = ? ";
		
		try {
			psmt = con.prepareStatement(query2);
			psmt.setString(1, dto.getGroup_Num());
			rs = psmt.executeQuery();

			while (rs.next()) {
				PreviousNames.add(rs.getString("name"));
				PreviousImg.add(rs.getString("img"));
			}
			Previous.put("PreviousNames", PreviousNames);
			Previous.put("PreviousImg", PreviousImg);
			
			System.out.println(" 이전 그룹 매칭되었던 리스트 DB연결 후 해당 그룹 이미지 및 이름 불러오기 성공 ! ! ! ");
		} catch (Exception e) {
			System.out.println(" 이전 그룹 매칭되었던 리스트 DB연결 후 해당 그룹 이미지 및 이름 불러오기 실패 . . .");
			e.printStackTrace();
		}
		
		return Previous;
	}

	/** 본인 관심사, 주소와 맞는 그룹의 그룹원, 그룹원 프로필, Group_num 조회 */
	public Map<String, List<String>> getGroupData(String interest, String address, String id) {
		Map<String, List<String>> firstGroup = new HashMap<>();
		List<String> groupName = new ArrayList<>();
		List<String> groupImg = new ArrayList<>();
		List<String> groupNum = new ArrayList<>();
		String query = " WITH RandomGroup AS ("
				+ "    SELECT group_num"
				+ "    FROM matchgroup"
				+ "    WHERE import = ? "
				+ "      AND address LIKE ? AND group_num NOT IN ("
				+ "            SELECT group_num "
				+ "            FROM matchgroup"
				+ "            WHERE ? IN (id1, id2, id3, id4, id5)) "
				+ " ORDER BY DBMS_RANDOM.VALUE"
				+ " FETCH FIRST 1 ROW ONLY )"
				+ " SELECT mg.group_num, m.name, m.img"
				+ " FROM RandomGroup rg"
				+ " JOIN matchgroup mg ON rg.group_num = mg.group_num"
				+ " JOIN member m ON m.id IN (mg.id1, mg.id2, mg.id3, mg.id4, mg.id5)";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, interest);
			psmt.setString(2, "%" + address + "%");
			/** 현재 사용자가 가입한 그룹은 보여주지 않기 */
			psmt.setString(3, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
	            groupName.add(rs.getString("name"));
	            groupImg.add(rs.getString("IMG"));
	            groupNum.add(rs.getString("group_num"));
	        }
			
			if(groupName.size() > 4) {
				groupName.clear();
                groupImg.clear();
                groupNum.clear();
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		firstGroup.put("groupName", groupName);
		firstGroup.put("groupImg", groupImg);
		firstGroup.put("groupNum", groupNum);

		System.out.println("DAO에서 매칭 group의 이름 : " + groupName + ", 이미지 : " + groupImg);
		return firstGroup;
	}

	/** 그룹에 몇명이 들어와 있는지 count하는 쿼리문 */
	public int countGroupMember(String groupnum) {
		int total_count = 0;
		String query = "SELECT COUNT(ID1) + COUNT(ID2) + COUNT(ID3) + COUNT(ID4) + COUNT(ID5) AS total_count FROM MATCHGROUP WHERE group_num = ? ";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, groupnum);

			rs = psmt.executeQuery();

			while (rs.next()) {
				total_count = rs.getInt("total_count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total_count;
	}

	/** 매칭 상세 보기를 눌렀을 때 group_num 값을 받아와서 그룹원 list의 정보를 뿌려주기 */
	public List<String> showGroupMember(String groupNum) {
		Map<String, List<String>> firstGroup = new HashMap<>();
		List<String> groupName = new ArrayList<>();
		List<String> groupImg = new ArrayList<>();
		List<String> groupJob = new ArrayList<>();

		return null;
	}

	/** '매치하기' 눌렀을 때 그룹에 가입하는 쿼리문 */
	public void groupJoin(String groupNum, String id, int total_count) {
		String query1 = "UPDATE MATCHGROUP SET " + "id" + (total_count + 1) + " = ? WHERE GROUP_NUM = ? ";

		try {
			psmt = con.prepareStatement(query1);
			psmt.setString(1, id);
			psmt.setString(2, groupNum);
			rs = psmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}

		String query2 = "INSERT INTO AGREEMATCH(GROUP_NUM, id, AGREE_CREATE) values (?, ?, 'Y')";

		try {
			psmt = con.prepareStatement(query2);
			psmt.setString(1, groupNum);
			psmt.setString(2, id);
			rs = psmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 나이 계산 */
	private int calculateAge(String birthDateStr) {
		int Age = 0;
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        Date birthDate = sdf.parse(birthDateStr);

	        Calendar birthCal = Calendar.getInstance();
	        birthCal.setTime(birthDate);

	        Calendar currentCal = Calendar.getInstance();
	        currentCal.setTime(new Date());

	        int age = currentCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
	        // 생일이 지났는지 체크
	        if (currentCal.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
	            age--;
	            
	        }
	        
	        if(age > 10 && age < 20) {
	        	Age = 10;
	        }else if(age > 20 && age < 30) {
	        	Age = 20;
	        }else if(age > 30 && age < 40) {
	        	Age = 30;
	        }else if(age > 40 && age < 50) {
	        	Age = 40;
	        }else if(age > 50 && age < 60) {
	        	Age = 50;
	        }

	        return Age;
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return 0; // 나이를 계산할 수 없을 경우 0으로 처리하거나 예외처리를 적절히 수정하세요.
	    }
	}

	/** 그룹 매치 전 해당 그룹 상세보기 */
	   public Map<String, List< String>> groupInformation(String groupnum) {
	      Map<String, List< String>> groupInfoList = new HashMap<>();
	      List<String> groupImg = new ArrayList<>();
	      List<String> groupName = new ArrayList<>();
	      List<String> groupAge = new ArrayList<>();
	      List<String> groupJob = new ArrayList<>();
	      List<String> groupinterest1 = new ArrayList<>();
	      List<String> groupinterest2 = new ArrayList<>();
	      List<String> groupinterest3 = new ArrayList<>();
	      
		String query = " SELECT" + "  m.img," + "  m.name," + "  m.birth," + "  m.job," + "  m.interest1,"
				+ "  m.interest2," + "  m.interest3," + "  m.address " + " FROM matchgroup mg "
				+ " JOIN member m ON m.id IN (mg.id1, mg.id2, mg.id3, mg.id4, mg.id5) " + " WHERE mg.group_num = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, groupnum);
			rs = psmt.executeQuery();

			while (rs.next()) {
				groupImg.add(rs.getString("img"));
				groupName.add(rs.getString("name"));
				
				// 나이 계산 및 추가
			    String birthDateStr = rs.getString("birth");
			    int age = calculateAge(birthDateStr);
			    groupAge.add(String.valueOf(age));

				groupJob.add(rs.getString("job"));
				groupinterest1.add(rs.getString("interest1"));
				groupinterest2.add(rs.getString("interest2"));
				groupinterest3.add(rs.getString("interest3"));
			}

			groupInfoList.put("groupImg", groupImg);
			groupInfoList.put("groupName", groupName);
			groupInfoList.put("groupAge", groupAge);
			groupInfoList.put("groupJob", groupJob);
			groupInfoList.put("groupinterest1", groupinterest1);
			groupInfoList.put("groupinterest2", groupinterest2);
			groupInfoList.put("groupinterest3", groupinterest3);

		} catch (Exception e) {
			System.out.println("그룹 정보 불러오기 실패");
			e.printStackTrace();
		}
		return groupInfoList;
	}

	   /** 그룹 회원 탈퇴하기 */
	   public void Leaving(String id, String GroupNum) {
		   String query1 = "UPDATE matchgroup SET id4 = CASE " +
	               "WHEN id4 = ? THEN NULL " +
	               "ELSE id4 END, " +
	               "id5 = CASE " +
	               "WHEN id5 = ? THEN NULL " +
	               "ELSE id5 END " +
	               "WHERE group_num = ? AND (? IN (id1, id2, id3, id4, id5))";
		   
		   try {
				psmt = con.prepareStatement(query1);
				psmt.setString(1, id);
				psmt.setString(2, id);
				psmt.setString(3, GroupNum);
				psmt.setString(4, id);
				rs = psmt.executeQuery();

			} catch (Exception e) {
				e.printStackTrace();
			}

			String query2 = "UPDATE AGREEMATCH SET GROUP_NUM = ? , id = ? , AGREE_CREATE = 'N'";

			try {
				psmt = con.prepareStatement(query2);
				psmt.setString(1, GroupNum);
				psmt.setString(2, id);				 
				rs = psmt.executeQuery();

			} catch (Exception e) {
				e.printStackTrace();
			}
	   }

	   /** Group 생성 시 그룹원 존재 여부 확인 */
	   public int checkId(String[] id) {
		   int idCheck = 1;
		   
		   for(int i = 0; i < id.length; i++) {
			   String query = "select * from member where ID=?"; 
			   
			   try {
				   psmt = con.prepareStatement(query); 
				   psmt.setString(1, id[i]); 
				   System.out.println("DAO 내부) DB에 검색하는 id값(input에 쓴 값) : "+ id[i]); 
				   rs = psmt.executeQuery();
				   if(rs.next()) {
					   idCheck = 1;
					   System.out.println("DAO 내부) id 확인됨");
					   } else {
						   idCheck = 0;
						   System.out.println("DAO 내부) id 확인 X");
					   
						   return idCheck;
					   }
				   } catch (Exception e) {
					   e.printStackTrace();
				   } 
			   }
		   return idCheck;
		 }
	   
	   /** number와 input값을 통해서 MATCHGROUP & AGREEMATCH 테이블 레코드 생성 */
	   public void makeGroup(String GroupNum, String ID, String[] groupID, String important, String address) {
		   String query1 = "INSERT INTO MATCHGROUP (GROUP_NUM, id1, id2, id3, id4, id5, import, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		   String query2 = "INSERT INTO AGREEMATCH (GROUP_NUM, id, agree_create) VALUES (?, ?, ?) ";
		   
		   try { 
			   psmt = con.prepareStatement(query1);
			   psmt.setString(1, GroupNum); 
			   psmt.setString(2, ID); 
		   
		   if(groupID.length == 2) {
			   psmt.setString(3, groupID[0]); 
			   psmt.setString(4, groupID[1]); 
			   psmt.setString(5, null); 
			   psmt.setString(6, null); 
			   psmt.setString(7, important); 
			   psmt.setString(8, address); 
		   } else if(groupID.length == 3) {
			   psmt.setString(3, groupID[0]); 
			   psmt.setString(4, groupID[1]); 
			   psmt.setString(5, groupID[1]); 
			   psmt.setString(6, null); 
			   psmt.setString(7, important); 
			   psmt.setString(8, address); 
		   } else {
			   psmt.setString(3, groupID[0]); 
			   psmt.setString(4, groupID[1]); 
			   psmt.setString(5, groupID[2]); 
			   psmt.setString(6, groupID[3]); 
			   psmt.setString(7, important); 
			   psmt.setString(8, address); 
		   }
		   
		   psmt.executeUpdate();	   
		   
		   for(int i = 0; i <= groupID.length; i++) {
			   psmt = con.prepareStatement(query2);
			   psmt.setString(1, GroupNum); 
			   
			   if(i == 0)
				   psmt.setString(2, ID);  
			   else
				   psmt.setString(2, groupID[i-1]);
	
			   psmt.setString(3, "Y"); 
			   psmt.executeUpdate();
			   
			   } 
		   } catch (SQLException e) {
			   e.printStackTrace();
			   }
		   }
	   
	/** DB 연결 해제 */
	public void close() {
		DBConnPool dbConnPool = new DBConnPool();
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (con != null)
				con.close();
			if (stmt != null)
				stmt.close();
			System.out.println("매칭조회 자원 해제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 매칭조회 자원 해제 중 예외 발생 ***");
		}
		dbConnPool.close();
	}
}
