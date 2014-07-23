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
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<div class="col-sm-4">
					<label for="companyA" class="control-label">Company A</label> <input
						type="text" class="form-control" id="companyA"
						placeholder="Enter the symbol of the first company." />
				</div>
				<div class="col-sm-4">
					<label for="companyB" class="control-label">Company B</label> <input
						type="text" class="form-control" id="companyB"
						placeholder="Enter the symbol of the second company." />
				</div>
				<div class="col-sm-4"></div>
			</div>
			<button type="button" class="btn btn-primary">Compare</button>
		</form>
	</div>
	<%@  include file="./templates/footer.jsp"%>
	<script>
		$('button').on('click',function(event){
			event.preventDefault();
			var A=$('#companyA').val();
			var B=$('#companyB').val();
			console.log(A+B);
		});
	</script>
</body>
</html>