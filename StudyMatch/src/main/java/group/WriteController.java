package group;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.GroupBoardDAO;
import model.GroupBoardDTO;

@WebServlet("/board/GroupWrite.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 파일 임시저장 사이즈 (파일 최대 용량 초과 시 사용)
		maxFileSize = 1024 * 1024 * 50, // 개별 최대 파일 사이즈
		maxRequestSize = 1204 * 1204 * 50 * 5) // 서버로 전송되는 파일의 최대 사이즈
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.getWriter().append("Served at: ").append(req.getContextPath());
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupBoardDAO dao = new GroupBoardDAO();
		System.out.println("DAO 객체 생성 확인: " + (dao != null));
		GroupBoardDTO dto = new GroupBoardDTO();
		System.out.println("DTO 객체 생성 확인: " + (dto != null));

//		String filePath = req.getParameter("path"); // 이미지 파일 경로
		Part filePart = req.getPart("ofile"); // 파일
		String fileName = getFileName(filePart); // 파일명

		if (fileName != null && !fileName.isEmpty()) {
			// 파일이 선택된 경우에만 실행
			String uploadPath = "uploads";
			String realPath = getServletContext().getRealPath(uploadPath);
			System.out.println("경로: " + realPath);
			File uploadDir = new File(realPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			String ext = "";
			if (fileName.contains(".")) {
				String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
				ext = fileName.substring(fileName.lastIndexOf("."));
				String newFileName = now + ext;

				try (OutputStream out = new FileOutputStream(new File(uploadDir, newFileName));
						InputStream input = filePart.getInputStream()) {
					int read;
					byte[] bytes = new byte[1024];
					while ((read = input.read(bytes)) != -1) {
						out.write(bytes, 0, read);
					}
					System.out.println("파일명: " + newFileName + " 경로: " + uploadPath + " 생성 완료");
					dto.setImg(newFileName);
				} catch (FileNotFoundException f) {
					f.printStackTrace();
					System.out.println("*** 파일 생성 중 예외 발생 ***");
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("*** 파일 업로드 중 예외 발생 ***");
				}
			}
		}

		// 글쓰기
		HttpSession session = req.getSession();
		Object userObject = session.getAttribute("user");
		String userId = null;
		String GroupNum = req.getParameter("groupnum");
		System.out.println("controller에서 groupnum 값은 : " + GroupNum);

		if (userObject != null) {
			userId = userObject.toString();
			System.out.println("게시글 작성 - userId 생성 성공");
		} else {
			resp.sendRedirect("../GroupBoard/Write.jsp?groupnum=" + GroupNum);
			System.out.println("userId 생성 실패");
			return; // 메서드 종료
		}

		dto.setId(userId);
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setGroup_num(GroupNum);

		int result = dao.insertWrite(dto);
		dao.close();

		if (result == 1) {
			resp.sendRedirect("../board/GroupList.do?groupnum=" + GroupNum);
			System.out.println("게시판: " + GroupNum + " 게시글 업로드 완료");
		} else {
			resp.sendRedirect("../GroupBoard/Write.jsp?groupnum=" + GroupNum);
			System.out.println("*** 게시글 업로드 실패 ***");
		}
	}

	private String getFileName(Part part) {
		String partHeader = part.getHeader("content-disposition");
		System.out.println("partHeader : " + partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 2, content.length() - 1);
			}
		}
		return null;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.getWriter().append("Served at: ").append(req.getContextPath());
		String GroupNum = req.getParameter("groupnum");
		RequestDispatcher dis = req.getRequestDispatcher("../GroupBoard/Write.jsp?groupnum=" + GroupNum);
		dis.forward(req, resp);
	}
}