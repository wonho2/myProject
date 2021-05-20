<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- 포지션게시판 : 게시물 디테일 시작 -->
<!-- 수정, 삭제 버튼 -->
<c:if test="${mem_num == positionVO.mem_num}">
	<div>
		<input type="button" value="수정" onclick="location.href='modify.do?num=${num}'">
		<input type="button" value="삭제" onclick="location.href='delete.do?num=${num}'" id="btn_delete">
	</div>
</c:if>

<!-- 게시물 상세 -->
<div>
	<h1>${positionVO.title}</h1>
	<section>
		[${positionVO.type}] | ${positionVO.date} | ${positionVO.mem_id}
	</section>
	<section>
		조회수 ${positionVO.view} | 추천수 ${positionVO.fav} | 댓글수 ${positionVO.comment}
	</section>
	<hr size="1" noshade="noshade" width="100%">
	<c:if test="${fn:endsWith(positionVO.uploadfile,'.jpg') ||
					fn:endsWith(positionVO.uploadfile,'.JPG') ||
					fn:endsWith(positionVO.uploadfile,'.gif') ||
					fn:endsWith(positionVO.uploadfile,'.GIF') ||
					fn:endsWith(positionVO.uploadfile,'.png') ||
					fn:endsWith(positionVO.uploadfile,'.PNG')}">
		<div>
			<img src="imageView.do?num=${num}">
		</div>
	</c:if>
	<div>
		<input type="button" value="추천" id="btn_fav">
	</div>
</div>
<!-- 포지션게시판 : 게시물 디테일 끝 -->