<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スケジュール管理表</title>
</head>
<body>
	<p><%= request.getAttribute("year") %>年
	   <%= request.getAttribute("month")%>月</p>
	<table>
		<tr>
			<td class="week">
				日
			</td>
			<td class="week">
				月
			</td>
			<td class="week">
				火
			</td>
			<td class="week">
				水
			</td>
			<td class="week">
				木
			</td>
			<td class="week">
				金
			</td>
			<td class="week">
				土
			</td>
		</tr>
		<% int weekCount = Integer.parseInt(request.getAttribute("count").toString()) / 7; %>
		<c:forEach items="${calendarDay}" var="calendarDay">
			<input type="text" value="${calnedarDay}"><br>
		</c:forEach>
		
	</table>
</body>
</html>