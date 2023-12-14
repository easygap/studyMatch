package servlet;

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
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		Part filePart = req.getPart("ofile"); // 파일
		String fileName = getFileName(filePart); // 파일명
		String uploadPath = "uploads";
		String realPath = getServletContext().getRealPath(uploadPath);
		File uploadDir = new File(realPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		// sessionID 값 전달
		String EditID = dao.checkSession(num, interest);
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		System.out.println("---------------file edit-------------------");
		System.out.println("Edit Controller에서 num값 : " + num + ", interest값 : " + interest);
		System.out.println("Edit Controller에서 title값 : " + title);
		System.out.println("Edit Controller에서 content값 : " + content);
		System.out.println("수정 파일 경로 : " + uploadPath);
		System.out.println("경로: " + realPath);
		System.out.println("현재 수정 페이지 sessionID : " + sessionID);
		System.out.println("---------------file edit-------------------");

		// sessionID & EditID 값 맞을 때만 아래 조건문 실행되도록 구현
		if (sessionID != null && sessionID.equals(EditID)) {
			// 파일 변경 후 재업로드 및 기존 파일 삭제
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

			// DB 연결
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
	}

	// 기본 파일 이름 가져오기
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