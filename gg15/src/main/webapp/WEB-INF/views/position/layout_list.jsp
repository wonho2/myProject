<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/position.css">
</head>

<body>
<div>
	<div id="main_header">
		<tiles:insertAttribute name="header"/>
	</div>
	<div id="position_main_menu_type">
		<tiles:insertAttribute name="menu_type"/>
	</div>
	<div id="position_main_menu_sort">
		<tiles:insertAttribute name="menu_sort"/>
	</div>
	<div id="position_main_boardList">
		<tiles:insertAttribute name="boardList"/>
	</div>
	<div id="position_main_search">
		<tiles:insertAttribute name="search"/>
	</div>
		<div id="main_footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>
</body>
</html>
