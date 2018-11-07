<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
		<title>Duhh-Store</title>
	</head>
	<body>
		<div class="container">
			<div class="page-header">
				<h1>Welcome to Duhh-Store</h1>
				<h2><small>Feel free to buy, search, and view items!</small></h2>
			</div>
			<form action="Store" method="post">
				<div class="row">
					<div class="col-xs-5 col-sm-4">
						<div class="form-group">
							<input type="text" class="form-control" id="name" name="name" placeholder="Enter the item name">
						</div>
					</div>
					<div class="col-xs-3 col-sm-2">
						<button type="submit" class="btn btn-default btn-block">Search</button>
					</div>
					<div class="col-xs-3 col-sm-2">
						<a class="btn btn-success btn-block" href="ShoppingCart">Shopping Cart (${counter})</a>
					</div>
					<div class="col-xs-3 col-sm-2">
						<a class="btn btn-danger btn-block" href="Inventoryy">Manage Inventory</a>
					</div>
					<div class="col-xs-3 col-sm-2">
						<a class="btn btn-warning btn-block" href="History">View History</a>
					</div>
				</div>
			</form>
			<c:if test="${empty items}">
				<div class="jumbotron">
					<h1>Uh-Oh! <small>There are no items to display.</small></h1>
				</div>
			</c:if>
			<c:if test="${not empty items}">
				<table class="table table-hover table-striped table-bordered">
					<thead>
						<tr>
							<th>Item</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${items}" var="item">	
							<tr>						
								<td>${item.name}</a></td>
								<td>${item.quantity}</td>
								<td>${item.price}</td>
								<td>
									<c:url value="Detail" var="detail">
										<c:param name="id" value="${item.id}"/>
									</c:url>
									<form action="ShoppingCart" method="post">
										<div class="row">
											<div class="col-xs-4 col-sm-3">
												<a class="btn btn-warning btn-block" href="${detail}">View Details</a>
											</div>
											<div class="col-xs-3 col-sm-2">
												<div class="form-group">
													<input type="hidden" name="id" value="${item.id}">
													<input type="hidden" name="name" value="${item.name}">
													<input type="number" class="form-control" name="quantity" placeholder="Quantity">
													<input type="hidden" name="price" value="${item.price}">
													<input type="hidden" name="cart" value="true">
												</div>
											</div>
											<div class="col-xs-4 col-sm-5">
												<button type="submit" class="btn btn-success btn-block">Add to Shopping Cart</button>
											</div>
										</div>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</body>
</html>