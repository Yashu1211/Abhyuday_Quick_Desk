<%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html>

        <head>
            <title>Login - QuickDesk</title>
            <link rel="stylesheet" href="assets/bootstrap.min.css">
            <link rel="stylesheet" href="static/css/Style.css">
            <style>
                body {
                    background-color: #f2f4f6;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    min-height: 100vh;
                }

                .login-card {
                    background-color: #ffffff;
                    max-width: 400px;
                    width: 100%;
                    padding: 2rem;
                    border-radius: 12px;
                    box-shadow: 0 6px 18px rgba(127, 90, 240, 0.15);
                }

                .login-card h2 {
                    text-align: center;
                    margin-bottom: 1.5rem;
                    color: #7f5af0;
                    font-weight: 600;
                }

                .login-card .btn {
                    background-color: #7f5af0;
                }

                .login-card .btn:hover {
                    background-color: #6549d5;
                }

                .login-card a {
                    color: #2cb67d;
                    font-weight: 500;
                }

                .login-card a:hover {
                    text-decoration: underline;
                }
            </style>
        </head>

        <body>
            <div class="login-card">
                <h2>Login</h2>
                <form method="post" action="login.do">
                    <div class="mb-3">
                        <label for="email" class="form-label">Email:</label>
                        <input type="email" id="email" name="email" class="form-control" required />
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password:</label>
                        <input type="password" id="password" name="password" class="form-control" required />
                    </div>
                    <button type="submit" class="btn w-100 text-white">Login</button>
                    <p class="mt-3 text-center">
                        Don’t have an account? <a href="register.jsp">Register</a>
                    </p>
                </form>
            </div>
        </body>

        </html>