<!-- WebContent/includes/header.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <% String username=(String) session.getAttribute("username"); %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>QuickDesk</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="static/css/Style.css" rel="stylesheet">
        </head>

        <body>

            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg navbar-custom">
                <div class="container-fluid">
                    <a class="navbar-brand" href="index.do">QuickDesk</a>
                    <div class="collapse navbar-collapse justify-content-end">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="#">Welcome, <%= username !=null ? username : "User" %></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="aboutus.jsp">About Us</a> 
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="logout.do">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>