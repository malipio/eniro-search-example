<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>
	<c:url value="/resources/text.txt" var="url"/>
	<spring:url value="/resources/text.txt" htmlEscape="true" var="springUrl" />
	Spring URL: ${springUrl} at ${time}
	<br/>
	JSTL URL: ${url}
	<br/>
	Message: ${message}
	<br/>
	<form:form method="POST" action="/search" commandName="searchObject">
		<table>
			<tr>
				<td><form:label path="searchWords">search words:</form:label></td>
				<td><form:input path="searchWords"/></td>
			</tr>
			<tr>
				<td><form:label path="regexpFilter">regexp filter:</form:label></td>
				<td><form:input path="regexpFilter"/></td>
			</tr>
			<tr>
	        <td colspan="2">
	            <input type="submit" value="Submit"/>
	        </td>
    		</tr>
		</table>
	</form:form>
</body>

</html>
