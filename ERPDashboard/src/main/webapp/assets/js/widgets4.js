
  $('#widgets4chart1').sparkline([ 1, 4, 5, 9, 8, 10, 5, 8,4,10,7,5,7], {
            type: 'bar',
            height: '50',
            barWidth: '4',
            resize: true,
            barSpacing: '5',
            barColor: '#7460ee'
        });
		
  $('#widgets4chart2').sparkline([20, 40, 30], {
            type: 'pie',
            width: '65',
            height: '65',
            resize: true,
            sliceColors: ['#0acae2', '#8e88ff', '#f1f2f7']
        });		
		
  $("#widgets4chart3").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
		type: 'line',
		width: '100',
		height: '60',
		lineColor: '#4caf50',
		fillColor: '#4caf50',
		maxSpotColor: '#4caf50',
		highlightLineColor: 'rgba(0, 0, 0, 0.2)',
		highlightSpotColor: '#4caf50'
	});
	
	
   $("#widgets4chart4").sparkline([0, 5, 10, 5, 15, 10, 20, 10, 5, 10, 5, 15, 10 ], {
            type: 'line',
            width: '100',
            height: '60',
			lineWidth: '2',
            lineColor: '#fff',
            fillColor: 'transparent',
            spotColor: '#fff',
            minSpotColor: undefined,
            maxSpotColor: undefined,
            highlightSpotColor: undefined,
            highlightLineColor: undefined
    }); 
	
	//peity pie
            $("span.pie").peity("pie",{
                width: 70,
                height: 70 
            });
			
	//peity donut

          $("span.donut").peity("donut",{
                width: 70,
                height: 70 
            });	

	$('.widgets4chart4').easyPieChart({
			easing: 'easeOutBounce',
			barColor : '#da37f5',
			lineWidth: 3,
			animate: 1000,
			size:80,
            lineCap: 'square',
            trackColor: '#e5e5e5',
			onStep: function(from, to, percent) {
				$(this.el).find('.w_percent').text(Math.round(percent));
			}
		});	

        $('.widgets4chart5').easyPieChart({
            easing: 'easeOutBounce',
            barColor : '#1dd625',
            lineWidth: 3,
            animate: 1000,
            size:80,
            lineCap: 'square',
            trackColor: '#e5e5e5',
            onStep: function(from, to, percent) {
                $(this.el).find('.w_percent').text(Math.round(percent));
            }
        }); 

        $('.widgets4chart6').easyPieChart({
            easing: 'easeOutBounce',
            barColor : '#24e7ff',
            lineWidth: 3,
            animate: 1000,
            size:80,
            scaleColor: false,
            lineCap: 'square',
            trackColor: '#e5e5e5',
            onStep: function(from, to, percent) {
                $(this.el).find('.w_percent').text(Math.round(percent));
            }
        }); 

        $('.widgets4chart7').easyPieChart({
            easing: 'easeOutBounce',
            barColor : '#ff76a5',
            lineWidth: 3,
            animate: 1000,
            size:80,
            scaleColor: false,
            lineCap: 'square',
            trackColor: '#e5e5e5',
            onStep: function(from, to, percent) {
                $(this.el).find('.w_percent').text(Math.round(percent));
            }
        }); 	
			
	 
	
	$('#widgets4chart8').sparkline([ 1, 4, 5, 9, 8, 10, 5, 8,4,10,7,5,7], {
            type: 'bar',
            height: '50',
            barWidth: '4',
            resize: true,
            barSpacing: '5',
            barColor: '#ffeb3b'
        });
        
  $('#widgets4chart9').sparkline([20, 40, 30], {
            type: 'pie',
            width: '65',
            height: '65',
            resize: true,
            sliceColors: ['#0acae2', '#ff575b', '#f1f2f7']
        });     
        
  $("#widgets4chart10").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
        type: 'line',
        width: '100',
        height: '60',
        lineColor: '#40c345',
        fillColor: '#40c345',
        maxSpotColor: '#40c345',
        highlightLineColor: 'rgba(0, 0, 0, 0.2)',
        highlightSpotColor: '#40c345'
    });
    
    
   $("#widgets4chart11").sparkline([0, 5, 10, 5, 15, 10, 20, 10, 5, 10, 5, 15, 10 ], {
            type: 'line',
            width: '100',
            height: '60',
            lineWidth: '2',
            lineColor: '#ff4987',
            fillColor: 'transparent',
            spotColor: '#fff',
            minSpotColor: undefined,
            maxSpotColor: undefined,
            highlightSpotColor: undefined,
            highlightLineColor: undefined
    }); 


   $('#widgets4chart12').sparkline([ 1, 4, 5, 9, 8, 10, 5, 8,4,10,7,5,7], {
            type: 'bar',
            height: '60',
            barWidth: '5',
            resize: true,
            barSpacing: '6',
            barColor: '#7460ee'
        });
        
  $('#widgets4chart13').sparkline([20, 40, 30], {
            type: 'pie',
            width: '70',
            height: '70',
            resize: true,
            sliceColors: ['#0acae2', '#ff575b', '#f1f2f7']
        });     
        
  $("#widgets4chart14").sparkline([2,4,4,6,8,5,6,4,8,6,6,2 ], {
        type: 'line',
        width: '130',
        height: '70',
        lineColor: '#40c345',
        fillColor: 'rgba(249, 255, 0, 0.65)',
        maxSpotColor: '#40c345',
        highlightLineColor: 'rgba(0, 0, 0, 0.2)',
        highlightSpotColor: '#40c345'
    });
    
    
   $("#widgets4chart15").sparkline([0, 5, 10, 5, 15, 10, 20, 10, 5, 10, 5, 15, 10 ], {
            type: 'line',
            width: '100',
            height: '60',
            lineWidth: '2',
            lineColor: '#ff4987',
            fillColor: 'transparent',
            spotColor: '#fff',
            minSpotColor: undefined,
            maxSpotColor: undefined,
            highlightSpotColor: undefined,
            highlightLineColor: undefined
    }); 