<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Employee</title>
</head>
<body>
<jsp:include page="_menu.jsp"/>
<div align="center">
    <h2>Employees</h2>

    <form:form commandName="employee" action="/employee/addOrEdit" method="post">
        <form:hidden path="id"/>
        <table>
            <tr>
                <td>Name</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Roles</td>
                <td>
                    <c:forEach items="${roles}" var="role">
                        <form:checkbox path="roles" value="${role.id}" label="${role.name}"/>
                    </c:forEach>
                    <%--<form:checkboxes path="roles" items="${roles}" itemValue="id" itemLabel="name"/>--%>
                </td>
            </tr>
            <tr>
                <td>Work at</td>
                <td>
                    <form:select path="department.id">
                        <form:options items="${departments}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form:form>

    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <th>No</th>
            <th>Name</th>
            <th>Role(s)</th>
            <th>Works at</th>
            <th colspan="2">Action</th>
        </tr>
        <c:choose>
            <c:when test="${empty employees}">
                <tr>
                    <td colspan="6" align="center">Data is empty</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach items="${employees}" var="employee" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${employee.name}</td>
                        <td>
                            <c:forEach items="${employee.roles}" var="role">
                                ${role.role}
                            </c:forEach>
                        </td>
                        <td>${employee.department.name}</td>
                        <td><a href="<c:url value="/role/edit/${employee.id}"/>">Edit</a></td>
                        <td><a href="<c:url value="/role/delete/${employee.id}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </table>
</div>
</body>
</html>