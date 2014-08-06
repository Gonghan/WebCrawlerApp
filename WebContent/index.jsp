<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risk Advisor</title>
<%@  include file="./templates/includes.jsp"%></head>
<body>
	<%@  include file="./templates/header.jsp"%>
	<!-- Main part -->
	<div class="jumbotron">
		<h1>Company Risk Advisor</h1>
		<p>Risk Advisor use Public Big Data to retrieve and analyze the
			"Risk Factors" for US publicly traded companies. Publicly available
			data sources (such as 10-K) are retrieved to create profiles for the
			companies and segment them based on their "risk factors" and "needs."</p>
		<p>
			<a href="./tutorial.jsp" class="btn btn-primary btn-lg" role="button">Tutorial
				&raquo;</a>
		</p>
	</div>
	<!-- <a href="./dummy">dummy link</a> -->
	<%@  include file="./templates/footer.jsp"%>
</body>
</html>