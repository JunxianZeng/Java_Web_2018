<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="init.jsp"%>
<meta charset="UTF-8">
<title>商品列表</title>
</head>
<body>
	<p>商品列表</p>
	<ul>
		<%
			for (Product product : products) {
				out.println("<li>" + product.getName() + "(" + product.getPrice() + ")("
						+ "<a href = 'showproductdetails.jsp?id=" + product.getId() + " '>详细信息</a>)");
			}
		%>

	</ul>
	<a href='viewcart.jsp'>查看购物车</a>
</body>
</html>