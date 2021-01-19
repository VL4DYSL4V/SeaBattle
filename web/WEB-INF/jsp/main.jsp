<%@ page import="entity.User" %>
<%@ page import="data.Rank" %>
<%@ page import="enums.MarineRank" %>
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
<div>
    <div id="battleButtonHolder" class="centerElementWrapper">
        <form action="${pageContext.request.contextPath}/main/battle"
              class="stoneBackground">
            <input type=submit id="battleButton" value="Battle!">
        </form>
    </div>
    <div id="accountInformationHolder" class="contentWrapper" style="margin-left: 4vw; margin-top: 3vw;">
        <div id="account" class="account stoneBackground">
            <div id="rankInfoHolder">
                <canvas id="rankImageCanvas" width="100" height="100"></canvas>
                <div id="rankLoginProgressContainer">
                    <canvas id="rankAndLoginCanvas" width="400" height="50"></canvas>
                    <div id="rankProgressBar" class="progress-bar" style="--width: 0;" data-label='null'></div>
                </div>
            </div>
            <div id="accountButtonHolder">
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
    <div id="shipPlacementDiv" class="contentWrapper" style="margin-left: 20vw; margin-top: 3vw;">
        <canvas id="shipPlacementCanvas" width="350" height="350" style="float: left; background-color: blue;"></canvas>
        <div id="shipPlacementButtonHolder">
            <form class="stoneBackground">
                <input type="submit" id="cancelButton" class="shipPlacementButton" value=""/>
            </form>
            <form class="stoneBackground">
                <input type="submit" id="resetButton" class="shipPlacementButton" value=""/>
            </form>
            <form class="stoneBackground">
                <input type="submit" id="turnAroundButton" class="shipPlacementButton" value=""/>
            </form>
            <form class="stoneBackground">
                <input type="submit" id="confirmButton" class="shipPlacementButton" value=""/>
            </form>
            <form class="stoneBackground">
                <input type="submit" id="generateButton" class="shipPlacementButton" value="\"/>
            </form>
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

    function fillRankProgress(currUserScores, previousPromotionScores, promotionScores) {
        const progressBar = document.getElementById("rankProgressBar");
        const percentage = (currUserScores - previousPromotionScores) / (promotionScores - previousPromotionScores);
        progressBar.style.setProperty('--width', String(percentage));
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

    function drawGridOnShipPlacementCanvas() {
        const shipPlacementCanvas = document.getElementById("shipPlacementCanvas");
        const context2D = shipPlacementCanvas.getContext("2d");
        context2D.fillStyle = "rgb(0, 0, 0)";
        context2D.lineWidth = 3;
        const stripeWidth = shipPlacementCanvas.width / 12;
        const stripeHeight = shipPlacementCanvas.height / 12;
        for (i = 1; i < 12; i++) {
            const x = i * stripeWidth;
            context2D.moveTo(x, stripeHeight);
            context2D.lineTo(x, stripeHeight * 11);
            context2D.stroke();

            const y = i * stripeHeight;
            context2D.moveTo(stripeWidth, y);
            context2D.lineTo(stripeWidth * 11, y);
            context2D.stroke();
        }

        for(i = 1; i < 11; i++){
            context2D.font = (stripeWidth - 1).toString() + "px Arial";
            context2D.fillText(i.toString(), i * stripeWidth, stripeHeight);

            context2D.font = (stripeHeight - 1).toString() + "px Arial";
            context2D.fillText(i.toString(), 0, (i + 1) * stripeHeight);
        }
    }

    drawRank('<%=Rank.of(u.getMarineRank()).getImageURL()%>');
    drawRankRepresentationAndLogin('<%=Rank.of(u.getMarineRank()).getName()%>', '<%=u.getLogin()%>');
    fillRankProgress(<%=u.getScores()%>,
        <%=Rank.of(MarineRank.previous(u.getMarineRank())).getPromotionScores()%>,
        <%=Rank.of(u.getMarineRank()).getPromotionScores()%>);
    hideStatistics();
    drawGridOnShipPlacementCanvas();

</script>

</html>
