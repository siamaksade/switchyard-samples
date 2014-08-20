package com.jboss.sample.bpm.web;

import java.io.IOException;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jboss.sample.bpm.model.OrderAggregate;
import com.jboss.sample.bpm.service.OrderDAO;

@WebServlet(urlPatterns = "/orders")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = -6895497510308790357L;
	
	@Inject
	private OrderDAO orderDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<OrderAggregate> orders = orderDAO.getOrders();
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/orders.jsp").include(request, response);
	}
}
