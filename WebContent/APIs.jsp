<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Web Crawler</title>
<%@  include file="./templates/includes.jsp"%></head>
<body>
	<%@  include file="./templates/header.jsp"%>
	
	<!-- Main part -->
	<h1>APIs</h1>
	<div class="apis">
		<table class="table table-striped table-hover table-bordered table-striped">
			<tbody>
				<tr>
					<td>Method</td>
					<td>URL</td>
					<td>Description</td>
					<td>Example</td>
				</tr>
				
				<tr  class ="success">
					<td>GET</td>
					<td><a target ="_blank" href="<%=basepath+"/api/symbols" %>"><%=basepath+"/api/symbols" %></a></td>
					<td>Get symbols of all companies and return JSON file.</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
	<%@  include file="./templates/footer.jsp"%>
</body>
</html>