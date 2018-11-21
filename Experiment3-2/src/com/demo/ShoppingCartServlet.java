package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lab.Product;
import com.lab.ShoppingItem;

@WebServlet(name = "ShoppingCartServlet", urlPatterns = 
	{ "/products","/viewProductDetails", "/addToCart", "/viewCart","/deleteItem" })
public class ShoppingCartServlet extends HttpServlet {
	// products 是存放所有商品的List对象
	private List<Product> products = new ArrayList<Product>();

	@Override
	public void init() throws ServletException {
		products.add(new Product(1, "单反相机", "尼康性价比最高的单发相机", 4159.95F));
		products.add(new Product(2, "三星手机", "三星公司的最新iPhone5产品", 1199.95F));
		products.add(new Product(3, "笔记本电脑", "联想公司的新一代产品", 5129.95F));
		products.add(new Product(4, "平板电脑", "苹果公司的新产品", 1239.95F));
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		// 根据请求URI决定调用那个方法
		if (uri.endsWith("/products")) {
			showProductList(response);
		} else if (uri.endsWith("/viewProductDetails")) {
			showProductDetails(request, response);
		} else if (uri.endsWith("viewCart")) {
			showCart(request, response);
		} else if (uri.endsWith("deleteItem")) {
			deleteItem(request, response);
		}
	}

	// 该方法用于显示所有商品信息
	private void showProductList(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>商品列表</title><" + "</head><body><p>商品列表</p>");
		out.println("<ul>");
		for (Product product : products) {
			out.println("<li>" + product.getName() + "(" + product.getPrice() + ")("
					+ "<a href = 'viewProductDetails?id=" + product.getId() + " '>详细信息</a>)");
		}
		out.println("</ul>");
		out.println("<a href = 'viewCart'>查看购物车</a>");
		out.println("</body></html>");
	}

	private void showProductDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int productId = 0;
		try {
			productId = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {

		}
		Product product = getProduct(productId);
		if (product != null) {
			out.println("<html><head>" + "<title>商品详细信息</title></head>" + "<body><p>"
					+ "<form method='post'action='addToCart'>");
			out.println("<input type='hidden' name='id'" + "value='" + productId + "'/>");
			out.println("<table>");
			out.println("<tr><td>商品名</td><td>" + product.getName() + "</td></tr>");
			out.println("<tr><td>说明</td><td>" + product.getDescription() + "</td></tr>");
			out.println("<tr><td>价格</td><td>" + product.getPrice() + "</td></tr>");
			out.println("<tr>" + "<tr>" + "<td><input name='quantity'/></td>" + "<td><input type='submit'value='购买'/>"
					+ "</td>" + "</tr>");
			out.println("<tr><td colspan='2'>" + "<a href='products'>商品列表</a>" + "</td></tr>");
			out.println("</table>");
			out.println("</form></body>");
		} else {
			out.println("No product found");
		}
	}

	// 根据商品号返回商品对象方法
	private Product getProduct(int productId) {
		for (Product product : products) {
			if (product.getId() == productId) {
				return product;
			}
		}
		return null;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将购买的商品添加到购物车中
		int productId = 0;
		int quantity = 0;
		try {
			productId = Integer.parseInt(request.getParameter("id"));
			quantity = Integer.parseInt(request.getParameter("quantity"));
		} catch (NumberFormatException e) {
		}

		Product product = getProduct(productId);
		if (product != null && quantity >= 0) {
			// 创建一个商品条目
			ShoppingItem shoppingItem = new ShoppingItem(product, quantity);
			HttpSession session = request.getSession();
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
		showProductList(response);
	}

	private void showCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>购物车</title></head>");
		out.println("<body><a href = 'products'>" + "商品列表</a>");
		HttpSession session = request.getSession();
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
					out.println("<td><a href=deleteItem?id=" + product.getId() + ">" + "删除</a>" + "</td>");
					total += subtotal;
					out.println("</tr>");
				}
			}
			out.println("<tr><td colspan = '4'" + "style = 'text-align:right'>" + "总计：" + total + "</td></tr>");
			out.println("</table>");
		}
		out.println("</table></body></html>");
	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
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
		showCart(request, response);
	}
}
