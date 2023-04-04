<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.3.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<h2>사원폼으로부터 입력한 값</h2>
	<h2>사원명: ${dto.name }</h2><br>
	<h2>부서명: ${dto.department }</h2><br>
	<h2>급여: ${dto.salary }</h2><br>
	<h2>나이: ${dto.age }</h2><br>
	<h2>혈액형: ${dto.blood }</h2><br>
</body>
</html>