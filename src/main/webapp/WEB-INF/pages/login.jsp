<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<form:form method="post" commandName="userCmd">
<p style="color:red"><form:errors path="*"/></p> <br>
   usernmae: <form:input path="uname"/> <br>
   Password: <form:input path="pwd"/> <br>
   Domain : <form:checkboxes items="${domains}" path="dmn"/><br>
   DOB : <form:input path="dob"/> (dd-MM-yyyy) <br>
   <input type="submit" value="Login">
</form:form>


 <c:if test="${msg ne null}">
    <c:out value="${msg}"/>
 </c:if>
<br>
<c:out value="${userCmd}"/>
