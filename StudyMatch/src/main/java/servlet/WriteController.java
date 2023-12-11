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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import member.MemberDTO;
import model.BoardDAO;
import model.BoardDTO;

@WebServlet("/board/WriteController.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 파일 임시저장 사이즈 (파일 최대 용량 초과 시 사용)
maxFileSize = 1024 * 1024 * 50, // 개별 최대 파일 사이즈
maxRequestSize = 1204 * 1204 * 50 * 5) // 서버로 전송되는 파일의 최대 사이즈
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.getWriter().append("Served at: ").append(req.getContextPath());
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		BoardDTO dto = new BoardDTO();
		
//		String filePath = req.getParameter("path"); // 이미지 파일 경로
		Part filePart = req.getPart("ofile"); // 파일
		String fileName = getFileName(filePart); // 파일명
		String uploadPath = "/uploads";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
		    uploadDir.mkdir();
		}
		if (fileName != null) {
		    String now = new SimpleDateFormat("yyyyMMdd_HhsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			
			File oldFile = new File(uploadDir + File.separator + fileName);
			File newFile = new File(uploadDir + File.separator + newFileName);
			oldFile.renameTo(newFile);
			
			dto.setImg(newFileName);
		}
		
		// 글쓰기
        HttpSession session = req.getSession();
        Object userObject = session.getAttribute("user");
        String userId = null;
        String internum = req.getParameter("interest");
        System.out.println("controller에서 interest값은 : " + internum);

        if (userObject instanceof MemberDTO) {
            MemberDTO sessionDTO = (MemberDTO) userObject;
            userId = sessionDTO.getId();
        } else {
            resp.sendRedirect("../board/Write.jsp?interest=" + internum);
            return; // 메서드 종료
        }

		dto.setId(userId);
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setInter_num(internum);

//		java.util.Date postdate = new java.util.Date();
//		java.sql.Date sqlDate = new java.sql.Date(postdate.getTime());
//		dto.setPost_date(sqlDate);
		
		BoardDAO dao = new BoardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		
		if (result == 1) {
		    resp.sendRedirect("../board/List.jsp?interest=" + internum);
		    System.out.println("게시판: " + internum + "게시글 업로드 완료");
		} else {
		    resp.sendRedirect("../board/Write.jsp?interest=" + internum);
		    System.out.println("*** 게시글 업로드 실패 ***");
		}
		
		// 파일 저장
		try (OutputStream out = new FileOutputStream(new File(uploadPath+File.separator + fileName));
				InputStream input = filePart.getInputStream()) {
			int read = 0; // input이 읽어들인 바이트 수 저장
			byte[] bytes = new byte[1024]; // input이 실제로 읽은 데이터 저장할 배열
			
			while ((read = input.read(bytes)) != -1) { // 파일 데이터 끝까지 읽기
				out.write(bytes, 0, read); // 읽은 데이터를 서버에 반환
			} // inputstream 파일 데이터를 1024 바이트씩 읽고 -> outputstream 쓴다
			System.out.println("파일명: " + fileName + " 경로: " + uploadPath + " 생성 완료");
		} catch (FileNotFoundException f) { // 해당 경로에 파일을 생성하지 못할 경우
			f.printStackTrace();
			System.out.println("*** 파일 생성 중 예외 발생 ***");
		}
	}
	
	/*
	private String getInterest(String url) {
	    int index = url.indexOf("interest=");
	    if (index != -1) {
	        int endIndex = url.indexOf("&", index);
	        if (endIndex != -1) {
	            return url.substring(index + 9, endIndex);
	        } else {
	            return url.substring(index + 9);
	        }
	    }
	    return null; // "interest="이 없는 경우
	}
	*/
	
	private String getFileName(Part part) {
		String partHeader = part.getHeader("content-disposition");
		System.out.println("partHeader : " + partHeader);
		for (String content: part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(
						content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.getWriter().append("Served at: ").append(req.getContextPath());
        String internum = req.getParameter("interest");
		RequestDispatcher dis = req.getRequestDispatcher("../board/Write.jsp?interest=" + internum);
		dis.forward(req, resp);
	}
}