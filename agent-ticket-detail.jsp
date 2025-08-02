<%@ page contentType="text/html;charset=UTF‑8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<div class="container mt‑4">
  <c:set var="t" value="${ticket}"/>
  <h3>Ticket #${t.ticketId}: ${t.title}</h3>
  <p><strong>Status:</strong> ${t.statusName}</p>
  <p><strong>Category:</strong> ${t.categoryName}</p>
  <p><strong>Description:</strong><br/> ${t.description}</p>

  <!-- Comments timeline (assumes CommentDao added) -->
  <h4>Conversation</h4>
  <div class="border p‑3 mb‑3" style="max-height:300px;overflow:auto;">
    <c:forEach var="cmt" items="${comments}">
      <div><b>${cmt.userName}</b> at ${cmt.createdAt.substring(0,19)}<br/>
        ${cmt.message}</div><hr/>
    </c:forEach>
  </div>

  <!-- Reply form -->
  <form action="replyticket.do" method="post">
    <input type="hidden" name="ticketId" value="${t.ticketId}"/>
    <div class="mb‑3">
      <label for="message">Reply / Update</label>
      <textarea class="form-control" name="message" id="message" rows="3"></textarea>
    </div>
    <div class="mb‑3">
      <label for="status">Update status</label>
      <select class="form-select" name="status" id="status">
        <option value="IN_PROGRESS">In Progress</option>
        <option value="RESOLVED">Resolved</option>
        <option value="CLOSED">Closed</option>
      </select>
    </div>
    <button class="btn btn-success">Submit</button>
    <a href="agentdashboard.do" class="btn btn-secondary">Back</a>
  </form>
</div>
<jsp:include page="footer.jsp"/>
