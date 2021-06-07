<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>${summoner.name}님의전적정보</title>
<style>
.flex-container {
  display: flex;
  background-color: #34deeb;
}

.flex-container > div {
  background-color: #f1f1f1;
  margin: 10px;
  padding: 20px;
  font-size: 30px;
}

.box {
    position:absolute; 
    top:50%; left:50%;
    margin-top:-100px; margin-left:-100px; 
    width:200px; height:200px; 
    }
</style>
</head>
<body>
			<div class="flex-container">
			<div >
			<img alt="아이콘" src="http://ddragon.leagueoflegends.com/cdn/11.11.1/img/profileicon/${summoner.profileIconId}.png"   style="width:300px;height:300px;margin:20px">
	
	
		
				<h3 style="margin:20px">소환사 : ${summoner.name}</h3>
				<h5 style="margin:25px">Lv : ${summoner.getSummonerLevel()}</h5>
			</div>
		

		<c:forEach var="leagueInfo" items="${leagueInfo}" varStatus="s">
				<div align="center"> 
					<img alt="랭크 엠블램" 		
						src=<c:out value="../resources/images/Emblem_${leagueInfo.getTier()}.png" />
						class="mx-auto d-block" style="max-width: 70%; margin:20%">
				</div>
				<div>
					<c:choose>
						<c:when test="${leagueInfo.getQueueType()== 'RANKED_SOLO_5x5'}">
							<h2>솔로 랭크</h2>
						</c:when>
						<c:when test="${leagueInfo.getQueueType() == 'RANKED_FLEX_SR'}">
							<h2>자유 랭크</h2>
						</c:when>
<%-- 						<c:when test="${leagueInfo.getQueueType() ==  'RANKED_TFT'}">
							<h2>전략적 팀전투</h2>
						</c:when> --%>
					
					</c:choose>
					<h3>승리/패배</h3>
					<p style="color: #007bff; font-size: 24px; display: inline">${leagueInfo.getWins()}</p>
					<p style="font-size: 24px; display: inline">/</p>
					<p style="color: #dc3545; font-size: 24px; display: inline">${leagueInfo.getLosses()}</p>
					<fmt:formatNumber var="percent"
						value="${leagueInfo.getWins()/(leagueInfo.getWins()+leagueInfo.getLosses())}"
						pattern="0.00%" />
					<span style="color: #6c757d; font-size: 18px;"> (승률 : ${percent})
					</span>
					<h3>당신의 티어는?</h3> 
					<p>현재  ${leagueInfo.getTier()}
						${leagueInfo.getRank()} 단계 입니다.</p>
				</div>
		</c:forEach>
	</div>
</body>
</html> 