<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<title>Duhh-Store Transaction History</title>
	</head>
	<body>
		<div class="container">
			<div class="page-header">
				<h2>Transaction History</h2>
			</div>
			<c:if test="${not empty message}">
				<p class="well-sm bg-success">${message}</p>
			</c:if>
			<c:if test="${empty items}">
				<div class="jumbotron">
					<h1><small>There are no transactions to display.</small></h1>
				</div>
			</c:if>
			<c:if test="${not empty items}">
				<table class="table table-hover table-striped table-bordered">
					<thead>
						<tr>
							<th>Name</th>
							<th>Quantity</th>
							<th>Total</th>
							<th>Order Number</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${items}" var="item">
							<tr>
								<td>${item.name}</td>
								<td>${item.quantity}</td>
								<td>${item.total}</td>
								<td>${item.orderNum}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<a class="btn btn-warning btn-success" href="Store">Go Back</a>
		</div>
	</body>
</html>