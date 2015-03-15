<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="en">

<body>
	<h1>Eniro API Search Test</h1>
	<h2>Query parameters</h2>
	<form:form method="POST" action="/" commandName="searchObject">
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
	<br/>
	<h2>Previous searches</h2>
	<table>
		<thead>
			<tr>
				<td>Search Date</td>
				<td>Search Words</td>
				<td>Regexp filter</td>
			</tr>
		</thead>
		<c:forEach var="searchObject" items="${searchObjectHistory}">
			<tr>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss.S" value="${searchObject.searchDate}"/></td>
				<td>${searchObject.searchWords}</td>
				<td>${searchObject.regexpFilter}</td>
			</tr>
		</c:forEach>
	</table>
	<h2>Query results</h2>
	<table>
		<thead>
		<tr>
			<td>eniroId</td>
			<td>companyName</td>
			<td>homepage</td>
		</tr>
		</thead>
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
