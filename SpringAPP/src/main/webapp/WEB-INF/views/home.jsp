<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="local.fran.Spring.models.LoginDto" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	HOME
</h1>

<c:choose>

	
	
	<c:when test="${error!=null}">
        
    	<c:set var = "msgerrorlogin" scope = "session" value = "${error}"/>
    	<c:redirect url="./login"/>
    	
    </c:when>
	
    <c:when test="${user!=null}">
        <P>  Bienvenido  ${user.getUser()}. </P>
        <br/>
        <a href="./logout">Destruir la sesion</a>
    </c:when>
        
    <c:otherwise>
        <c:redirect url="./login"/>
    </c:otherwise>
</c:choose>

</body>
</html>
