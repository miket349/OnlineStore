<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
		<title>Duhh-Store Inventory</title>
	</head>
	<body>
		<div class="container">
			<div class="page-header">
				<h2>Manage Duhh-Store Inventory</h2>
			</div>
			<form action="Inventoryy" method="post">
				<div class="row">
					<div class="col-xs-6 col-sm-5">
						<div class="form-group">
							<input type="text" class="form-control" id="name" name="name" placeholder="Enter the item name">
						</div>
					</div>
					<div class="col-xs-3 col-sm-2">
						<button type="submit" class="btn btn-default btn-block">Search</button>
					</div>
					<div class="col-xs-3 col-sm-2">
						<a class="btn btn-success btn-block" href="AddInventory">Add Item</a>
					</div>
					<div class="col-xs-4 col-sm-3">
						<a class="btn btn-warning btn-block" href="Store">Go to Store</a>
					</div>
				</div>
			</form>
			<c:if test="${empty items}">
				<div class="jumbotron">
					<h1>Uh-Oh! <small>There are no items to display.</small></h1>
				</div>
			</c:if>
			<div class="inventory">
				<c:if test="${not empty items}">
					<table class="table table-hover table-striped table-bordered">
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Quantity</th>
								<th>Price</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items}" var="item">
								<tr>
									<td>${item.name}</td>
									<td>${item.description}</td>
									<td>${item.quantity}</td>
									<td>${item.price}</td>
									<td>
										<c:url value="RemoveInventory" var="remove">
							   				<c:param name="id" value="${item.id}"/>
										</c:url>
										<a class="btn btn-danger btn-xs" href="${remove}">Remove</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</body>
</html>