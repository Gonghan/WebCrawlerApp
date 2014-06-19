package edu.cmu.sv.webcrawler.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cmu.sv.webcrawler.services.Get10K;

@WebServlet("/crawl")
public class CrawlerServlet extends HttpServlet {

	private static final long serialVersionUID = -5293515395438429354L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("./error.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doGet(req, resp);
		PrintWriter out = resp.getWriter();
		out.println("done");
		Get10K get = new Get10K();
		String companyname = req.getParameter("companyname");
		if (companyname == null || companyname.isEmpty()) {
			get.Download10KbyCIKList();
		} else {
			get.Download10KbyCIK(companyname, true);
		}
		// Thread thread1 = new Thread(new Runnable() {
		// public void run() {
		// get.Download10KbyCIKList();
		// }
		//
		// });
		

	}

}
