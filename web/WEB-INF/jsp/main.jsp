<%@ page import="entity.User" %>
<%@ page import="data.Rank" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User u = (User) request.getSession().getAttribute("user");
%>

<style>
    <%@include file="../css/SeaTheme.css"%>
    <%@include file="../css/Main.css"%>
</style>

<html class="standardBrightFontColor seaBackground standardTextFont">
<head>
    <title>
        <spring:message code="main.title" text="Main"/>
    </title>
</head>
<body>
<div class="centeredText header">
    <spring:message code="main.header" text="Main"/>
</div>
<div>
    <div id="findOpponentWrapper" class="centerElementWrapper">
        <form action="${pageContext.request.contextPath}/main/battle" class="sandBackground bigTextFont">
            <input type=submit value="Battle!" class="standardButton">
        </form>
    </div>
    <div id="accountInformationHolder" class="accountHolder">
        <div id="account" class="account stoneBackground">
            <div id="rankInfoHolder">
                <canvas id="rankImageCanvas" width="100" height="100"></canvas>
                <div id="rankLoginProgressContainer">
                    <canvas id="rankAndLoginCanvas" width="400" height="50"></canvas>
                    <div id="rankProgressBar" class="progress-bar" style="--width: 0;" data-label='null'></div>
                </div>
            </div>
            <div id = "accountButtonHolder">
                <input type="submit" value="Statistics" class="accountButton" onclick="showStatistics()"/>
                <table id="statisticHolder">
                    <tr>
                        <td>Won battles:</td>
                        <td><%=u.getGameStatistics().getWonBattles()%>
                        </td>
                    </tr>
                    <tr>
                        <td>Lost battles:</td>
                        <td><%=u.getGameStatistics().getLostBattles()%>
                        </td>
                    </tr>
                    <tr>
                        <td>Destroyed ships:</td>
                        <td><%=u.getGameStatistics().getDestroyed()%>
                        </td>
                    </tr>
                    <tr>
                        <td>Damaged ships:</td>
                        <td><%=u.getGameStatistics().getDamaged()%>
                        </td>
                    </tr>
                </table>
                <form style="margin-top: 1vw;">
                    <input type="submit" value="Edit profile" class="accountButton"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

<script>

    function drawRank(rankImageURL) {
        const canvas = document.getElementById("rankImageCanvas");
        const context2D = canvas.getContext("2d");
        const img = new Image();
        img.onload = () => {
            context2D.drawImage(img, 0, 0, 100, 100);
        };
        img.src = rankImageURL;
    }

    function drawRankRepresentationAndLogin(rankRepresentation, login) {
        const canvas = document.getElementById("rankAndLoginCanvas");
        const context2D = canvas.getContext("2d");
        context2D.font = '1.2vw Arial';
        context2D.fillStyle = 'White';
        context2D.fillText(rankRepresentation + " " + login, 0, 40);
    }

    //TODO: IT WILL INCORRECTLY CALCULATE WIDTH, IF Curr_SCORES != 0
    function fillRankProgress(currUserScores, promotionScores) {
        const progressBar = document.getElementById("rankProgressBar");
        progressBar.style.setProperty('--width', String(currUserScores / promotionScores));
        progressBar.setAttribute("data-label", currUserScores + "/" + promotionScores);
    }

    function showStatistics() {
        const statisticsDiv = document.getElementById("statisticHolder");
        if (statisticsDiv.style.display === "none") {
            statisticsDiv.style.display = "block";
        } else {
            statisticsDiv.style.display = "none";
        }
    }

    function hideStatistics() {
        const statisticsDiv = document.getElementById("statisticHolder");
        statisticsDiv.style.display = "none";
    }

    drawRank('<%=Rank.of(u.getMarineRank()).getImageURL()%>');
    drawRankRepresentationAndLogin('<%=Rank.of(u.getMarineRank()).getName()%>', '<%=u.getLogin()%>');
    fillRankProgress(<%=u.getScores()%>, <%=Rank.of(u.getMarineRank()).getPromotionScores()%>);
    hideStatistics();

</script>

</html>
