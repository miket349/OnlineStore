<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<title>Duhh-Store Checkout</title>
	</head>
	<body>
		<div class="container">
			<div class="page-header">
				<h2>Your Item Checkout</h2>
			</div>
			<table class="table table-hover table-striped table-bordered">
				<thead>
					<tr>
						<th>Name</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Total</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${items}" var="item">
						<tr>
							<td>${item.name}</td>
							<td>${item.quantity}</td>
							<td>${item.price}</td>
							<td>${item.total}</td>
						</tr>
						<b>Complete the checkout by entering your name and your e-mail address.</b>
						<form action="Checkout" method="post">
							<input type="text" class="form-control" name="firstName" placeholder="First Name">
							<input type="text" class="form-control" name="lastName" placeholder="Last Name">
							<input type="text" class="form-control" name="email" placeholder="E-mail Address">
							<input type="hidden" name="quantity" value="${item.quantity}">
							<input type="hidden" name="id" value="${item.id}">
							<input type="hidden" name="name" value="${item.name}">
							<input type="hidden" name="total" value="${item.total}">
							<button type="submit" class="btn btn-success btn-block">Complete Purchase</button>
						</form>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>