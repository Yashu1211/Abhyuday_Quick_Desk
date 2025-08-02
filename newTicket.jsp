<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="models.User" %>

        <% User user=(User) session.getAttribute("user"); if (user==null) { response.sendRedirect("login.jsp"); return;
            } %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Create New Ticket - QuickDesk</title>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            </head>

            <body>

                <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">
                    <a class="navbar-brand" href="userdashboard.jsp">QuickDesk</a>
                    <div class="ml-auto text-white">
                        Welcome, <%= user.getName() %> | <a href="logout.jsp" class="text-light ml-3">Logout</a>
                    </div>
                </nav>

                <div class="container mt-5">
                    <h3>Create New Ticket</h3>
                    <form action="create-ticket.do" method="post">
                        <div class="form-group">
                            <label for="subject">Subject</label>
                            <input type="text" name="subject" id="subject" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="categoryId">Category</label>
                            <select name="categoryId" id="categoryId" class="form-control" required>
                                <c:forEach var="cat" items="${categories}">
                                    <option value="${cat.categoryId}">${cat.name}</option>
                                </c:forEach>

                                <!-- Populate dynamically from DB in future -->
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="description">Description</label>
                            <textarea name="description" id="description" rows="5" class="form-control"
                                required></textarea>
                        </div>

                        <button type="submit" class="btn btn-primary">Submit Ticket</button>
                        <a href="userdashboard.jsp" class="btn btn-secondary">Cancel</a>
                    </form>
                </div>

            </body>

            </html>