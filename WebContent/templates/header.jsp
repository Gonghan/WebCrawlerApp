<%
	String basepath = request.getContextPath();
%>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href=<%=basepath + "/index.jsp"%>>Risk Advisor</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href=<%=basepath + "/index.jsp"%>>Index</a></li>
				<li><a href=<%=basepath + "/crawler.jsp"%>>Crawler</a></li>
				<li><a href=<%=basepath + "/results.jsp"%>>Results</a></li>
				<li><a href=<%=basepath + "/comparison.jsp"%>>Comparison</a></li>
				<!-- <li><a href=<%=basepath + "/MongoDB.jsp"%>>MongoDB</a></li>-->
				<li><a href=<%=basepath + "/APIs.jsp"%>>APIs</a></li>
				<li><a href=<%=basepath + "/about.jsp"%>>About</a></li>
				<li><a href=<%=basepath + "/tutorial.jsp"%>>Tutorial</a></li>
				<!--  <li><a href=<%=basepath + "/Envs"%>>Envs</a></li>-->
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>
<div class="container">
	<div class="main">