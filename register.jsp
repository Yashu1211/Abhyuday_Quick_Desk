<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Register - QuickDesk</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
            <link href="static/css/Style.css" rel="stylesheet">
            <style>
                body {
                    background-color: #f2f4f6;
                    font-family: 'Segoe UI', 'Poppins', sans-serif;
                }

                .register-container {
                    max-width: 460px;
                    margin: 60px auto;
                }

                .card {
                    background-color: #fff;
                    border-radius: 12px;
                    padding: 2rem;
                    box-shadow: 0 6px 18px rgba(127, 90, 240, 0.15);
                }

                .card-title {
                    font-size: 1.8rem;
                    font-weight: bold;
                    color: #7f5af0;
                }

                .form-label {
                    font-weight: 500;
                }

                .btn-primary {
                    background-color: #7f5af0;
                    border: none;
                }

                .btn-primary:hover {
                    background-color: #6549d5;
                }

                a {
                    color: #2cb67d;
                    font-weight: 500;
                }

                a:hover {
                    text-decoration: underline;
                }
            </style>
        </head>

        <body>
            <div class="container register-container">
                <div class="card">
                    <h2 class="card-title text-center mb-4">Create an Account</h2>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <form action="register.do" method="post">
                        <div class="mb-3">
                            <label class="form-label">Name</label>
                            <input type="text" name="name" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label for="role_id" class="form-label">Role</label>
                            <select class="form-select" id="role_id" name="role_id" required>
                                <option value="">-- Select Role --</option>
                                <c:forEach var="role" items="${roles}">
                                    <option value="${role.roleId}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-primary w-100">Register</button>

                        <div class="text-center mt-3">
                            <a href="login.jsp">Already have an account? Log in</a>
                        </div>
                    </form>
                </div>
            </div>
        </body>

        </html>