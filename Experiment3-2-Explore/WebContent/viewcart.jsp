<%@page import="java.util.List"%>
<%@page import="com.lab.ShoppingItem"%>
<%@page import="com.lab.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看购物车</title>
</head>
<body>
	<%
		response.setContentType("text/html;charset=UTF-8");
		out.println("<html><head><title>购物车</title></head>");
		out.println("<body><a href = 'showproductlist.jsp'>" + "商品列表</a>");
		List<ShoppingItem> cart = (List<ShoppingItem>) session.getAttribute("cart");
		if (cart != null) {
			out.println("<table>");
			out.println("<tr><td style = 'width:50px'>数量" + "</td>" + "<td style = 'width:80px'>商品</td>"
					+ "<td style = 'width:80px'>价格</td>" + "<td style = 'width:80px'>小计</td>"
					+ "<td style = 'width:80px'>是否删除</td></tr>");

			double total = 0.0;
			for (ShoppingItem shoppingItem : cart) {
				Product product = shoppingItem.getProduct();
				int quantity = shoppingItem.getQuantity();
				if (quantity != 0) {
					float price = product.getPrice();
					out.println("<tr>");
					out.println("<td>" + quantity + "</td>");
					out.println("<td>" + product.getName() + "</td>");
					out.println("<td>" + price + "</td>");
					// 计算小计并实现四舍五入
					double subtotal = ((int) (price * quantity * 100 + 0.5)) / 100.00;
					out.println("<td>" + subtotal + "</td>");
					out.println("<td><a href=deleteitem.jsp?id=" + product.getId() + ">" + "删除</a>" + "</td>");
					total += subtotal;
					out.println("</tr>");
				}
			}
			out.println("<tr><td colspan = '4'" + "style = 'text-align:right'>" + "总计：" + total + "</td></tr>");
			out.println("</table>");
		}
		out.println("</table></body></html>");
	%>
</body>
</html>