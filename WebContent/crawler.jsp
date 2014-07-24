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
	<div class="container">
		<form id="crawlerform" class="form-horizontal" role="form"
			action="/crawl" method="post">
			<div class="form-group">
				<label for="companyname">Crawl records of a company</label> <input
					type="text" id="companyname" class="form-control col-sm-4"
					placeholder="Empty = crawl all companies"></input>
			</div>
			<div class="form-group">
				<button id="begincrawl" type="submit" class="btn btn-default">Crawl
					now!</button>
			</div>
			<div>
				<p class="bg-info" id="crawl-info"></p>
			</div>
			<label>Delete the records of a company</label>
			<div class="form-group">
				<input id="deletesymbol" type="text" class="form-control col-sm-4"
					placeholder="Empty = delete all records" />
				<button id="deleterecord" class="btn btn-default">Delete</button>
			</div>
			<div>
				<p class="bg-info" id="crawl-delete"></p>
			</div>
		</form>
	</div>


	<script>
		$("#begincrawl")
				.click(
						function(event) {
							event.preventDefault();
							var symbol = $("#companyname").val();
							var output = $("#crawl-info");
							var crawlurl = "/api/crawl/" + symbol;
							$
									.ajax({
										url : crawlurl,
										success : function(data) {
											output
													.text("Crawled the risk factors of the company "
															+ symbol);
										}
									});
						});

		$("#deleterecord").click(
				function(event) {
					event.preventDefault();
					var symbol = $("#deletesymbol").val();
					var output = $("#crawl-delete");
					var deleteurl = "/api/results/" + symbol;
					$.ajax({
						type : "DELETE",
						url : deleteurl,
						success : function(data) {
							output.text("Deleted the records of the company "
									+ symbol);
						}
					});
				});
	</script>


	<%@  include file="./templates/footer.jsp"%>
</body>
</html>