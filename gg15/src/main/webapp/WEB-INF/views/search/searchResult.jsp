<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>${summoner.name}님의전적정보</title>
</head>
<body>

			<div class="col-sm-4">
			<img alt="아이콘" src="http://ddragon.leagueoflegends.com/cdn/11.11.1/img/profileicon/${summoner.profileIconId}.png"   style="width:300px;height:300px">
	
	
		
				<h3>${summoner.name}</h3>
				<p>Lv. ${summoner.getSummonerLevel()}</p>
			</div>
		

		<c:forEach var="leagueInfo" items="${leagueInfo}" varStatus="s">
				<div class="col-md-5">
					<img alt="랭크 엠블램" 		
						src=<c:out value="../resources/images/Emblem_${leagueInfo.getTier()}.png" />
						class="mx-auto d-block" style="max-width: 40%;">
				</div>
				<div class="col-md-6" style="text-align: center;">
					<c:choose>
						<c:when test="${leagueInfo.getQueueType() == 'RANKED_FLEX_SR'}">
							<h2>자유 랭크</h2>
						</c:when>
						<c:when test="${leagueInfo.getQueueType() ==  'RANKED_TFT'}">
							<h2>전략적 팀전투</h2>
						</c:when>
						<c:when test="${leagueInfo.getQueueType()== 'RANKED_SOLO_5x5'}">
							<h2>솔로 랭크</h2>
						</c:when>
					</c:choose>
					<h3>승리/패배</h3>
					<p style="color: #007bff; font-size: 24px; display: inline">${leagueInfo.getWins()}</p>
					<p style="font-size: 24px; display: inline">/</p>
					<p style="color: #dc3545; font-size: 24px; display: inline">${leagueInfo.getLosses()}</p>
					<fmt:formatNumber var="percent"
						value="${leagueInfo.getWins()/(leagueInfo.getWins()+leagueInfo.getLosses())}"
						pattern="0.00%" />
					<span style="color: #6c757d; font-size: 18px;"> (${percent})
					</span>
					<h3>당신의 티어는?</h3> 
					<p>현재  ${leagueInfo.getTier()}
						${leagueInfo.getRank()} 단계 입니다.</p>
				</div>
		</c:forEach>
</body>
</html> 