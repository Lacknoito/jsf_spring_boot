function keyPressIntegerOnly(myfield, e, dec)
{
	console.log('keyPressIntegerOnly');
	
    var key;
    var keychar;

    if (window.event) key = window.event.keyCode;
    else if (e) key = e.which;
    else return true;
    keychar = String.fromCharCode(key);

    if ((key==null) || (key==0) || (key==8) || 
        (key==9) || (key==13) || (key==27) )
       return true;

    else if ((("0123456789").indexOf(keychar) > -1))
       return true;

    else if (dec && (keychar == "."))
    {
    myfield.form.elements[dec].focus();
    return false;
    }
    else return false;
}

/*price range*/

 $('#sl2').slider();

	var RGBChange = function() {
	  $('#RGB').css('background', 'rgb('+r.getValue()+','+g.getValue()+','+b.getValue()+')')
	};	
		
/*scroll to top*/

$(document).ready(function(){
	$(function () {
		$.scrollUp({
	        scrollName: 'scrollUp', // Element ID
	        scrollDistance: 300, // Distance from top/bottom before showing element (px)
	        scrollFrom: 'top', // 'top' or 'bottom'
	        scrollSpeed: 300, // Speed back to top (ms)
	        easingType: 'linear', // Scroll to top easing (see http://easings.net/)
	        animation: 'fade', // Fade, slide, none
	        animationSpeed: 200, // Animation in speed (ms)
	        scrollTrigger: false, // Set a custom triggering element. Can be an HTML string or jQuery object
					//scrollTarget: false, // Set a custom target element for scrolling to the top
	        scrollText: '<i class="fa fa-angle-up"></i>', // Text for element, can contain HTML
	        scrollTitle: false, // Set a custom <a> title if required.
	        scrollImg: false, // Set true to use image
	        activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
	        zIndex: 2147483647 // Z-Index for the overlay
		});
	});
});


