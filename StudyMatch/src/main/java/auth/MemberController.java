package auth;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;

import member.MemberDAO;
import member.MemberDTO;
import utils.JSFunction;

@WebServlet("/auth/MypageView.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
		maxFileSize = 1024 * 1024 * 5, // 5 MB
		maxRequestSize = 1024 * 1024 * 5 * 5 // 25 MB
)
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// 방법2. session에 담겨있는 loginUser(로그인한 회원정보) 객체에 있는 아이디 꺼내오기
		HttpSession session = req.getSession();
		// session에서 값을 꺼내오면 object 타입으로 가져와지므로 Member로 형변환해야한다.
		String sessionID = (String) session.getAttribute("user");

		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();

		String saveDirectory = req.getServletContext().getRealPath("/MyProfile");

		int maxPostSize = 1024 * 1000; // 1MB
		String encoding = "UTF-8";
		MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxPostSize, encoding);

		String pw = mr.getParameter("pw");
		String pwcheck = mr.getParameter("pwcheck");

		String job = mr.getParameter("job");
		String nick = mr.getParameter("nickName");
		String phone = mr.getParameter("phone");
		String email = mr.getParameter("Email");
		String addres = mr.getParameter("address");
		String[] interest = mr.getParameterValues("interest");

		String newImage = mr.getFilesystemName("img");

		// 기존 이미지 파일명을 가져옴
		String oldImage = mr.getParameter("oldImg");

		String nickChe = null;

		boolean Mypage = false;

		try {

			if (pw.equals(pwcheck)) {
				dto.setPass(pw);
				System.out.println("비밀번호 동일");
			} else {
				System.out.println("비밀번호 다름");
			}

			dto.setJob(job);
			dto.setNick(nick);
			dto.setEmail(email);
			dto.setAddress(addres);
			dto.setPhone(phone);
			if (mr.getParameterValues("interest") != null) {
				if (interest.length == 1) {
					dto.setInterest1(interest[0]);
				} else if (interest.length == 2) {
					dto.setInterest1(interest[0]);
					dto.setInterest2(interest[1]);
				} else if (interest.length == 3) {
					dto.setInterest1(interest[0]);
					dto.setInterest2(interest[1]);
					dto.setInterest3(interest[2]);
				}
			}

			// 기존 이미지가 있는 경우
			if (oldImage != null && !oldImage.isEmpty()) {
				dto.setImage(oldImage);
			}
			if (newImage != null) { // 새로운 이미지가 업로드된 경우

				String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
				String ext = newImage.substring(newImage.lastIndexOf("."));
				String newFileName = now + ext;

				File oldFile = new File(saveDirectory + File.separator + newImage);
				File newFile = new File(saveDirectory + File.separator + newFileName);
				oldFile.renameTo(newFile);

				if (oldImage != null && !oldImage.isEmpty()) {
					File toDeleteFile = new File(saveDirectory + File.separator + oldImage);
					toDeleteFile.delete();
					System.out.println("마이페이지 수정 이미지 삭제 성공 ! " + oldImage);
				}

				dto.setImage(newFileName);
				System.out.println("newFileName : " + newFileName);
			}

			Mypage = dao.updateMypage(dto, sessionID);
			nickChe = dao.nickCheck(nick);

			if (Mypage == true) {
				JSFunction.alertRegist(resp, "마이페이지 정보 변경이 완료되었습니다.", "../auth/Mypage.do");
			} else if(nickChe.equals("N")) {
				JSFunction.alertRegistFail(resp, "해당 닉네임은 이미 사용 중 입니다. 중복확인 후 다시 시도해 주세요.");
			} else if(Mypage == false){
				JSFunction.alertRegistFail(resp, "마이페이지 정보 변경에 실패하였습니다.");
			}

			dao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(job + " / " + nick + " / " + sessionID + " / " + oldImage);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("../auth/MyPage.do").forward(req, resp);
	}
}
