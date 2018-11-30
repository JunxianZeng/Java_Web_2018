<%@page import="com.lab.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(1, "单反相机", "尼康性价比最高的单发相机", 4159.95F));
		products.add(new Product(2, "三星手机", "三星公司的最新iPhone5产品", 1199.95F));
		products.add(new Product(3, "笔记本电脑", "联想公司的新一代产品", 5129.95F));
		products.add(new Product(4, "平板电脑", "苹果公司的新产品", 1239.95F));
	%>
</body>
</html>