/**
 *  user 관련 js
 */
 
 let index = {
	init : function(){
		$('#btn-save').on('click', () => { //function을 쓰지 않고 () => {}를 하는 이유는 this를 바인딩하기 위해서
			this.save();
		});
	},
	save : function(){
		//alert("save 호출");
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		};
		
		//console.log(data);
		
		//ajax 비동기통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
		//ajax 호출 시 default가 비동기 호출
		//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자바스크립트 오브젝트로 변환
		$.ajax({
			//회원가입 수행 요청
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data),	//http body 데이터
			contentType:"application/json;charset=utf-8", //body 데이터 형식 (MIME)
			dataType:"json"	//요청을 서버로 해서 응답된 데이터 형식, 기본 응답형식은 문자열이다. (이때, 생긴게 JSON형식이라면 javascript오브젝트로 변경해줌)
		}).done(function(response){
			//응답결과가 정상 
			alert("회원가입이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			//응답결과 실패
			alert(JSON.stringify(error));
		});	
	}
}
index.init();