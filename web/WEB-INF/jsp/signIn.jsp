<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    <%@include file="../css/SeaTheme.css"%>
    <%@include file="../css/FormStyle.css"%>
</style>
<html class="seaBackground standardBrightFontColor standardTextFont">
<head>
    <title><spring:message code="signIn.title" text="Sign in"/></title>
    <meta charset="UTF-8">
</head>
<body>
<div class="header centeredText">
    <spring:message code="signIn.header" text="Sign in"/>
</div>
<div class="centerElementWrapper">
    <div class="formHolder standardDarkFontColor bigTextFont">
        <form:form method="post" modelAttribute="signInForm" cssClass="form">
            <div class="formRow">
                <div class="formCell">
                    <spring:message code="form.login" text="Login"/>
                </div>
                <div class="formCell">
                    <form:input path="login"/>
                </div>
                <div class="formCell">
                    <form:errors path="login" cssClass="error"/>
                </div>
            </div>
            <div class="formRow">
                <div class="formCell">
                    <spring:message code="form.password" text="Password"/>
                </div>
                <div class="formCell">
                    <form:password path="password"/>
                </div>
                <div class="formCell">
                    <form:errors path="password" cssClass="error"/>
                </div>
            </div>
            <div class="formRow">
                <div class="formCell">
                    <input type="submit" value="Sign in">
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
