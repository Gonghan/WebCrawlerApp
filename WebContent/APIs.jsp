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
	<%
		String host = "http://webcrawlerapp.mybluemix.net";
	%>

	<!-- Main part -->
	<h1>APIs</h1>
	<div class="apis">
		<table
			class="table table-striped table-hover table-bordered table-striped">
			<tbody>
				<tr>
					<td>Method</td>
					<td>URL</td>
					<td>Description</td>
					<td>Example</td>
				</tr>

				<tr class="success">
					<td>GET</td>
					<td><a target="_blank" href="<%=basepath + "/api/symbols"%>"><%=host + "/api/symbols"%></a></td>
					<td>Get symbols of all companies and return JSON file.</td>
					<td></td>
				</tr>

				<tr class="success">
					<td>GET</td>
					<td><a target="_blank"
						href="<%=basepath + "/api/crawl/symbol"%>"><%=host + "/api/crawl/DDD"%></a></td>
					<td>Begin to crawl the record from a given company symbol.</td>
					<td></td>
				</tr>
				
				<tr class="success">
					<td>GET</td>
					<td><a target="_blank"
						href="<%=basepath + "/api/results/symbol"%>"><%=host + "/api/results/DDD"%></a></td>
					<td>Show the results of a given company.</td>
					<td></td>
				</tr>
				
				<tr class="success">
					<td>GET</td>
					<td><a target="_blank"
						href="<%=basepath + "/api/results/symbol?year=yyyy"%>"><%=host + "/api/results/DDD?year=2013"%></a></td>
					<td>Show the results of a given company in a year.</td>
					<td></td>
				</tr>
				
				<tr class="success">
					<td>DELETE</td>
					<td><a class="disabled" target="_blank"
						href="<%=basepath + "/api/results/symbol"%>"><%=host + "/api/results/DDD"%></a></td>
					<td>DELETE the results of a given company.</td>
					<td></td>
				</tr>
				
				<tr class="success">
					<td>DELETE</td>
					<td><a class="disabled" target="_blank"
						href="<%=basepath + "/api/results/symbol?year=yyyy"%>"><%=host + "/api/results/DDD?year=2013"%></a></td>
					<td>DELETE the results of a given company in a given year.</td>
					<td></td>
				</tr>
				
				<tr class="danger">
					<td>GET</td>
					<td><a class="disabled" target="_blank"
						href="<%=basepath + "/api/results/keywords"%>"><%=host + "/api/results/keywords"%></a></td>
					<td>Begin to crawl the record from a given company symbol.</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
	<%@  include file="./templates/footer.jsp"%>
</body>
</html>