package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

@WebServlet("/service/ServiceWrite.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 파일 임시저장 사이즈 (파일 최대 용량 초과 시 사용)
		maxFileSize = 1024 * 1024 * 50, // 개별 최대 파일 사이즈
		maxRequestSize = 1204 * 1204 * 50 * 5) // 서버로 전송되는 파일의 최대 사이즈

public class ServiceWriteController extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		ServiceDAO dao = new ServiceDAO();
		ServiceDTO dto = new ServiceDTO();

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
		String userId = (String) session.getAttribute("user");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String pass = req.getParameter("pass");
		
		String category_name = req.getParameter("categorySelect");
		String detail_name = req.getParameter("detailSelect");
		
		System.out.println("-----------------------------");
		System.out.println("category_name: " + category_name);
		System.out.println("detail_name: " + detail_name);
		System.out.println("id: " + userId);
		System.out.println("title: " + title);
		System.out.println("content: " + content);
		System.out.println("pass: " + pass);
		System.out.println("-----------------------------");

		dto.setId(userId);
		dto.setTitle(title);
		dto.setCategory_name(category_name);
		dto.setDetail_name(detail_name);
		dto.setContent(req.getParameter("content"));

		int result = dao.insertService(dto);
		dao.close();

		if (result == 1) {
			resp.sendRedirect("../service/ServiceList.do");
//			System.out.println("게시판: " + internum + " 게시글 업로드 완료");
		} else {
			resp.sendRedirect("../service/ServiceWrite.jsp");
			System.out.println("*** 문의글 업로드 실패 ***");
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

}
