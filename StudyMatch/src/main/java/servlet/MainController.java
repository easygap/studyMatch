package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

			
			/** 본인이 가입되어있는 그룹인지 확인하는 변수 */
			String invited = "N";
			
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
				Map<String, List<String>> groupArr2;
				do {
					groupArr2 = dao.getGroupData(interest1, address, sessionID);
				}
				while(groupArr2 != groupArr1 && groupArr2 != null);
					
				/** 두번째 그룹 매치 - 그룹원 이름 */
				groupNameList2 = groupArr1.get("groupName");

				/** 두번째 그룹 매치 - 그룹원 프로필 사진 */
				groupImgList2 = groupArr1.get("groupImg");
				
				/** 두번째 그룹 매치 - Group_Num 값 */
				List<String> groupNum2 = groupArr2.get("groupNum");
				if(!groupNum2.isEmpty())
					secondGroup = groupNum2.get(0);
				
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
	
	/** Group_num 생성을 위한 처리 - 현 로그인 계정의 관심사로 한자리수의 코드 부여 */
	private String MemberInterest(String input) {

        String interestCode = "";

        if ("영어".equals(input) || "일본어".equals(input) || "중국어".equals(input)) {
            interestCode = "1";
        } else if ("프론트/백엔드".equals(input) || "프로젝트".equals(input)) {
            interestCode = "2";
        } else if ("디자이너".equals(input) || "퍼블리셔".equals(input) || "프로젝트".equals(input)) {
            interestCode = "3";
        } else if ("엑셀/한글/워드".equals(input) || "회계".equals(input)) {
            interestCode = "4";
        } else if ("부동산".equals(input) || "투자".equals(input)) {
            interestCode = "5";
        } else {
            // 다른 경우에 대한 처리
            interestCode = "0"; // 혹은 다른 값을 지정하거나 필요에 따라 처리하세요.
        }
		return interestCode;
	}
	
	/** group_num 생성을 위한 interest1 혹은 interest2 값을 난수로 준다. */
	private String getRandomGroupNum(String groupNum1, String groupNum2) {
        Random random = new Random();
        int randomNumber = random.nextInt(2); // 0 또는 1 생성

        // 랜덤으로 선택된 번호에 따라 groupNum1 또는 groupNum2 반환
        return (randomNumber == 0) ? groupNum1 : groupNum2;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}