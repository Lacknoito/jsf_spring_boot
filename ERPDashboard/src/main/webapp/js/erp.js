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
function closeModalPDF() {
	$('#myModalPDF').modal('hide');
}
function clearDivChart(){
	$("#divChart1").removeClass("col-sm-6");
	$("#divChart1").addClass("col-sm-11");
	
	$("#divChart2").removeClass("col-sm-6");
	$("#divChart2").addClass("col-sm-1");

	$("#divGraph").css("display", "block");
}

function onStartProcess() {
	$('.modalLoading').modal();
}
function onEndProcess() {
	$('.modalLoading').modal('hide');
}

function handleLegend1Click(graph) {
	document.getElementById("formId:x").value = graph.title;
	
	if(graph.title == 'BSD'
  		|| graph.title == 'FC/KE'
  		|| graph.title == 'UPC'){
  		selectItemFromChart();
  	}else{
  		selectItemFromChartPS();
  		
  		$("#divChart1").removeClass("col-sm-11");
  		$("#divChart1").addClass("col-sm-6");
  		
  		$("#divChart2").removeClass("col-sm-1");
  		$("#divChart2").addClass("col-sm-6");
  	}
}
function handleLegend2Click(graph) {
	document.getElementById("formId:x").value = graph.title;
	selectItemFromChart();
}


function keyPressDecimalOnly(myfield, e, dec)
{
	var key;
    var keychar;
    
    if (window.event) key = window.event.keyCode;
    else if (e) key = e.which;
    else return true;
    keychar = String.fromCharCode(key);
    
	if ((key==null) || (key==0) || (key==8) || 
        (key==9) || (key==13) || (key==27)  || (key==45) ) {
       return true;

    } else if ((("0123456789.").indexOf(keychar) > -1)) {
    	if (keychar == "." && myfield.value.indexOf(".") > -1) {
    		return false; 
    	} else {
       		return true;
       	}

    } else if (dec && (keychar == ".")) {
	    myfield.form.elements[dec].focus();
	    return false;
    } else return false;
}

function converDecimalFormat(obj) {
	if(obj.value) {
		if(obj.value.length == 1
				&& obj.value == '.'){
			obj.value = '0';
		}
		var input = parseFloat(obj.value);
    	obj.value = input.toFixed(2);
	}
}

function start() {
	PF('statusDialog').show();
}

function stop() {
	PF('statusDialog').hide();
}

function divChartClick() {
	document.getElementById("formId:dateStrId").blur();
}
function openEdit() {
	$('#editPopup').modal({
		show : true
	});
}
function cloadEdit() {
	$('#editPopup').modal('hide');
}
function saveComplate(){
	closeModal();
	cloadEdit();
}