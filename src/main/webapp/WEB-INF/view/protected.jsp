<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Protected Page</title>
</head>
<body>

<header>
    <h1>Title : ${title}</h1>
</header>

<section>
    <h2>Message : ${message}</h2>
    <p>Description: ${description}</p>
</section>

<c:if test="${pageContext.request.userPrincipal.name != null}">
<h3>Welcome : ${pageContext.request.userPrincipal.name}</h3>
        <c:url var="logoutUrl" value="/logout"/>
    <form action="${logoutUrl}" id="logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" name="submit" value="Log Out">
    </form>
</c:if>

<footer>
    <div>Get <a href="/">to home</a> resource to all users.</div>
    <div>Get <a href="opened">opened</a> resource to anonymous users.</div>
    <div>Get <a href="closed">closed</a> resource to authorized users only.</div>
    <div>Get <a href="protected">protected</a> resource for admin.</div>
</footer>

</body>
</html>
