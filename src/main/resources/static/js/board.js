/**
 *  user 관련 js
 */
 
 let index = {
	init : function(){
		$('#btn-save').on('click', () => { 
			this.save();
		});
	},
	save : function(){

		let data = {
			title : $("#title").val(),
			content : $("#content").val()
		};

		$.ajax({
			type:"POST",
			url:"/api/board",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8", 
			dataType:"json"	
		}).done(function(response){
			//응답결과가 정상 
			alert("글쓰기가 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			//응답결과 실패
			alert(JSON.stringify(error));
		});	
	}
}
index.init();