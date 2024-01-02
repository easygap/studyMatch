/*!
* Start Bootstrap - Simple Sidebar v6.0.6 (https://startbootstrap.com/template/simple-sidebar)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-simple-sidebar/blob/master/LICENSE)
*/
// 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});

$(document).ready(function(){

	  $('#lan').click(function(){
		  var ele = $('#lan').text();
		  if(ele == "어학 ▶"){
			  $('#lan').text("어학 ▼");
		  }
		  if(ele == "어학 ▼"){
			  $('#lan').text("어학 ▶");
		  }
	    $('#lan1').slideToggle();
	    $('#lan2').slideToggle();
	    $('#lan3').slideToggle();
	  })
	  
	  $('#dev').click(function(){
		  var ele = $('#dev').text();
		  if(ele == "개발자 ▶"){
			  $('#dev').text("개발자 ▼");
		  }
		  if(ele == "개발자 ▼"){
			  $('#dev').text("개발자 ▶");
		  }
	    $('#dev1').slideToggle();
	    $('#dev2').slideToggle();
	  })
	  
	  $('#ui').click(function(){
		  var ele = $('#ui').text();
		  if(ele == "UI/UX ▶"){
			  $('#ui').text("UI/UX ▼");
		  }
		  if(ele == "UI/UX ▼"){
			  $('#ui').text("UI/UX ▶");
		  }
	    $('#ui1').slideToggle();
	    $('#ui2').slideToggle();
	    $('#ui3').slideToggle();
	  })
	  
	  $('#aff').click(function(){
		  var ele = $('#aff').text();
		  if(ele == "사무직 ▶"){
			  $('#aff').text("사무직 ▼");
		  }
		  if(ele == "사무직 ▼"){
			  $('#aff').text("사무직 ▶");
		  }
	    $('#aff1').slideToggle();
	    $('#aff2').slideToggle();
	  })
	  
	  $('#fin').click(function(){
		  var ele = $('#fin').text();
		  if(ele == "금융 ▶"){
			  $('#fin').text("금융 ▼");
		  }
		  if(ele == "금융 ▼"){
			  $('#fin').text("금융 ▶");
		  }
	    $('#fin1').slideToggle();
	    $('#fin2').slideToggle();
	  })
})