<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="parametersUrl" value="/parameters"/>

<form action="${parametersUrl}">
  <div>
    <label for="parameter1">Parameter 1</label>
    <input name="parameter1" id="parameter1"/>
  </div>
  <div>
    <label for="parameter2">Parameter 2</label>
    <input name="parameter2" id="parameter2"/>
  </div>
  <div>
    <label for="parameter3">Parameter 3</label>
    <input name="parameter3" id="parameter3"/>
  </div>
  <input type="submit" name="Submit">
</form>