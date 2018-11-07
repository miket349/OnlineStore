<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
		<title>Duhh-Store Item Detail</title>
	</head>
	<body>
		<div class="container">
			<div class="page-header">
				<h2>Item Details</h2>
			</div>
			<c:forEach items="${items}" var="item">
				<li>Name: ${item.name}</li>
				<li>Description: ${item.description}</li>
				<li>Quantity: ${item.quantity}</li>
				<li>Price: ${item.price}</li>
				<br/>
				<form action="ShoppingCart" method="post">
					<div class="row">
						<div class="col-xs-3 col-sm-2">
							<div class="form-group">
								<input type="hidden" name="id" value="${item.id}">
								<input type="hidden" name="name" value="${item.name}">
								<input type="hidden" name="description" value="${item.description}">
								<input type="number" class="form-control" name="quantity" placeholder="Quantity">
								<input type="hidden" name="price" value="${item.price}">
							</div>
						</div>
						<div class="col-xs-4 col-sm-5">
							<button type="submit" class="btn btn-success btn-block">Add to Shopping Cart</button>
						</div>
						<div class="col-xs-4 col-sm-5">
							<a class="btn btn-danger btn-block" href="Store">Go Back</a>
						</div>
					</div>
				</form>
			</c:forEach>
		</div>
	</body>
</html>