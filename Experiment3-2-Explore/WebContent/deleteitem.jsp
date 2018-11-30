<%@page import="com.lab.Product"%>
<%@page import="com.lab.ShoppingItem"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>删除商品</title>
</head>
<body>
	<%
		List<ShoppingItem> cart = (List<ShoppingItem>) session.getAttribute("cart");
		int productId = Integer.parseInt(request.getParameter("id"));
		int i = 0;
		for (ShoppingItem shoppingItem : cart) {
			Product product = shoppingItem.getProduct();
			i++;
			if (product.getId() == productId) {
				break;
			}
		}
		cart.remove(--i);
		pageContext.forward("viewcart.jsp");
	%>
</body>
</html>