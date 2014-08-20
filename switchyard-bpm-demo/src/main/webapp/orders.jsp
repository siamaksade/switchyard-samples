<%@ page import="com.jboss.sample.bpm.model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Orders</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="css/default.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">
      
      <div class="page-header">
        <h1>Orders</h1>
      </div>

      <div class="row marketing">
	<% 
	   Collection<OrderAggregate> orders = (Collection<OrderAggregate>)request.getAttribute("orders");
	   if (orders == null || orders.isEmpty()) { %>
		<div style="padding-left:20px">No orders found</div>
	<% } else { %>
	      <table class="table table-bordered table-striped">
		<thead>
		<tr>
			<th>Order ID</th>
			<th>Item ID</th>
			<th>Quantity</th>
			<th>Status</th>
		</tr>
		</thead>
		<tbody>

	          <% for (Iterator<OrderAggregate> iter = orders.iterator(); iter.hasNext(); ) { 
			OrderAggregate aggregate = iter.next();
		  %>
			<tr>
				<td><%= aggregate.getOrder().getOrderId() %></td>
				<td><%= aggregate.getOrder().getItemId() %> </td>
				<td><%= aggregate.getOrder().getQuantity() %> </td>
				<td class="status-<%= (aggregate.getOrderStatus() == null ? "unknown" : aggregate.getOrderStatus().getStatus().toLowerCase()) %>"><%= (aggregate.getOrderStatus() == null ? "UNKNOWN" : aggregate.getOrderStatus().getStatus()) %> </td>
			</tr>
		  <% } %>
		  </tbody>
	      </table>
	<% } %>
      </div>

      <div class="footer" style="margin-top:40px">
        <p>&copy; Red Hat 2014</p>
      </div>

    </div> <!-- /container -->
  </body>
</html>
