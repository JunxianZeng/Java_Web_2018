<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="init.jsp"%>
<meta charset="UTF-8">
<title>详细信息</title>
</head>
<body>
	<%
		response.setContentType("text/html;charset=UTF-8");
		int productId = 0;

		productId = Integer.parseInt(request.getParameter("id"));

		Product product = products.get(1);
		for (Product product1 : products) {
			if (product1.getId() == productId) {
				product = product1;
				break;
			} else
				product = null;
		}
		if (product != null) {
			out.println("<html><head>" + "<title>商品详细信息</title></head>" + "<body><p>"
					+ "<form method='post'action='addtocart.jsp'>");
			out.println("<input type='hidden' name='id'" + "value='" + productId + "'/>");
			out.println("<table>");
			out.println("<tr><td>商品名</td><td>" + product.getName() + "</td></tr>");
			out.println("<tr><td>说明</td><td>" + product.getDescription() + "</td></tr>");
			out.println("<tr><td>价格</td><td>" + product.getPrice() + "</td></tr>");
			out.println("<tr>" + "<tr>" + "<td><input name='quantity'/></td>"
					+ "<td><input type='submit'value='购买'/>" + "</td>" + "</tr>");
			out.println("<tr><td colspan='2'>" + "<a href='showproductlist.jsp'>商品列表</a>" + "</td></tr>");
			out.println("</table>");
			out.println("</form></body>");
		} else {
			out.println("No product found");
		}
	%>

</body>
</html>