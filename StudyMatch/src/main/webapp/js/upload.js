/**
 * 
 */
 function validateForm(form) { // 필수 항목 입력 확인}
		if (form.title.value == "") {
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		}
		if (form.content.value == "") {
			alert("내용을 입력하세요.");
			form.content.focus();
			return false;
		}
	}
	
	function fileCheck(obj) {
	    pathpoint = obj.value.lastIndexOf('.');
	    filepoint = obj.value.substring(pathpoint+1,obj.length);
	    filetype = filepoint.toLowerCase();
	    if(filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype=='bmp') {

	        // 정상적인 이미지 확장자 파일일 경우 ...

	    } else {
	        alert('이미지 파일만 선택할 수 있습니다.');

	        parentObj  = obj.parentNode
	        node = parentObj.replaceChild(obj.cloneNode(true),obj);

	        return false;
	    }
	    if(filetype=='bmp') {
	        upload = confirm('BMP 파일은 웹상에서 사용하기엔 적절한 이미지 포맷이 아닙니다.\n그래도 계속 하시겠습니까?');
	        if(!upload) return false;
	    }
	}