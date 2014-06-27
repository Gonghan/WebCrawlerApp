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
		String host = "http://gonghan.mybluemix.net";
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
				</tr>

				<tr class="success">
					<td>GET</td>
					<td><a target="_blank" href="<%=basepath + "/api/symbols"%>"><%=host + "/api/symbols"%></a></td>
					<td>Get symbols of all companies and return JSON file.</td>
				</tr>

				<tr class="success">
					<td>GET</td>
					<td><a target="_blank" href="<%=basepath + "/api/crawl/"%>"><%=host + "/api/crawl/"%></a></td>
					<td>Begin to crawl the record from all companies' symbols.</td>
				</tr>

				<tr class="success">
					<td>GET</td>
					<td><a target="_blank"
						href="<%=basepath + "/api/crawl/symbol"%>"><%=host + "/api/crawl/symbol"%></a></td>
					<td>Begin to crawl the record from a given company symbol.</td>
				</tr>

				<tr class="success">
					<td>GET</td>
					<td><a target="_blank"
						href="<%=basepath + "/api/results/symbol"%>"><%=host + "/api/results/symbol"%></a></td>
					<td>Show the results of a given company.</td>
				</tr>

				<tr class="success">
					<td>GET</td>
					<td><a target="_blank"
						href="<%=basepath + "/api/results/symbol?year=yyyy"%>"><%=host + "/api/results/symbol?year=yyyy"%></a></td>
					<td>Show the results of a given company in a year.</td>
				</tr>

				<tr class="success">
					<td>DELETE</td>
					<td><a target="_blank"
						href="<%=basepath + "/api/results/symbol"%>"><%=host + "/api/results/symbol"%></a></td>
					<td>DELETE the results of a given company.</td>
				</tr>

				<tr class="success">
					<td>DELETE</td>
					<td><a target="_blank"
						href="<%=basepath + "/api/results/symbol?year=yyyy"%>"><%=host + "/api/results/symbol?year=yyyy"%></a></td>
					<td>DELETE the results of a given company in a given year.</td>
				</tr>

				<tr class="danger">
					<td>GET</td>
					<td><a target="_blank"
						href="<%=basepath + "/api/results/keywords"%>"><%=host + "/api/results/keywords"%></a></td>
					<td>Begin to crawl the record from a given company symbol.</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div id="instructions" class="bg-info">
		<label>Instructions</label>
		<ul>
			<li>Replace symbol with a REAL company symbol(e.g., NWSA, FUBC).</li>
			<li>Replace the yyyy with a REAL year(e.g., 2013, 2012)</li>
		</ul>
	</div>

	<%@  include file="./templates/footer.jsp"%>
</body>
</html>