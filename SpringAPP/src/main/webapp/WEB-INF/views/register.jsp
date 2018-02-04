<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
    </head>
    <body>
        <h3>User FORM</h3>
        
        <p>Crea tu cuenta</p>
        <form:form method="POST" action="./register" modelAttribute="logindto">
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
       
    </body>
</html>