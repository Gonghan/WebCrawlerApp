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
	<div class="container">
		<form id="crawlerform" class="form-horizontal" role="form" action="<%=request.getContextPath()+"/crawl" %>" method="post">
			<div class="form-group">
				<label for="companyname">Crawl records of a company</label>
				<input type="text" id="companyname" class="form-control"  placeholder="empty = crawl all companies"></input>
			</div>
			<div class="form-group">
			      <button id="begincrawl" type="submit" class="btn btn-default">Crawl now!</button>
		  	</div>
			
		</form>
	</div>
	<div>
		<p class="bg-info" id="crawl-info"></p>
	</div>
	
	<script>
		$("#begincrawl").click(function(event){
			event.preventDefault();
			var symbol=$("#companyname").val();
			console.log(symbol);
			var output=$("#crawl-info");
			$.ajax(
				{
					url:"/crawler/api/symbols",
					success:function(result){
						output.text(result);
					}
				
				}
			
			);
		});
	</script>
	
	
	<%@  include file="./templates/footer.jsp"%>
</body>
</html>