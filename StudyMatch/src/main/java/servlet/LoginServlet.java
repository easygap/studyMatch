package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("/Login.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        String pass = req.getParameter("pass");

        MemberDAO memberDAO = new MemberDAO();
        boolean loginResult = memberDAO.login(id, pass);

        if (loginResult) {
            resp.sendRedirect("List.jsp");
        } else {
            resp.sendRedirect("login.jsp");
        }

        memberDAO.close();
    }
}
