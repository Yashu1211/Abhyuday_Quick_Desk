<%@ page language="java"
         contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>Agent Dashboard • QuickDesk</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-651slERerJDgk0oXpahlMmnYg5V+zWzYx+I/Kz3PWY7fHlAwt5l6YUdLgQiUlLY7"
        crossorigin="anonymous"/>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css"/>
</head>
<body>

  <jsp:include page="header.jsp" />

  <main class="container-fluid mt-5">
    <div class="row mb-4">
      <div class="col-sm-6 col-md-3">
        <div class="card bg-warning text-dark text-center">
          <div class="card-body p-4">
            <h5 class="card-title">Unassigned Tickets</h5>
            <h2 class="display-6">${fn:length(openList)}</h2>
          </div>
        </div>
      </div>
      <div class="col-sm-6 col-md-3">
        <div class="card bg-info text-white text-center">
          <div class="card-body p-4">
            <h5 class="card-title">My Tickets</h5>
            <h2 class="display-6">${fn:length(myList)}</h2>
          </div>
        </div>
      </div>
      <!-- additional cards if needed -->
    </div>

    <form class="row g-3 mb-4" method="get" action="agentdashboard.do">
      <div class="col-md-4">
        <div class="input-group">
          <span class="input-group-text"><i class="bi bi-search"></i></span>
          <input type="search" class="form-control" name="q" placeholder="Search tickets…" value="${param.q}">
        </div>
      </div>
      <div class="col-md-auto">
        <select class="form-select" name="status">
          <option value="">All Statuses</option>
          <option value="OPEN"    ${param.status=='OPEN'?'selected':''}>Open</option>
          <option value="IN_PROGRESS" ${param.status=='IN_PROGRESS'?'selected':''}>In Progress</option>
          <option value="RESOLVED"    ${param.status=='RESOLVED'?'selected':''}>Resolved</option>
          <option value="CLOSED"      ${param.status=='CLOSED'?'selected':''}>Closed</option>
        </select>
      </div>
      <div class="col-md-auto">
        <button type="submit" class="btn btn-primary">Filter</button>
      </div>
    </form>

    <div class="row">
      <div class="col-lg-6 mb-4">
        <div class="card">
          <div class="card-header bg-light"><h4>Unassigned Tickets</h4></div>
          <div class="card-body p-0">
            <div class="table-responsive" style="max-height:400px; overflow-y:auto;">
              <table class="table table-hover m-0">
                <thead>
                  <tr><th>ID</th><th>Title</th><th>Category</th><th>Created</th><th>Action</th></tr>
                </thead>
                <tbody>
                  <c:forEach var="t" items="${openList}">
                    <tr>
                      <td><c:out value="${t.ticketId}"/></td>
                      <td><c:out value="${t.title}"/></td>
                      <td><c:out value="${t.categoryName}"/></td>
                      <td><fmt:formatDate value="${t.createdAt}" pattern="yyyy-MM-dd HH:mm"/></td>
                      <td>
                        <form id="formTake${t.ticketId}" action="taketicket.do" method="post" class="d-inline">
                          <input type="hidden" name="ticketId" value="${t.ticketId}"/>
                          <button type="button" class="btn btn-sm btn-success"
                                  onclick="document.getElementById('formTake${t.ticketId}').submit();">Take</button>
                        </form>
                      </td>
                    </tr>
                  </c:forEach>
                  <c:if test="${empty openList}">
                    <tr><td colspan="5" class="text-center">No open tickets.</td></tr>
                  </c:if>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>

      <div class="col-lg-6 mb-4">
        <div class="card">
          <div class="card-header bg-light"><h4>My Tickets</h4></div>
          <div class="card-body p-0">
            <div class="table-responsive" style="max-height:400px; overflow-y:auto;">
              <table class="table table-hover mb-0">
                <thead>
                  <tr><th>ID</th><th>Title</th><th>Status</th><th>Last Updated</th></tr>
                </thead>
                <tbody>
                  <c:forEach var="t" items="${myList}">
                    <tr style="cursor:pointer;"
                        onclick="window.location = 'ticketdetail.do?id=${t.ticketId}'">
                      <td><c:out value="${t.ticketId}"/></td>
                      <td><c:out value="${t.title}"/></td>
                      <td><c:out value="${t.statusName}"/></td>
                      <td><fmt:formatDate value="${t.updatedAt}" pattern="yyyy-MM-dd HH:mm"/></td>
                    </tr>
                  </c:forEach>
                  <c:if test="${empty myList}">
                    <tr><td colspan="4" class="text-center">No assigned tickets.</td></tr>
                  </c:if>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>

  <jsp:include page="footer.jsp"/>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-FFMTj6VJ+yBP+zR5LsSfklBgBpykKoQghn8x1CpAS+1sQSJ8pxDQ6kaAgwhb5Na8"
          crossorigin="anonymous"></script>
</body>
</html>
