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
  <title>Jersey Examples</title>
</head>
<body>
<p>
  <strong>Please note</strong>, it's generally bad practise to bind to '/' like
  our MainService does, as that prevents future changes to the software. We're
  just doing it for now, as we'd like you to get right to the MainService.
</p>
<p>
  <strong>Why?</strong> It may be that you later decide to use Jersey for some
  aspects of your software, and other frameworks or libraries for other aspects
  of your software. If '/' is bound to Jersey, nothing else can use it. Then,
  you will need to violate Tim's
  <a href="https://www.w3.org/Provider/Style/URI.html">utopian recommendations
  </a>
  ; please don't make him, or the people deep linking into your web pages,
  angry!
</p>
<c:choose>
  <c:when test="${model.page == '/WEB-INF/jsp/test.jsp'}">
    <jsp:include page="/WEB-INF/jsp/test.jsp"/>
  </c:when>
  <c:when test="${model.page == '/WEB-INF/jsp/testpath.jsp'}">
    <jsp:include page="/WEB-INF/jsp/testpath.jsp"/>
  </c:when>
  <c:when test="${model.page == '/WEB-INF/jsp/parameters.jsp'}">
    <jsp:include page="/WEB-INF/jsp/parameters.jsp"/>
  </c:when>
  <c:otherwise>
    <jsp:include page="/WEB-INF/jsp/default-view.jsp"/>
  </c:otherwise>
</c:choose>

<c:if test="${model.page == '/WEB-INF/jsp/test.jsp'}">
  <jsp:include page="pageinfo.jsp"/>
</c:if>

</body>
</html>
