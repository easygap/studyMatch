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

import com.oreilly.servlet.MultipartRequest;

import member.MemberDAO;
import member.MemberDTO;
import utils.JSFunction;

@WebServlet("/auth/Regist.do")
public class RegistAuth2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req, resp);
    	System.out.println("doPost()");
    	
    	req.setCharacterEncoding("UTF-8");
    	
    	// 파일 업로드 처리       
        String saveDirectory = req.getServletContext().getRealPath("/Profile");
        // 파일 용량
        int maxPostSize = 1024 * 1000; // 1MB
        String encoding = "UTF-8";
        MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxPostSize, encoding);
        
        System.out.println("[ " + mr.getParameter("id") + " ]");

        String[] interest = mr.getParameterValues("interests");
        if (interest != null) {
            for (String s : interest) {
                System.out.println("[ " + s + " ]");
            }
        } else {
            System.out.println("[ No interests selected ]");
        }

        MemberDAO dao = new MemberDAO();

        String uri = req.getRequestURI();

        try {	
            MemberDTO dto = new MemberDTO();
            
            // 회원가입 DB 연결
            if (uri.indexOf("Regist.do") != -1) {
                dto.setId(mr.getParameter("id"));
                dto.setPass(mr.getParameter("pw"));
                dto.setName(mr.getParameter("name"));
                dto.setNick(mr.getParameter("nickName"));
                dto.setBirth(mr.getParameter("birth"));
                dto.setPhone(mr.getParameter("phone"));
                dto.setAddress(mr.getParameter("address"));
                dto.setEmail(mr.getParameter("Email"));
                dto.setJob(mr.getParameter("job"));
                if (interest != null) {
                	if(interest.length == 1) {
                    dto.setInterest1(interest[0]);
                	}else if(interest.length == 2) {
                		dto.setInterest1(interest[0]);
                		dto.setInterest2(interest[1]);	
                	}else if(interest.length == 3) {
                		dto.setInterest1(interest[0]);
                		dto.setInterest2(interest[1]);
                		dto.setInterest2(interest[2]);
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

                // 회원가입 성공/실패 알람창
                boolean result = dao.signUp(dto);
                dao.close();
                
				if (result == true) {
					JSFunction.alertRegist(resp, "회원가입에 성공하였습니다.", "../auth/LoginAuth.do");
				} else {
					JSFunction.alertRegist(resp, "회원가입에 실패하였습니다.", "../auth/Regist.do");
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