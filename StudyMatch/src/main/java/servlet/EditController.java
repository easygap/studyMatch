package servlet;

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
import javax.servlet.http.Part;

import model.BoardDAO;
import model.BoardDTO;

@WebServlet("/board/edit.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 파일 임시저장 사이즈 (파일 최대 용량 초과 시 사용)
		maxFileSize = 1024 * 1024 * 50, // 개별 최대 파일 사이즈
		maxRequestSize = 1204 * 1204 * 50 * 5) // 서버로 전송되는 파일의 최대 사이즈
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();

		String num = req.getParameter("num");
		String interest = req.getParameter("interest");
		System.out.println("Edit Controller에서 num값 : " + num + ", interest값 : " + interest);
		String title = req.getParameter("title");
		System.out.println("Edit Controller에서 title값 : " + title);
		String content = req.getParameter("content");
		System.out.println("Edit Controller에서 content값 : " + content);

		Part filePart = req.getPart("ofile"); // 파일
		String fileName = getFileName(filePart); // 파일명
		String uploadPath = "uploads";
		System.out.println("수정 파일 경로 : " + uploadPath);
		String realPath = getServletContext().getRealPath(uploadPath);
		System.out.println("경로: " + realPath);
		File uploadDir = new File(realPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		String ext = "";
		if (!fileName.equals("")) {
			String imgNameToDelete = dao.modifyNameIMG(num, interest);

			if (imgNameToDelete != null) {
				File toDeleteFile = new File(realPath + File.separator + imgNameToDelete);
				toDeleteFile.delete();
				System.out.println("수정 - 이미지 삭제 성공 " + imgNameToDelete);
			}

			String now = new SimpleDateFormat("yyyyMMdd_HhsS").format(new Date());
			ext = fileName.substring(fileName.lastIndexOf("."));
			String editFileName = now + ext;
			System.out.println("파일명: " + editFileName + " 경로: " + uploadPath + " 생성 완료");

			try (OutputStream out = new FileOutputStream(new File(uploadDir, editFileName));
					InputStream input = filePart.getInputStream()) {
				int read;
				byte[] bytes = new byte[1024];
				while ((read = input.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				dto.setImg(editFileName);
			} catch (FileNotFoundException f) {
				f.printStackTrace();
				System.out.println("*** 수정 파일 생성 중 예외 발생 ***");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("*** 수정 파일 업로드 중 예외 발생 ***");
			}

			File oldFile = new File(uploadDir + File.separator + fileName);
			File newFile = new File(uploadDir + File.separator + editFileName);
			oldFile.renameTo(newFile);

		}

		dto.setTitle(title);
		dto.setContent(content);
		dto.setInter_num(interest);
		dto.setBoard_num(num);

		int result = dao.updateEdit(dto);
		dao.close();
		if (result == 1)
			req.getRequestDispatcher("/board/view.do?interest=" + interest + "&num=" + num).forward(req, resp);
		else
			req.getRequestDispatcher("/board/edit.do?interest=" + interest + "&num=" + num).forward(req, resp);
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
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////		resp.getWriter().append("Served at: ").append(req.getContextPath());
//        String internum = req.getParameter("interest");
//		RequestDispatcher dis = req.getRequestDispatcher("../board/Edit.jsp?interest=" + internum);
//		dis.forward(req, resp);
//	}
}