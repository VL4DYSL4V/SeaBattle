<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="../css/SeaTheme.css" %>
    <%@include file="../css/Welcome.css"%>
</style>

<html class="standardBrightFontColor standardTextFont seaBackground">
<head>
    <title><spring:message code="welcome.title" text="Welcome"/></title>
    <meta charset="UTF-8">
</head>
<body>
<div class="header centeredText">
    <spring:message code="welcome.header" text="Welcome to \"Sea battle!\""/>
</div>
<div id="contentPanel" class="contentHolder">
    <div class="contentContainerRow">
        <div id="signInContainer" class="contentContainer sandBackground">
            <div class="centerElementWrapper">
                <img src="https://i.ibb.co/Trp5dhV/signIn.png" alt="*signIn picture*"
                     class="containerAvatar">
            </div>
            <form action="${pageContext.request.contextPath}/signIn" class="centerElementWrapper">
                <input type="submit" value="Sign in" class="standardButton">
            </form>
            <p>Already registered? Click here and keep having fun!</p>
        </div>
        <div id="signUpContainer" class="contentContainer sandBackground">
            <div class="centerElementWrapper">
                <img src="https://i.ibb.co/tPXx0vK/signUp.png" alt="*signUp picture*"
                     class="containerAvatar">
            </div>
            <form action="${pageContext.request.contextPath}/signUp" class="centerElementWrapper">
                <input type="submit" value="Sign up" class="standardButton">
            </form>
            <p>Not registered yet? Click here to make things right!</p>
        </div>
        <div id="rulesContainer" class="contentContainer sandBackground">
            <div class="centerElementWrapper">
                <img src="https://i.ibb.co/NCdqtSr/scroll.png" alt="*rules picture*"
                     class="containerAvatar">
            </div>
            <form action="${pageContext.request.contextPath}/welcome/rules" class="centerElementWrapper">
                <input type="submit" value="Rules of game" class="standardButton">
            </form>
            <p>Unaware with rules of "Sea battle"? This article is for you!</p>
        </div>
    </div>
    <div class="contentContainerRow">
        <div id="guideContainer" class="contentContainer sandBackground">
            <div class="centerElementWrapper">
                <img src="https://i.ibb.co/CHnnkhH/guide.png" alt="*guide picture*"
                     class="containerAvatar">
            </div>
            <form action="${pageContext.request.contextPath}/welcome/guide" class="centerElementWrapper">
                <input type="submit" value="Guide" class="standardButton">
            </form>
            <p>This instructions will help you to get start and become familiar with all features of application!</p>
        </div>
        <div id="faqContainer" class="contentContainer sandBackground">
            <div class="centerElementWrapper">
                <img src="https://i.ibb.co/Rj7sfz3/question-Sign.png" alt="*faq picture*"
                     class="containerAvatar">
            </div>
            <form action="${pageContext.request.contextPath}/welcome/faq" class="centerElementWrapper">
                <input type="submit" value="FAQ" class="standardButton">
            </form>
            <p>Before writing to administration, check this section: maybe there is an answer you desire!</p>
        </div>
        <div id="aboutUsContainer" class="contentContainer sandBackground">
            <div class ="centerElementWrapper">
                <img src="" alt="*about us picture*" class="containerAvatar">
            </div>
            <form action="${pageContext.request.contextPath}/welcome/aboutUs" class="centerElementWrapper">
                <input type="submit" value="About Us" class="standardButton">
            </form>
        </div>
    </div>
</div>
</body>
</html>
