package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import member.MemberDAO;
import member.MemberDTO;
import utils.FileUtil;
import utils.JSFunction;

@WebServlet("/auth/Regist.do")
public class RegistAuth2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		System.out.println("doPost()");
		String[] interest = req.getParameterValues("interests");

		System.out.println("[ " + req.getParameter("id") + " ]");

		for (String s : interest) {
			System.out.println("[ " + s + " ]");
		}

		MemberDAO dao = new MemberDAO();

		String uri = req.getRequestURI();

		String saveDirectory = req.getServletContext().getRealPath("/Uploads");
		System.out.println("{ " + saveDirectory + " }");

		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));

		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		if (mr == null || mr.getFilesystemName("img") == null) {
			JSFunction.alertLocation(resp, "첨부 파일이 제한 용량을 초과합니다.", "../auth/Regist.do");
		}

		try {
			MemberDTO dto = new MemberDTO();

			if (uri.indexOf("Regist.do") != -1) {
				dto.setId(req.getParameter("id"));
				dto.setPass(req.getParameter("pw"));
				dto.setName(req.getParameter("name"));
				dto.setNick(req.getParameter("nickname"));
				dto.setBirth(req.getParameter("birth"));
				dto.setPhone(req.getParameter("phone"));
				dto.setAddress(req.getParameter("address"));
				dto.setEmail(req.getParameter("Email"));
				dto.setJob(req.getParameter("job"));
				dto.setInterest1(interest[0]);
				dto.setInterest2(interest[1]);
				dto.setInterest3(interest[2]);

				String fileName = mr.getFilesystemName("img");
				if (fileName != null) {
					String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
					String ext = fileName.substring(fileName.lastIndexOf("."));
					String newFileName = now + ext;

					File oldFile = new File(saveDirectory + File.separator + fileName);
					File newFile = new File(saveDirectory + File.separator + newFileName);
					oldFile.renameTo(newFile);

					dto.setImage(mr.getParameter("newFileName"));
					System.out.println("newFileName : " + newFileName);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet()");

		req.getRequestDispatcher("/auth/Regist.jsp").forward(req, resp);
	}

}