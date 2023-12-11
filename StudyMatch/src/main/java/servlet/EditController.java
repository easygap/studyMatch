package servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.BoardDAO;
import model.BoardDTO;

@WebServlet("/board/edit.do")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		String num = req.getParameter("num");
		String interest = req.getParameter("interest");
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		BoardDTO dto = new BoardDTO();
		
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
			
			String imgNameToDelete = dao.modifyNameIMG(num, interest);
			
			File toDeleteFile = new File(imgNameToDelete);
			toDeleteFile.delete();
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