<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Default View</h1>
<p>
  Welcome to the default view.
</p>

<h2>Jersey Examples</h2>
<jsp:include page="/WEB-INF/jsp/pageinfo.jsp"/>
<h2>Bean Validation</h2>
<p>
  Field validation failure: ${fail}
</p>
