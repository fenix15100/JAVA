<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
    </head>
    <body>
        <h3>User FORM</h3>
        
        
        <c:if test="${msgerrorlogin!=null}">
        
        	<p>${msgerrorlogin}</p>
        	<br/>
        
        </c:if>
        
        <p>Para ver la HOME hay que loguearse</p>
        <form:form method="POST" action="./login" modelAttribute="logindto">
             <table>
                <tr>
                    <td><form:label path="user">Username</form:label></td>
                    <td><form:input path="user"/></td>
                </tr>
                <tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><form:input path="password"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
        
        <br/>
        <a href="./register">Crear cuenta</a>
    </body>
</html>