<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home Page</title>
</head>
<body>

<header>
    <h1>Title : ${title}</h1>
</header>

<section>
    <h2>Message : ${message}</h2>
    <p>Description: ${description}</p>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h3>Welcome : ${pageContext.request.userPrincipal.name}</h3>
    </c:if>
</section>

<footer>
    <div>Get <a href="/">to home</a> resource to all users.</div>
    <div>Get <a href="opened">opened</a> resource to anonymous users.</div>
    <div>Get <a href="closed">closed</a> resource to authorized users only.</div>
    <div>Get <a href="protected">protected</a> resource for admin.</div>
</footer>

</body>
</html>
