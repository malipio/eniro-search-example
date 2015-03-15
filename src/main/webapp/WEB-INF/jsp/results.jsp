<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>
	Spring URL: ${springUrl} at ${time}
	<br/>
	JSTL URL: ${url}
	<br/>
	<table>
		<tr>
			<td><strong>eniroId</strong></td>
			<td><strong>companyName</strong></td>
			<td><strong>homepage</strong></td>
		</tr>
		<c:forEach var="advert" items="${adverts}">
		<tr>
			<td>${advert.eniroId}</td>
			<td>${advert.companyInfo.companyName}</td>
			<td><a href="${advert.homepage}">link</a></td>
		</tr>
		</c:forEach>
		
	</table>
</body>

</html>
