<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.lab.ShoppingItem"%>
<%@page import="com.lab.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="init.jsp"%>
<meta charset="UTF-8">
<title>加入购物车</title>
</head>
<body>
	<%
		int productId = 0;
		int quantity = 0;
		try {
			productId = Integer.parseInt(request.getParameter("id"));
			quantity = Integer.parseInt(request.getParameter("quantity"));
		} catch (NumberFormatException e) {
		}

		Product product = products.get(1);
		for (Product product1 : products) {
			if (product1.getId() == productId) {
				product = product1;
				break;
			} else
				product = null;
		}
		if (product != null && quantity >= 0) {
			// 创建一个商品条目
			ShoppingItem shoppingItem = new ShoppingItem(product, quantity);
			// 在会话对象中查找购物车对象
			List<ShoppingItem> cart = (List<ShoppingItem>) session.getAttribute("cart");
			if (cart == null) {
				// 如果在会话对象上找不到购物车对象，则创建一个
				cart = new ArrayList<ShoppingItem>();
				// 将购物车对象存储到会话对象上
				session.setAttribute("cart", cart);
			}
			// 将商品添加到购物车对象中
			cart.add(shoppingItem);
		}
		pageContext.forward("showproductlist.jsp");
	%>
</body>
</html>