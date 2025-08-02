<%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <title>About Us - QuickDesk</title>
            <link rel="stylesheet" href="assets/bootstrap.min.css">
            <style>
                body {
                    background-color: #f1f4f8;
                    font-family: 'Segoe UI', sans-serif;
                }

                .about-section {
                    max-width: 900px;
                    margin: 50px auto;
                    background: #ffffff;
                    padding: 40px;
                    border-radius: 20px;
                    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                }

                .about-title {
                    text-align: center;
                    margin-bottom: 30px;
                    font-size: 2.5rem;
                    color: #004d70;
                    font-weight: 600;
                }

                .about-description {
                    font-size: 1.1rem;
                    color: #444;
                    line-height: 1.8;
                }

                .about-highlight {
                    color: #00bfae;
                    font-weight: 600;
                }

                .about-values {
                    margin-top: 30px;
                }

                .value-item {
                    margin-bottom: 15px;
                }

                .value-item strong {
                    color: #004d70;
                }
            </style>
        </head>

        <body>

            <!-- Header -->
            <div class="container">
                <c:import url="header.jsp" />
            </div>

            <!-- About Section -->
            <div class="about-section container">
                <h1 class="about-title">About QuickDesk</h1>
                <p class="about-description">
                    <span class="about-highlight">QuickDesk</span> is a modern, user-friendly help desk solution
                    designed to simplify how users raise issues and how support teams resolve them. Our goal is to
                    provide an intuitive platform that streamlines communication and ensures faster resolutions without
                    overwhelming complexity.
                </p>

                <div class="about-values">
                    <div class="value-item">
                        <strong>✅ Simple Ticketing:</strong> Easily create, manage, and track support tickets.
                    </div>
                    <div class="value-item">
                        <strong>✅ Role-Based Access:</strong> Separate panels for End Users, Support Agents, and Admins.
                    </div>
                    <div class="value-item">
                        <strong>✅ Efficient Workflows:</strong> Filter tickets, manage responses, and resolve issues
                        quickly.
                    </div>
                    <div class="value-item">
                        <strong>✅ Clean & Responsive UI:</strong> Works seamlessly on desktop and mobile devices.
                    </div>
                    <div class="value-item">
                        <strong>✅ Transparency & Accountability:</strong> Built-in status tracking, user feedback, and
                        audit logs.
                    </div>
                </div>
            </div>

            <!-- Footer -->
            <div class="container">
                <c:import url="footer.jsp" />
            </div>

        </body>

        </html>