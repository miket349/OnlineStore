<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
		<title>Add Inventory</title>
	</head>
	<body>
		<div class="container">
			<div class="page-header">
				<h2>Add Item to Inventory</h2>
			</div>
			<form action="AddInventory" method="post">
				<div class="row">
					<div class="col-xs-9 col-sm-8">
						<div class="form-group">
							<input type="text" class="form-control" name="name" placeholder="Name">
							<input type="text" class="form-control" name="description" placeholder="Description">
							<input type="number" class="form-control" name="quantity" placeholder="Quantity">
							<input type="text" class="form-control" name="price" placeholder="Price"><br/><br/>
						</div>
					</div>
				</div>
				<c:if test="${not empty error}">
					<p class="well-sm bg-danger">${error}</p>
				</c:if>
				<div class="row">
					<div class="col-xs-5 col-sm-4">
						<button type="submit" class="btn btn-success btn-block">Add Item</button>
					</div>
					<div class="col-xs-5 col-sm-4">
						<a class="btn btn-danger btn-block" href="Inventoryy">Go Back</a>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>