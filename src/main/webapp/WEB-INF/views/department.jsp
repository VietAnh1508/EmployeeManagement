<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Department</title>
</head>
<body>
    <jsp:include page="_menu.jsp"/>
    <div align="center">
        <h2>Departments</h2>

        <form:form modelAttribute="department" action="/department/addOrEdit" method="post">
            <form:hidden path="id"/>
            <label>Add department</label>
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
                <c:when test="${empty departments}">
                    <tr>
                        <td colspan="4" align="center">Data is empty</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${departments}" var="department" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${department.name}</td>
                            <td><a href="<c:url value="/department/edit/${department.id}"/>">Edit</a></td>
                            <td><a href="<c:url value="/department/delete/${department.id}"/>">Delete</a></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </table>
    </div>
</body>
</html>
