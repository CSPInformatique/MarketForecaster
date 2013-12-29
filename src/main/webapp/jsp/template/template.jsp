<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<spring:url var="cssUrl" value="/resources/css" />
<spring:url var="jsUrl" value="/resources/js" />

<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>Market Forecaster</title>
	
	    <!-- Bootstrap core CSS -->
	   	<link href="resources/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <link href="resources/lib/bootstrap-datepicker/css/datepicker.css" rel="stylesheet">
	    <link href="resources/lib/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet">
	    <link href="resources/lib/select2/css/select2.css" rel="stylesheet">
	    
	    <!-- Custom styles for this template -->
	    <link href="resources/css/marketForecaster.css" rel="stylesheet">
    	<script src="resources/lib/jquery/jquery.js"></script>
		<script src="resources/lib/underscore/underscore.js"></script>
	    <script src="resources/lib/backbone/backbone.js"></script>
	    <script src="resources/lib/bootstrap/js/bootstrap.js"></script>
	    
       	<script type="text/javascript">
       		var ctx = "${pageContext.servletContext.contextPath}"; 
       		_.templateSettings = {
       			interpolate: /\<\@\=(.+?)\@\>/gim, 
     			evaluate: /\<\@([\s\S]+?)\@\>/gim, 
     			escape: /\<\@\-(.+?)\@\>/gim 
     		};
       	</script>
	</head>

	<body>
		<tiles:insertAttribute name="menu" />
	
		<div class="container">
			<div class="mainContent">
				<tiles:insertAttribute name="content" />
			</div>
			<div class="footer">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	    
	    <script src="resources/lib/cspjs/js/cspjs.js"></script>
	    <script src="resources/lib/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	    <script src="resources/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	    <script src="resources/lib/moment/js/moment-with-langs.js"></script>
	    <script src="resources/lib/select2/js/select2.min.js"></script>
	    
	  	<script src="resources/js/marketForecaster.js"></script>
	    
	  	<!-- Forecast -->
	  	<script src="resources/js/forecast/forecast-model.js"></script>
	  	<script src="resources/js/forecast/forecast-view.js"></script>
	  	
	  	<!-- User -->
	  	<script src="resources/js/user/user-model.js"></script>
	</body>
</html>