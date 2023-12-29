function validateForm(form) {
	return true;
}

function Leaving() {
	if (confirm("정말 그룹을 탈퇴 하시겠습니까?") == true) {
		alert("그룹 탈퇴가 완료되었습니다.");
		document.historyFrm.submit();
	} else {
		// 사용자가 취소를 선택한 경우 아무 동작 없음
	}
}