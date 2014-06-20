package edu.cmu.sv.webcrawler.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cmu.sv.webcrawler.util.MongoHelper;

@WebServlet("/dummy")
public class DummyServlet extends HttpServlet {

	private static final long serialVersionUID = 6271606642838193911L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		Map<String, String> env = System.getenv();
		for(String key:env.keySet()){
			out.println(key+"=>"+env.get(key));
		}
		
		
		
		
		MongoHelper helper = new MongoHelper();
		String filename = "stocksymbol";
		// This solution is from
		// http://kodejava.org/how-do-i-read-text-file-in-servlet/
		ServletContext context = getServletContext();
		InputStream is = context.getResourceAsStream(filename);
		if (is != null) {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			String text = "";
			while ((text = reader.readLine()) != null) {
				out.println(text);
				helper.insertCompanySymbol(text);
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("./error.jsp").forward(req, resp);
	}

}
