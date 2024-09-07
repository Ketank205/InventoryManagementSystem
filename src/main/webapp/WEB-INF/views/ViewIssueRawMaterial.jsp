<%@page import="com.r3sys.Models.IssueRaw"%>
<%@page import="com.r3sys.Models.RawMaterial"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Inventory Management Tool :Raw Material Services</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .sidebar {
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            width: 250px;
            background-color: #343a40;
            color: #fff;
        }
        .sidebar a {
            color: #fff;
            padding: 15px;
            display: block;
            text-decoration: none;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .content {
            margin-left: 250px;
            padding: 20px;
        }
        .navbar {
            margin-left: 250px;
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <h2 class="text-center mt-4">Welcome Manager</h2>
        <a href="toAddRawMaterial">Add Raw Material</a>
        <a href="ViewRawMaterial">View/Delete Raw Material</a>
        <a href="toUpdateRawMaterial">Update Raw Material</a>
        <a href="toIssueRawMaterial">Issue Raw Material</a>
        <a href="ViewIssueRawMaterial">View Issued Raw Material</a>
        <a href="UnavailableRawMaterial">Unavailable Raw Material</a>
        <a href="ChangePassword">Change Password</a>
    </div>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="Home">Inventory Management Tool</a>
        <div class="ml-auto">
            <a class="btn btn-outline-light" href="logout">Logout</a>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="content">
       <!-- Form for viewing raw material -->
       <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">rawId</th>
                    <th scope="col">rawName</th>
                    <th scope="col">IssuerName</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">IssueDate</th>
                    <th scope="col">IssueTime</th>
                    <th scope="col">Delete</th>
                </tr>
            </thead>
            <% 
                List<IssueRaw> issueRaw = (List<IssueRaw>)request.getAttribute("issueRaw");
                %>
            <tbody>
            	<%
            	for(IssueRaw c :issueRaw){
            		if(c.getQuantity()>0){%>
            		<tr>
            			<td><%=c.getId()%></td>
            			<td><%=c.getRawId()%></td>
            			<td><%=c.getRawName()%></td>
            			<td><%=c.getIssuerName()%></td>
            			<td><%=c.getQuantity()%></td>
            			<td><%=c.getIssueDate()%></td>
            			<td><%=c.getIssueTime()%></td>
            			<td><a href="deleteIssueRaw?id=<%=c.getId()%>">Delete</a></td>
            		</tr>
            		<%
            		}
            	}
            	%>
            </tbody>
        </table>
    </div>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>