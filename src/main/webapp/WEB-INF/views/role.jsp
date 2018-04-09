<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Role</title>
</head>
<body>
<jsp:include page="_menu.jsp"/>
<div align="center">
    <h2>Roles</h2>

    <form:form modelAttribute="role" action="/role/addOrEdit" method="post">
        <form:hidden path="id"/>
        <label>Add role</label>
        <form:input path="name"/>
        <input type="submit" value="Submit"/>
    </form:form>

    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>No</th>
            <th>Name</th>
            <th colspan="2">Action</th>
        </tr>
        <c:choose>
            <c:when test="${empty roles}">
                <tr>
                    <td colspan="4" align="center">Data is empty</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${roles}" var="role" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${role.name}</td>
                        <td><a href="<c:url value="/role/edit/${role.id}"/>">Edit</a></td>
                        <td><a href="<c:url value="/role/delete/${role.id}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
</div>
</body>
</html>
