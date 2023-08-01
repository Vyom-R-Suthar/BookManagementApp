// Jai Swaminarayan KESHAV , Swami-Shreeji
package com.book.manage;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String title = request.getParameter("bookTitle");
		boolean deleted = BookDao.delete(title);
		if(deleted) {
			out.println("Successfully deleted !");
			response.sendRedirect("list.jsp");
		}else {
			out.println("Something went wrong");
		}
	}

}
