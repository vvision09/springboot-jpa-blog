<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="#" method="post">
		<div class="form-group">
			<label for="title">title</label> 
			<input type="text" class="form-control" placeholder="Enter title" id="title">
		</div>
		<div class="form-group">
			<label for="content">Content:</label> 
			<textarea class="form-control summernote" rows="3" cols="" id="content" style="resize:none"></textarea>
		</div>
		
		<button id="btn-save" class="btn btn-primary">글쓰기</button>
	</form>
</div>
<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>



