package edu.cmu.sv.webcrawler.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
		String base=request.getSession().getServletContext().getRealPath("");
		String filename=base+"/stocksymbol";
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line="";
		MongoHelper helper=new MongoHelper();
		while((line=br.readLine())!=null){
		
			helper.insertCompanySymbol(line);
		}
		br.close();
		ArrayList<String>list=helper.getAllSymbols();
		PrintWriter out=resp.getWriter();
		for(String s:list){
			out.println(s);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("./error.jsp").forward(req, resp);
	}

}
