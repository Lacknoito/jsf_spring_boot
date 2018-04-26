function showModal() {
	$('#myModal').modal();
}

function closeModal(){
	$('#myModal').modal('hide');
	
	/*$('html, body').animate({
        scrollTop: $("#chartdiv1").offset().top
    }, 800);*/
	
	clearDivChart();
}

function statusChange(obj, param){
	document.getElementById("formId:x").value = param;
	document.getElementById("formId:y").value = $(obj).prop("checked");
	
	updateStatus();
}
function showModalPDF() {
	$('#myModalPDF').modal();
}
function clearDivChart(){
	$("#divChart1").removeClass("col-sm-6");
	$("#divChart1").addClass("col-sm-11");
	
	$("#divChart2").removeClass("col-sm-6");
	$("#divChart2").addClass("col-sm-1");
}

function onStartProcess() {
	$('.modalLoading').modal({backdrop: 'static', keyboard: false});
}
function onEndProcess() {
	$('.modalLoading').modal('hide');
}