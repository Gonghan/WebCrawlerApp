package edu.cmu.sv.webcrawler.util;

public class Test {

	public static void main(String args[]){
		String url="http://localhost:8080/crawler/APIs.jsp";
		System.out.println(url.substring(0,url.length()-9));
	}
}
