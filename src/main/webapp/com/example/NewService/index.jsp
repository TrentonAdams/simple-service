<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: trenta
  Date: 19/02/16
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>jsp example</title>
</head>
<body>
<jsp:useBean id="model" scope="request" type="com.example.NewService"/>
<p>Jsp example: ${model.name}</p>
<p>session: ${model.session}</p>
<p>service link: ${model.link}</p>

<ul>
<c:forEach var="link" items="${model.serviceLinks}">
  <li>${link.uri}</li>
</c:forEach>
</ul>

</body>
</html>
