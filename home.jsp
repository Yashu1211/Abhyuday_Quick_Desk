<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <% String username=(session !=null) ? (String) session.getAttribute("username") : null; %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Welcome to QuickDesk</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
            <link href="static/css/Style.css" rel="stylesheet">
        </head>

        <body class="bg-light">


            <div class="container mt-5">
                <div class="card shadow-lg">
                    <div class="card-body text-center">
                        <h1 class="card-title">Welcome to QuickDesk Help Desk System</h1>
                        <p class="card-text mt-3">Hello <strong>
                                <%= username !=null ? username : "Guest" %>
                            </strong></p>
                        <p>Raise and manage support tickets with ease.</p>

                        <a href="register.jsp" class="btn btn-primary me-2">Register</a>
                        <a href="login.jsp" class="btn btn-success">Login</a>
                    </div>
                </div>
            </div>
            <div class="container">
                <!-- -----------Header(START)----------------- -->
                <c:import url="header.jsp" />
                <!-- -----------Header(END)----------------- -->
            </div>
        </body>


        <div class="container">
            <!-- ------Footer(START)---------- -->
            <c:import url="footer.jsp" />
            <!-- ------Footer(End)---------- -->
        </div>

        </html>