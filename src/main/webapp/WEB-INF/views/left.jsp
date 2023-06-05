<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row p-2"><a href="main.jsp" class="nav-link px-3"><span class="text-center">Main</span></a></div>
<c:choose>
	<c:when test="${empty sessionScope.id}">
		<div class="row p-2"><a href="login.do" class="nav-link px-3"><span class="text-center">Login</span></a></div>
		<div class="row p-2"><a href="memberRegister.do" class="nav-link px-3"><span class="text-center">Register</span></a></div>
	</c:when>
	<c:otherwise>
		<div class="row p-2"><a href="boardlist.board" class="nav-link px-3"><span class="text-center">Board</span></a></div>
	</c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${sessionScope.id=='admin'}">
	<div class="row p-2"><a href="memberlist.do" class="nav-link px-3"><span class="text-center">Members</span></a></div>
	</c:when>
</c:choose>
 
   