$('.indexchart1').easyPieChart({
			easing: 'easeOutBounce',
			barColor : '#ff3e43',
			lineWidth: 3,
			animate: 1000,
			size:80,
            lineCap: 'square',
            trackColor: '#e5e5e5',
			onStep: function(from, to, percent) {
				$(this.el).find('.w_percent').text(Math.round(percent));
			}
		});	

$('.indexchart2').easyPieChart({
			easing: 'easeOutBounce',
			barColor : '#4caf50',
			lineWidth: 3,
			animate: 1000,
			size:80,
            lineCap: 'square',
            trackColor: '#e5e5e5',
			onStep: function(from, to, percent) {
				$(this.el).find('.w_percent').text(Math.round(percent));
			}
		});	

$('.indexchart3').easyPieChart({
			easing: 'easeOutBounce',
			barColor : '#ffa200',
			lineWidth: 3,
			animate: 1000,
			size:80,
            lineCap: 'square',
            trackColor: '#e5e5e5',
			onStep: function(from, to, percent) {
				$(this.el).find('.w_percent').text(Math.round(percent));
			}
		});	

$('.indexchart4').easyPieChart({
			easing: 'easeOutBounce',
			barColor : '#0acae2',
			lineWidth: 3,
			animate: 1000,
			size:80,
            lineCap: 'square',
            trackColor: '#e5e5e5',
			onStep: function(from, to, percent) {
				$(this.el).find('.w_percent').text(Math.round(percent));
			}
		});	