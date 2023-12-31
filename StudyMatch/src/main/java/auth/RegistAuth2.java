package auth;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import member.MemberDAO;
import member.MemberDTO;
import utils.JSFunction;

@WebServlet("/auth/Regist.do")
public class RegistAuth2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req, resp);
		req.setCharacterEncoding("UTF-8");

		// 파일 업로드 처리
		String saveDirectory = req.getServletContext().getRealPath("/MyProfile");

		// 파일 용량
		int maxPostSize = 1024 * 1000; // 1MB
		String encoding = "UTF-8";
		MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxPostSize, encoding);

		// 콘솔 확인
		System.out.println("---------------------- Regist ----------------------");
		System.out.println("doPost()");
		System.out.println(saveDirectory);
		System.out.println("[ " + mr.getParameter("id") + " ]");

		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();

		// parameter 로 값 받아오기
		String pw = mr.getParameter("pw");
		String pwcheck = mr.getParameter("pwcheck");

		boolean Regist = false;
		String idC = null;
		String nickChe = null;

		try {

			String uri = req.getRequestURI();

			// 회원가입 DB 연결
			if (uri.indexOf("Regist.do") != -1) {
				dto.setId(mr.getParameter("id"));

				// 비밀번호 같은지 다른지 확인
				if (pw.equals(pwcheck)) {
					dto.setPass(pw);
					System.out.println("비밀번호 동일");
				} else {
					System.out.println("비밀번호 다름");
				}
				dto.setName(mr.getParameter("name"));
				dto.setNick(mr.getParameter("nickName"));
				dto.setBirth(mr.getParameter("birth"));
				dto.setPhone(mr.getParameter("phone"));
				dto.setAddress(mr.getParameter("address"));
				dto.setEmail(mr.getParameter("Email"));
				dto.setJob(mr.getParameter("job"));
				if (mr.getParameter("interests") != null) {
					String[] interest = mr.getParameterValues("interests");
					for (String s : interest) {
						System.out.println("[ " + s + " ]");
					}
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
				// 이미지 저장 경로 파일명 변경
				String fileName = mr.getFilesystemName("img");
				if (fileName != null) {
					String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
					String ext = fileName.substring(fileName.lastIndexOf("."));
					String newFileName = now + ext;

					File oldFile = new File(saveDirectory + File.separator + fileName);
					File newFile = new File(saveDirectory + File.separator + newFileName);
					oldFile.renameTo(newFile);

					dto.setImage(newFileName);
					System.out.println("newFileName : " + newFileName);
				}

				Regist = dao.signUp(dto);
				idC = dao.idCheck(mr.getParameter("id"));
				nickChe = dao.nickCheck(mr.getParameter("nickName"));

				// 회원가입 성공 알람창
				if (Regist == true) {
					JSFunction.alertRegist(resp, "회원가입에 성공하였습니다.", "../auth/LoginAuth.do");
				} else if (idC.equals("N")) { // 아이디 중복 알림창
					JSFunction.alertRegistFail(resp, "해당 ' 아이디 '는 이미 사용 중 입니다. 중복확인 후 다시 시도해 주세요.");
				} else if(nickChe.equals("N")) {
					JSFunction.alertRegistFail(resp, "해당 ' 닉네임 '은 이미 사용 중 입니다. 중복확인 후 다시 시도해 주세요.");
				} else if (Regist == false) { // 회원가입 실패 알람창
					JSFunction.alertRegistFail(resp, "회원가입에 실패하였습니다. 다시 확인해 주세요.");
				}
			}
			dao.close();
			// 빈 칸으로 그냥 둘 때는 그냥 다시 원래 페이지로 백
			if (Regist == false) {
				JSFunction.alertRegistEmpty(resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("---------------Regist doGet()----------------");
		req.getRequestDispatcher("/auth/Regist.jsp").forward(req, resp);
	}
}
