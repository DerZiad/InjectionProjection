<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>
</body>
<c:url var="serveur" value="/home" />
<script>
	window.location = "${serveur}";
</script>
</html>
