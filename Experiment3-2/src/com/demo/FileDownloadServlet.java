package com.demo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FileDownLoadServlet
 */
@WebServlet(urlPatterns = {"/download"})
public class FileDownloadServlet extends HttpServlet{
	public  void doGet (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(session == null || session.getAttribute("loggedIn") == null) {
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/data");
		File file = new File(dataDirectory, "servlet-spec.pdf");
		if(file.exists()) {
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename= servlet-spec.pdf");
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while(i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			}catch(IOException ex) {
				System.out.println(ex.toString());
			}finally {
				if(bis != null) {
					bis.close();
				}
				if(fis != null) {
					fis.close();
			}
		}
	}else {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("文件不存在！");
	}
	}
}
