<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <% String username=(String) session.getAttribute("username"); %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <title>User Dashboard - QuickDesk</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <link href="static/css/Style.css" rel="stylesheet">
                <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
            </head>

            <body>
                <div class="container">
                    <!-- -----------Header(START)----------------- -->
                    <c:import url="header.jsp" />
                    <!-- -----------Header(END)----------------- -->
                </div>

                <!-- HEADER -->
                <div class="row">
                    <div class="col">
                        <nav class="navbar navbar-expand-lg bg-body-tertiary">
                            <div class="container-fluid">
                                <a class="navbar-brand" href="index.do">QuickDesk</a>
                                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                    aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="navbar-toggler-icon"></span>
                                </button>
                                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                        <li class="nav-item">
                                            <a class="nav-link active" aria-current="page" href="home.jsp">Home</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="aboutus.jsp">About Us</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="contact.jsp">Contact Us</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Tickets</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Profile</a>
                                        </li>
                                    </ul>

                                    <form action="search.do" class="d-flex" role="search">
                                        <input class="form-control me-2" type="search" placeholder="Search"
                                            aria-label="Search">
                                        <button class="btn btn-outline-success" type="submit">Search</button>
                                    </form>

                                    <div class="dropdown ms-3">
                                        <button class="btn btn-secondary dropdown-toggle" type="button"
                                            data-bs-toggle="dropdown" aria-expanded="false">
                                            <span class="material-icons">person</span>
                                            <span class="ms-2">User</span>
                                        </button>
                                        <ul class="dropdown-menu">
                                            <c:choose>
                                                <c:when test="${not empty user}">
                                                    <li>
                                                        <span class="btn dropdown-item text-center">
                                                            <img src="static/media/images/user.png" height="100"><br>
                                                            <a href="profile.do">${user.email}</a>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <hr>
                                                        <a href="logout.do"
                                                            class="btn btn-danger dropdown-item d-flex align-items-center">
                                                            <span class="material-icons ms-auto">logout</span>
                                                            <span class="ms-2 me-auto">Logout</span>
                                                        </a>
                                                    </li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li>
                                                        <span class="dropdown-item" data-bs-toggle="modal"
                                                            data-bs-target="#signin_form">
                                                            <span class="material-icons">login</span>
                                                            <span class="ms-2">SignIn</span>
                                                        </span>
                                                    </li>
                                                    <li>
                                                        <span class="dropdown-item d-flex align-item-center"
                                                            data-bs-toggle="modal" data-bs-target="#signupbox">
                                                            <span class="material-icons">person_add</span>
                                                            <span class="ms-2">SignUp</span>
                                                        </span>
                                                    </li>
                                                </c:otherwise>
                                            </c:choose>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </div>
                </div>

                <!-- START OF USER DASHBOARD -->
                <div class="container mt-4">
                    <h2 class="mb-4">Welcome, <%= username !=null ? username : "User" %>
                    </h2>

                    <!-- Dashboard Cards -->
                    <div class="row g-4">
                        <div class="col-md-4">
                            <div class="card shadow">
                                <div class="card-body">
                                    <h5 class="card-title">Open Tickets</h5>
                                    <p class="card-text">View all your currently open support tickets.</p>
                                    <a href="MyTicketsServlet" class="btn btn-primary">View Tickets</a>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card shadow">
                                <div class="card-body">
                                    <h5 class="card-title">Create Ticket</h5>
                                    <p class="card-text">Need help? Submit a new support request here.</p>
                                    <a href="create_ticket.jsp" class="btn btn-success">Create Ticket</a>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card shadow">
                                <div class="card-body">
                                    <h5 class="card-title">Profile</h5>
                                    <p class="card-text">Update your personal info and preferences.</p>
                                    <a href="profile.jsp" class="btn btn-secondary">Go to Profile</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

                <div class="container">
                    <!-- ------Footer(START)---------- -->
                    <c:import url="footer.jsp" />
                    <!-- ------Footer(End)---------- -->
                </div>
            </body>

            </html>