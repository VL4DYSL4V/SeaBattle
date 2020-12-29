<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    <%@include file="../css/SeaTheme.css"%>
    <%@include file="../css/FormStyle.css"%>
</style>
<html class="standardTextFont standardBrightFontColor seaBackground">
<head>
    <title><spring:message code="signUp.title" text="Sign up"/></title>
    <meta charset="UTF-8">
</head>
<body>
<div class="header centeredText">
    <spring:message code="signUp.header" text="Sign up"/>
</div>
<div class="centerElementWrapper standardDarkFontColor">
    <div class="formHolder standardDarkFontColor bigTextFont">
        <form:form method="post" modelAttribute="registrationForm" cssClass="form sandBackground">
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
                    <spring:message code="form.email" text="E-mail"/>
                </div>
                <div class="formCell">
                    <form:input path="email"/>
                </div>
                <div class="formCell">
                    <form:errors path="email" cssClass="error"/>
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
                    <spring:message code="form.repeat_password" text="Repeat password"/>
                </div>
                <div class="formCell">
                    <form:password path="repeatedPassword"/>
                </div>
                <div class="formCell">
                    <form:errors path="repeatedPassword" cssClass="error"/>
                </div>
            </div>
            <div class="formRow">
                <div class="formCell">
                    <input type="submit" value="Sign up" class="standardButton">
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
