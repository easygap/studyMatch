package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;
import model.GroupDTO;

@WebServlet("/board/Main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupDAO dao = new GroupDAO();
		GroupDTO dto = new GroupDTO();

		// DTO에 회원 정보 저장
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		String NickName = dao.getUserName(sessionID);
		
		/** 아이디 저장 */
		dto.setId(sessionID);
		/** 닉네임 저장 */
		dto.setNickName(NickName);
		/** 가입한 그룹이 있는지 - 있으면 1, 없으면 0  */
		int status = dao.getGroupNum(sessionID);
		dto.setGroup_status(status);
		System.out.println("그룹에 가입했으니 당신은 : " + status + "의 값을 갖고 있습니다.");
		
		// 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();

        // DateTimeFormatter를 사용하여 날짜를 "yyyy-MM-dd" 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String YearThatYY = currentDate.format(formatter);

        System.out.println("현재 날짜: " + YearThatYY);		

		if (sessionID != null) {
			String interest1 = null;
			String interest2 = null;
			
			// 현재 회원 ID의 주소 정보 가져오기
			String address = dao.getaddress(sessionID);
			/** 주소 저장 */
			dto.setAddress(address);
						
			// 현재 회원 ID의 interest 정보 가져오기
			ArrayList<String> interest = dao.getMemberInterest(sessionID);
			
			/** 그룹을 생성해야 하는지 확인하는 변수 */
			String createGR = "N";
			
			/** 그룹 매칭 관련 변수 전역변수로 만들기 */
			List<String> groupNameList1;
			List<String> groupImgList1;
			List<String> groupNameList2;
			List<String> groupImgList2;
			String firstGroup = "";
			String secondGroup = "";
		
			
			/** 현 로그인 계정의 관심사가 2개 이상일 경우 */
			if (interest.size() != 1) {
				interest1 = interest.get(0);
				interest1 = interest1.trim();
				interest2 = interest.get(1);
				interest2 = interest2.trim();
					
				System.out.println("흥미 : " + interest1 + " 그리고 " + interest2);
				/** 흥미 저장 1 */
				dto.setInterest1(interest1);
				/** 흥미 저장 2 */
				dto.setInterest2(interest2);
				
				System.out.println("관심사 : " + interest1 +", 주소 : " + address);
				
				/** 첫번째 그룹 매치 - 관심사, 주소와 맞는 Group_num 추천 */
				Map<String, List<String>> groupArr1 = dao.getGroupData(interest1, address, sessionID);
				
				/** 첫번째 그룹 매치 - 그룹원 이름 */
				groupNameList1 = groupArr1.get("groupName");

				/** 첫번째 그룹 매치 - 그룹원 프로필 사진 */
				groupImgList1 = groupArr1.get("groupImg");
				
				/** 첫번째 그룹 매치 - Group_Num 값 */
				List<String> groupNum1 = groupArr1.get("groupNum");
				if(!groupNum1.isEmpty())
					firstGroup = groupNum1.get(0);
				
				/** 포워딩된 페이지에서 그룹 이용자들이 들어있는 groupList를 불러옴 */
				req.setAttribute("nameGR1", groupNameList1);
				req.setAttribute("imgGR1", groupImgList1);
				
				/** 두번째 그룹 매치 - 흥미가 2개 이상일 때 interest2 값으로도 그룹 매치 */ 
				Map<String, List<String>> groupArr2 = dao.getGroupData(interest2, address, sessionID);
				
				/** 두번째 그룹 매치 - 그룹원 이름 */
				groupNameList2 = groupArr2.get("groupName");

				/** 두번째 그룹 매치 - 그룹원 프로필 사진 */
				groupImgList2 = groupArr2.get("groupImg");
				
				/** 두번째 그룹 매치 - Group_Num 값 */
				List<String> groupNum2 = groupArr2.get("groupNum");
				if(!groupNum2.isEmpty())
					secondGroup = groupNum2.get(0);				
				
				/** 포워딩된 페이지에서 그룹 이용자들이 들어있는 groupList를 불러옴 */
				req.setAttribute("nameGR2", groupNameList2);
				req.setAttribute("imgGR2", groupImgList2);
	
			} else {	
				/** 현 로그인 계정의 관심사가 한개일 경우 */
				interest1 = interest.get(0);
				interest1 = interest1.trim();
				System.out.println("흥미 : " + interest1);
				/** 흥미 저장 */
				dto.setInterest1(interest1);
				
				System.out.println("관심사 : " + interest1 +", 주소 : " + address);
				
				// 첫번째 그룹 매치 - 관심사, 주소와 맞는 Group_num 추천
				Map<String, List<String>> groupArr1 = dao.getGroupData(interest1, address, sessionID);
				
				/** 첫번째 그룹 매치 - 그룹원 이름 */
				groupNameList1 = groupArr1.get("groupName");

				/** 첫번째 그룹 매치 - 그룹원 프로필 사진 */
				groupImgList1 = groupArr1.get("groupImg");
				
				/** 첫번째 그룹 매치 - Group_Num 값 */
				List<String> groupNum1 = groupArr1.get("groupNum");
				if(!groupNum1.isEmpty())
					firstGroup = groupNum1.get(0);
				
				/** 포워딩된 페이지에서 그룹 이용자들이 들어있는 groupList를 불러옴 */
				req.setAttribute("nameGR1", groupNameList1);
				req.setAttribute("imgGR1", groupImgList1);
								
				// 두번째 그룹 매치 - 흥미가 1개일 때 interest1 값으로만 그룹 매치
//				Map<String, List<String>> groupArr2 = dao.getGroupData(interest1, address, sessionID);

				/** 두번째 그룹 매치 - 그룹원 이름 */
				groupNameList2 = groupArr1.get("groupName");

				/** 두번째 그룹 매치 - 그룹원 프로필 사진 */
				groupImgList2 = groupArr1.get("groupImg");
				
				/** 두번째 그룹 매치 - Group_Num 값 */
//				List<String> groupNum2 = groupArr2.get("groupNum");
//				if(!groupNum2.isEmpty())
//					secondGroup = groupNum2.get(0);
				
				/** 포워딩된 페이지에서 그룹 이용자들이 들어있는 groupList를 불러옴 */
				req.setAttribute("nameGR2", groupNameList2);
				req.setAttribute("imgGR2", groupImgList2);
			}
			if(groupNameList1.isEmpty() || groupNameList2.isEmpty()) {
				createGR = "Y";
				/** 흥미1에 대한 그룹 생성 여부 저장 */
				/**	추후 추가 예정 - 매칭되는 그룹이 없다면 새로운 그룹을 생성해준다. 이때 만들어질 group_num의 이름값을 만드는 요소.	
			// 랜덤으로 선택할 그룹 번호 생성
	        String selectedGroupNum = getRandomGroupNum(groupNum1, groupNum2);
			
	        // interestCode에 할당
	        String MemberInterest = MemberInterest(selectedGroupNum);
	        }
	        **/	
			}
			dto.setFirstGroup(firstGroup);
			dto.setSecondGroup(secondGroup);
			dto.setCreateGroup(createGR);
		}
		
		// request 영역에 DTO 담기
		req.setAttribute("dto", dto);
		
		dao.close();
				
		// jsp 페이지로 forward 이동
		req.getRequestDispatcher("../board/MainPage.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
	/** 추후 개발 - 그룹이 가득 찼을 때 걸러주는 커리문을 위한 id1, id2, id3, id4, id5 갯수 체크 (count가 5 미만일 때만 추천) */
	/** SELECT COUNT(ID1) + COUNT(ID2) + COUNT(ID3) + COUNT(ID4) + COUNT(ID5) AS total_count FROM MATCHGROUP WHERE group_num = '2231101' */
}