package servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GroupDAO;

/**
 * Servlet implementation class CheckGroupMember
 */
@WebServlet("/board/CheckGroupMember.do")
public class CheckGroupMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
//		GroupDAO dao = new GroupDAO();
		
		String groupID1 = req.getParameter("group1");
		
		String groupID2 = req.getParameter("group2");
		
		String groupID3 = req.getParameter("group3");
		
		String groupID4 = req.getParameter("group4");
		
		System.out.println("groupID1 : " + groupID1 + "groupID2 : " + groupID2 + "groupID3 : " + groupID3 + "groupID4 : " + groupID4);
	
		String[] strArray = { groupID1, groupID2, groupID3, groupID4 };

		System.out.println("\"AA\" 제거 전: " + Arrays.toString(strArray));
		
		strArray = Arrays.stream(strArray)
				.distinct()
				.filter(s -> (s!=null && s.length() >0 ))
				.toArray(String[]::new);

		  System.out.println("\"AA\" 제거 후: " + Arrays.toString(strArray));
		
//		dao.checkId(groupID1);
	}
}