function checkMember() {
	let form = document.member;
	let id = form.memberId.value;
	let pw1 = form.passwd1.value;
	let pw2 = form.passwd2.value;
	let name = form.name.value;
	let btnCheck = form.btnCheck.value;

	//정규 표현식
	let pw_pat1 = /[0-9]+/ // 숫자만
	let pw_pat2 = /[a-z]+/ // 영어만
	let pw_pat3 = /[~!@#$%^&*()_=]+/ // 특수문자만
	let name_pat = /[가-힣]+/


	if (id.length < 4 || id.length > 15) {
		alert("아이디는 4~15자까지 입력 가능합니다.");
		form.memberId.select();
		return false;
	} else if (pw1.length < 8 || !pw_pat1.test(pw1)
		|| !pw_pat2.test(pw1) || !pw_pat3.test(pw1)) {
		alert("비밀번호는 영문자, 숫자, 특수문자 포함 8자 이상입니다.")
		return false;
	} else if (pw1 != pw2) {
		alert("비밀번호가 일치하지 않습니다.")
		form.passwd2.select();
		return false;
	}
	else if (name == "" && name_pat.test(name)) {
		alert("이름을 입력해 주세욜")
		form.name.focus();
		return false;
	} else if (btnCheck == "N") {
		alert("중복 확인을 해 주세요.")
		return false;
	}
	else {
		form.submit();
	}
}


function checkId() {
	let memberId = $('#memberId').val();
	$.ajax({
		type: "post",
		url: "http://localhost:8080/checkid",
		dataType: "text",
		data: { id: memberId },
		success: function(data) {
			if ($.trim(data) == "useable") {
				$('#btnCheck').attr('value', 'Y');
				$("#check").text("사용가능한 ID 입니다.")
					.css({ 'color': 'blue', 'padding-top': '5px' });
				
			} else {
				$("#check").text("이미 존재하는 ID 입니다.")
					.css({ 'color': 'red', 'padding-top': '5px' });
			}
		},
		error: function() {
			alert("다시해");
		}
	});
}