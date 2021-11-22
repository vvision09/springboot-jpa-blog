<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp"%>

<div class="container">
	<c:forEach var="board" items="${boards.content}">
			<div class="card m-3">
				<div class="card-body">
					<h4 class="card-title">${board.title}</h4>
					<h5 class="card-title" style="color : #8f8f8f;">${board.content}</h5>
					<a href="#" class="btn btn-primary">상세보기</a>
				</div>
			</div>
	</c:forEach>
	<ul class="pagination justify-content-center">
	 	<c:choose>
	 		<c:when test="${boards.first}">
	 			<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
	 		</c:when>
	 		<c:otherwise>
		 		<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
	 		</c:otherwise>
	 	</c:choose>
	 	
	 	 <c:forEach var="i" begin="1" end="${boards.totalPages}">
	 	 	<c:choose>
	 	 		<c:when test="${i-1 eq boards.number }">
	 	 			<li class="page-item active"><a class="page-link" href="?page=${i-1}">${i}</a></li>
	 	 		</c:when>
	 	 		<c:otherwise>
	 	 			<li class="page-item"><a class="page-link" href="?page=${i-1}">${i}</a></li>
	 	 		</c:otherwise>
	 	 	</c:choose>
            
        </c:forEach>
        
	 	<c:choose>
	 		<c:when test="${boards.last}">
	 			<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
	 		</c:when>
	 		<c:otherwise>
		 		<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
	 		</c:otherwise>
	 	</c:choose>
	  
		
	  
	</ul>
</div>
<%@ include file="layout/footer.jsp"%>



