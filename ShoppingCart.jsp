<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
		<title>Duhh-Store Shopping Cart</title>
	</head>
	<body>
		<div class="container">
			<div class="page-header">
				<h2>Your Shopping Cart</h2>
			</div>
			<c:if test="${empty items}">
				<div class="well">
					<h3 class="text-center">There are no items in the shopping cart.</h3>
				</div>
			</c:if>
			<c:if test="${not empty items}">
				<table class="table table-hover table-striped table-bordered">
					<thead>
						<tr>
							<th>Name</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Total</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${items}" var="item">
							<tr>
								<td>${item.name}</td>
								<td>${item.quantity}</td>
								<td>${item.price}</td>
								<td>${item.total}</td>
								<td>
									<c:url value="RemoveItem" var="remove">
							   			<c:param name="id" value="${item.id}"/>
									</c:url>
									<a class="btn btn-danger btn-xs" href="${remove}">Remove</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<div class="row" align="center">
				<c:if test="${not empty items}">
					<div class="col-xs-4 col-sm-3">
						<a class="btn btn-success btn-block" href="Checkout">Proceed to Checkout</a>
					</div>
				</c:if>
				<div class="col-xs-4 col-sm-3">
					<a class="btn btn-danger btn-block" href="Store">Go Back to Store</a>
				</div>
			</div>
		</div>
	</body>
</html>